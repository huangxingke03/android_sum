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
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
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

@Route(path = PagePath.ModuleMainPage.COMPOSE_TEST_PAGE)
class ComposeTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Andoid_sumTheme {
        Scaffold(bottomBar = { NavigationBarView() }) { padding ->
            HomeScreen(
                Modifier.padding(
                    padding
                )
            )
        }
    }
}

@Preview
@Composable
fun HomeScreen(modifier: Modifier = Modifier, composeViewModel: ComposeViewModel = viewModel()) {
    val title by composeViewModel.titleFlow.collectAsState()
    val favoriteTitle by composeViewModel.favoriteTitleFlow.collectAsState()
    val bodyList by composeViewModel.bodyListFlow.collectAsState()
    val favoriteList by composeViewModel.favoriteListFlow.collectAsState()
    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar()
        Spacer(modifier = Modifier.height(10.0.dp))
        BodyInfoSection(title, bodyList)
        Spacer(modifier = Modifier.height(10.0.dp))
        FavoriteSection(favoriteTitle, favoriteList)
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
                .padding(top = 8.0.dp)
        )
    }
}

@Composable
fun FavoriteSection(
    titleString: String,
    favoriteDataList: List<BodyItemInfo>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = titleString,
            style = MaterialTheme.typography.titleMedium
        )
        FavoriteView(
            favoriteDataList, modifier = Modifier
                .background(colorResource(com.example.module_home.R.color.purple_700))
                .padding(top = 8.0.dp)
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
fun FavoriteView(
    favouriteDataList: List<BodyItemInfo>,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        modifier = modifier
            .height(168.0.dp),
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            16.0.dp
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

@Composable
fun NavigationBarView(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null
                )
            },
            label = { Text(text = stringResource(R.string.bottom_navigation_profile)) },
            selected = true, onClick = {})
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = { Text(text = stringResource(R.string.bottom_navigation_home)) },
            selected = false, onClick = {})
    }
}
