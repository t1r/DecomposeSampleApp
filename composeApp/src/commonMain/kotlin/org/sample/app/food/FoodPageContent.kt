package org.sample.app.food

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.sample.logic.food.AddFoodEntryComponent
import org.sample.logic.food.ProductShort

@Composable
fun FoodPageContent(
    foodComponent: AddFoodEntryComponent<ProductShort>,
    modifier: Modifier = Modifier,
) {
    val foodModel by foodComponent.models.collectAsState(AddFoodEntryComponent.Model())

    LazyColumn(
        modifier = modifier
    ) {
        items(foodModel.itemList.orEmpty()) { item ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(item.title)
            }
        }
    }
}

@Composable
fun RecipePageContent(
    recipeComponent: AddFoodEntryComponent<ProductShort>,
    modifier: Modifier = Modifier,
) {
    val recipeModel by recipeComponent.models.collectAsState(AddFoodEntryComponent.Model())

    LazyColumn(
        modifier = modifier
    ) {
        items(recipeModel.itemList.orEmpty()) { item ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(item.title)
            }
        }
    }
}