package com.example.module_home.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.colorResource
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
    val favoriteList by composeViewModel.favoriteListFlow.collectAsState()
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
        BodyInfoSection(
            "bodyInfoList",
            bodyList
        )
        FavoriteView(
            favoriteList,
            modifier = Modifier
                .padding(top = 8.0.dp)
                .background(colorResource(com.example.module_home.R.color.purple_700))
        )
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
fun BodyInfoSection(
    titleString: String,
    bodyDataList: List<BodyItemInfo>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = titleString,
            style = MaterialTheme.typography.titleMedium
        )
        BodyInfoView(
            bodyDataList,
            modifier = Modifier
                .background(colorResource(com.example.module_home.R.color.purple_200))
                .padding(8.0.dp)
        )
    }
}

@Composable
fun BodyInfoView(bodyDataList: List<BodyItemInfo>, modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            horizontal = 16.0.dp
        ),
        modifier = modifier,
    ) {
        items(bodyDataList) { itemData ->
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
                .size(88.0.dp)
                .clip(CircleShape)
                .paddingFromBaseline(10.0.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(text),
            modifier = Modifier
                .paddingFromBaseline(10.0.dp, 10.0.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun FavoriteView(favouriteDataList: List<BodyItemInfo>, modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        modifier = modifier
            .height(168.0.dp)
            .padding(top = 8.0.dp, bottom = 8.0.dp),
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = 16.0.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.0.dp)
    ) {
        items(favouriteDataList) { item ->
            FavoriteCard(item.drawableRes, item.titleRes, Modifier.height(80.0.dp))
        }
    }
}

@Composable
fun FavoriteCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int, modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        Row(
            modifier = Modifier.width(200.0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                modifier = Modifier.size(80.0.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 15.0.dp)
            )
        }
    }
}
