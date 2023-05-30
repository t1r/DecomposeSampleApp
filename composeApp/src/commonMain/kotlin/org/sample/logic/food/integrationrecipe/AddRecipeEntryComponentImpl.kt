package org.sample.logic.food.integrationrecipe

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.sample.logic.common.stateToModel
import org.sample.logic.food.AddFoodEntryComponent
import org.sample.logic.food.AddFoodEntryComponent.Event
import org.sample.logic.food.AddFoodEntryComponent.Model
import org.sample.logic.food.FoodRepository
import org.sample.logic.food.ProductShort

class AddRecipeEntryComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    foodRepository: FoodRepository,
) : AddFoodEntryComponent<ProductShort>, ComponentContext by componentContext {

    // Is class as key ok?
    // Exception  without key
    private val store = instanceKeeper.getStore(AddRecipeEntryStoreProvider::class) {
        AddRecipeEntryStoreProvider(
            storeFactory = storeFactory,
            foodRepository = foodRepository,
        ).provide()
    }

    override val models: Flow<Model<ProductShort>> = store.states.map { state ->
        stateToModel(
            state = state,
            mapItemAction = { s ->
                map(productList = s.productList)
            }
        )
    }

    override val events: Flow<Event> = store.labels.map { labelToEvent(it) }
}