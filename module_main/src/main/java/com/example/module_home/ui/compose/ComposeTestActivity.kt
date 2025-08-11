package com.example.module_home.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.content.PagePath
import com.example.module_home.ui.compose.ui.theme.Andoid_sumTheme
import com.example.module_home.ui.data.BodyItemInfo
import com.example.module_home.ui.vm.ComposeViewModel
import com.example.module_utils.R
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
    val bodyList by composeViewModel.bodyListFlow.collectAsState()
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
        SearchBar(modifier = Modifier.padding(15.0.dp))
        BodyInfoView(bodyList, modifier = Modifier.padding(15.0.dp))
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "", modifier = modifier
            .fillMaxWidth()
            .height(50.dp), leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = null
            )
        }, placeholder = { Text(stringResource(R.string.placeholder_search)) }, onValueChange = {})
}

@Composable
fun BodyInfoView(itemDataList: List<BodyItemInfo>, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier) {
        items(itemDataList) { itemData ->
            BodyInfoItemView(itemData.drawableRes, itemData.titleRes)
        }
    }
}

@Composable
fun BodyInfoItemView(
    @DrawableRes drawable: Int,
    @StringRes text: Int, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            modifier = Modifier
                .size(30.0.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(text),
            modifier = Modifier
                .width(45.0.dp)
                .padding(0.0.dp, 10.0.dp, 0.0.dp, 0.0.dp)
        )
    }
}
