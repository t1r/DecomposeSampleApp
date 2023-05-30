package org.sample.logic.food

class FoodRepository {

    fun getProducts(): List<ProductItem> {
        return mutableListOf<ProductItem>().apply {
            repeat(30) {
                add(
                    ProductItem(
                        id = it.toLong(),
                        title = "Product #$it",
                    )
                )
            }
        }
    }

    fun getReceipts(): List<RecipeItem> {
        return mutableListOf<RecipeItem>().apply {
            repeat(30) {
                add(
                    RecipeItem(
                        id = it.toLong(),
                        title = "Recipe #$it",
                        productIds = listOf((it + 1000).toLong()),
                    )
                )
            }
        }
    }
}