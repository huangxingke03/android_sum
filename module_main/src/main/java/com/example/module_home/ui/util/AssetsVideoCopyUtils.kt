import android.content.Context
import android.util.Log
import java.io.*

object AssetsNoOverwriteCopyUtils {

    private const val TAG = "AssetsCopy"

    /**
     * 单个文件复制（不覆盖）
     */
    fun copySingleFileNoOverwrite(
        context: Context,
        assetsPath: String,
        targetFile: File,
        onProgress: (progress: Int) -> Unit = {}
    ): Boolean {
        // 不覆盖：已存在且非空直接跳过
        if (targetFile.exists() && targetFile.length() > 0) {
            Log.d(TAG, "已存在，跳过: ${targetFile.absolutePath}")
            onProgress(100)
            return true // skipped
        }

        targetFile.parentFile?.mkdirs()

        try {
            context.assets.open(assetsPath).use { input ->
                val totalSize = input.available().toLong()
                FileOutputStream(targetFile).use { output ->
                    val buffer = ByteArray(8 * 1024)
                    var copied: Long = 0
                    var bytesRead: Int

                    while (input.read(buffer).also { bytesRead = it } != -1) {
                        output.write(buffer, 0, bytesRead)
                        copied += bytesRead
                        val progress = if (totalSize > 0) {
                            (copied * 100 / totalSize).coerceAtMost(100).toInt()
                        } else 100
                        onProgress(progress)
                    }
                    output.flush()
                }
            }
            Log.d(TAG, "复制完成: $assetsPath → ${targetFile.absolutePath}")
            onProgress(100)
            return false
        } catch (e: Exception) {
            Log.e(TAG, "复制失败: $assetsPath", e)
            targetFile.delete()
            onProgress(-1)
            throw e
        }
    }

    /**
     * 文件夹递归复制（不覆盖）
     */
    fun copyFolderNoOverwrite(
        context: Context,
        assetsPath: String,
        targetDir: File,
        onProgress: (fileName: String, progress: Int) -> Unit = { _, _ -> }
    ) {
        if (!targetDir.exists()) targetDir.mkdirs()

        val files = context.assets.list(assetsPath) ?: return

        for (file in files) {
            val subAssetsPath = if (assetsPath.isEmpty()) file else "$assetsPath/$file"
            val subTarget = File(targetDir, file)

            if (context.assets.list(subAssetsPath)?.isNotEmpty() == true) {
                // 子文件夹
                copyFolderNoOverwrite(context, subAssetsPath, subTarget, onProgress)
            } else {
                // 子文件
                val fileName = file
                copySingleFileNoOverwrite(
                    context = context,
                    assetsPath = subAssetsPath,
                    targetFile = subTarget
                ) { progress ->
                    onProgress(fileName, progress)
                }
            }
        }
    }
}