package org.sample.logic.food.container

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import org.sample.logic.food.FoodRepository

class AddFoodEntryContainerComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val foodRepository: FoodRepository,
) : AddFoodEntryContainerComponent, ComponentContext by componentContext {
}