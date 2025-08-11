package com.example.module_home.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.content.PagePath
import com.example.module_home.ui.compose.ui.theme.Andoid_sumTheme
import com.example.module_home.ui.vm.ComposeViewModel
import kotlin.random.Random

@Route(path = PagePath.ModuleMainPage.COMPOSE_TEST_PAGE)
class ComposeTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Andoid_sumTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Greeting()
                }
            }
        }
    }
}

@Preview
@Composable
fun Greeting(composeViewModel: ComposeViewModel = viewModel()) {
    val title by composeViewModel.titleFlow.collectAsState()
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title
        )
        Button(onClick = { composeViewModel.updateTitle("新标题 ${Random.nextInt()}") }) {
            Text("更新数据")
        }
    }
}
