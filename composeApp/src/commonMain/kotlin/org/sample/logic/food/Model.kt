package org.sample.logic.food

data class ProductItem(
    val id: Long,
    val title: String,
)

data class RecipeItem(
    val id: Long,
    val title: String,
    val productIds: List<Long>,
)