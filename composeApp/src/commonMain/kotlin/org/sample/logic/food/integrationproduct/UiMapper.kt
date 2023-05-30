package org.sample.logic.food.integrationproduct

import org.sample.logic.food.ProductItem
import org.sample.logic.food.ProductShort

internal fun map(
    productList: List<ProductItem>?,
): List<ProductShort> {
    return productList.orEmpty().map { from ->
        ProductShort(
            id = from.id,
            title = from.title,
        )
    }
}