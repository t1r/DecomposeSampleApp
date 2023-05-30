package org.sample.logic.food.integrationrecipe

import org.sample.logic.food.ProductShort
import org.sample.logic.food.RecipeItem

internal fun map(
    productList: List<RecipeItem>?,
): List<ProductShort> {
    return productList.orEmpty().map { from ->
        ProductShort(
            id = from.id,
            title = from.title,
        )
    }
}