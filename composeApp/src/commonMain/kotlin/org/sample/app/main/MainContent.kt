package org.sample.app.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.sample.app.food.FoodPageContent
import org.sample.app.food.RecipePageContent
import org.sample.logic.food.AddFoodEntryComponent
import org.sample.logic.food.ProductShort

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(
    foodComponent: AddFoodEntryComponent<ProductShort>,
    recipeComponent: AddFoodEntryComponent<ProductShort>,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            TabRow(
                modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = pagerState.currentPage,
            ) {
                Tab(
                    selected = pagerState.currentPage == 0,
                    onClick = { coroutineScope.launch { pagerState.scrollToPage(0) } },
                ) {
                    Text(
                        text = "Food",
                        modifier = Modifier.padding(12.dp),
                        softWrap = false,
                    )
                }
                Tab(
                    selected = pagerState.currentPage == 1,
                    onClick = { coroutineScope.launch { pagerState.scrollToPage(1) } },
                ) {
                    Text(
                        text = "Recipe",
                        modifier = Modifier.padding(12.dp),
                        softWrap = false,
                    )
                }
            }
        },
        content = { pv ->
            Column(
                modifier = Modifier
                    .padding(pv)
                    .fillMaxSize(),
            ) {
                HorizontalPager(
                    modifier = Modifier
                        .weight(1F),
                    pageCount = 2,
                    state = pagerState,
                ) { index ->
                    when (index) {
                        0 -> FoodPageContent(
                            foodComponent = foodComponent,
                            modifier = Modifier.fillMaxSize()
                        )

                        else -> RecipePageContent(
                            recipeComponent = recipeComponent,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    )


    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { index ->
            coroutineScope.launch { pagerState.scrollToPage(index) }
        }
    }
}