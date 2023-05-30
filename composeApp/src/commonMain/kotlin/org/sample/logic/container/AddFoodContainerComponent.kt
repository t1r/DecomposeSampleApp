package org.sample.logic.container

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import org.sample.logic.food.AddFoodEntryComponent
import org.sample.logic.food.ProductShort
import org.sample.logic.food.container.AddFoodEntryContainerComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class AddFoodEntry(
            val component: AddFoodEntryContainerComponent,
            val foodComponent: AddFoodEntryComponent<ProductShort>,
            val recipeComponent: AddFoodEntryComponent<ProductShort>,
        ) : Child()
    }
}