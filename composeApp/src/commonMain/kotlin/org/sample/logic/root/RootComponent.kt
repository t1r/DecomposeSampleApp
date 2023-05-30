package org.sample.logic.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.sample.logic.food.AddFoodEntryComponent
import org.sample.logic.food.ProductShort

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class Main(
            val foodComponent: AddFoodEntryComponent<ProductShort>,
            val recipeComponent: AddFoodEntryComponent<ProductShort>,
        ) : Child()
    }
}