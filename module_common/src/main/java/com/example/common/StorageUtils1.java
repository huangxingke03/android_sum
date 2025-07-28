package com.example.common;

import android.os.Environment;
import android.os.StatFs;

import java.util.Locale;

public class StorageUtils1 {
    public static String queryStorage() {

        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());

        //存储块总数量
        long blockCount = statFs.getBlockCount();
        //块大小
        long blockSize = statFs.getBlockSize();
        //可用块数量
        long availableCount = statFs.getAvailableBlocks();
        //剩余块数量，注：这个包含保留块（including reserved blocks）即应用无法使用的空间
        long freeBlocks = statFs.getFreeBlocks();
        //这两个方法是直接输出总内存和可用空间，也有getFreeBytes
        //API level 18（JELLY_BEAN_MR2）引入
        long totalSize = statFs.getTotalBytes();
        long availableSize = statFs.getAvailableBytes();

        LogUtils.d("total = " + getUnit(totalSize));
        LogUtils.d("availableSize = " + getUnit(availableSize));

        //这里可以看出 available 是小于 free ,free 包括保留块。
        LogUtils.d("total = " + getUnit(blockSize * blockCount));
        LogUtils.d("available = " + getUnit(blockSize * availableCount));
        LogUtils.d("free = " + getUnit(blockSize * freeBlocks));

        return "total = " + getUnit(totalSize) + ", availableSize = " + getUnit(availableSize) + ",total = " + getUnit(blockSize * blockCount)+", available = " + getUnit(blockSize * availableCount)+", free = " + getUnit(blockSize * freeBlocks);
    }

    private static String[] units = {
            "B", "KB", "MB", "GB", "TB"};

    /**
     * 单位转换
     */
    private static String getUnit(float size) {

        int index = 0;
        while (size > 1024 && index < 4) {

            size = size / 1024;
            index++;
        }
        return String.format(Locale.getDefault(), " %.2f %s", size, units[index]);
    }

}
