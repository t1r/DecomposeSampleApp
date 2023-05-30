package org.sample.logic.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import org.sample.logic.food.AddFoodEntryComponent
import org.sample.logic.food.FoodRepository
import org.sample.logic.food.ProductShort
import org.sample.logic.food.integrationproduct.AddFoodEntryComponentImpl
import org.sample.logic.food.integrationrecipe.AddRecipeEntryComponentImpl
import org.sample.logic.root.RootComponent.Child

class RootComponentImpl internal constructor(
    componentContext: ComponentContext,
    private val addFoodEntry: (ComponentContext) -> AddFoodEntryComponent<ProductShort>,
    private val addRecipeEntry: (ComponentContext) -> AddFoodEntryComponent<ProductShort>,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()
    private val stack = childStack(
        initialConfiguration = Configuration.Main,
        source = navigation,
        handleBackButton = true,
        childFactory = ::createChild
    )

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        foodRepository: FoodRepository,
    ) : this(
        componentContext = componentContext,
        addFoodEntry = { childContext ->
            AddFoodEntryComponentImpl(
                componentContext = childContext,
                storeFactory = storeFactory,
                foodRepository = foodRepository,
            )
        },
        addRecipeEntry = { childContext ->
            AddRecipeEntryComponentImpl(
                componentContext = childContext,
                storeFactory = storeFactory,
                foodRepository = foodRepository,
            )
        },
    )

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child = when (configuration) {
        is Configuration.Main -> Child.Main(
            foodComponent = addFoodEntry(componentContext),
            recipeComponent = addRecipeEntry(componentContext),
        )
    }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Main : Configuration()
    }
}