package org.sample.logic.food.integrationrecipe

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import org.sample.logic.food.AddFoodEntryStore
import org.sample.logic.food.AddFoodEntryStore.Action
import org.sample.logic.food.AddFoodEntryStore.Intent
import org.sample.logic.food.AddFoodEntryStore.Label
import org.sample.logic.food.AddFoodEntryStore.Message
import org.sample.logic.food.AddFoodEntryStore.State
import org.sample.logic.food.FoodRepository
import org.sample.logic.food.RecipeItem

class AddRecipeEntryStoreProvider(
    private val storeFactory: StoreFactory,
    private val foodRepository: FoodRepository,
) {

    fun provide(): AddFoodEntryStore<RecipeItem> =
        object : AddFoodEntryStore<RecipeItem>,
            Store<Intent, State<RecipeItem>, Label> by storeFactory.create(
                name = "AddRecipeEntryStoreProvider",
                initialState = State(),
                bootstrapper = BootstrapperImpl(),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl,
            ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<Intent, Action, State<RecipeItem>, Message<RecipeItem>, Label>() {

        override fun executeAction(action: Action, getState: () -> State<RecipeItem>) =
            when (action) {
                Action.Init -> dispatch(Message.ProductList(foodRepository.getReceipts()))
            }
    }

    private object ReducerImpl : Reducer<State<RecipeItem>, Message<RecipeItem>> {
        override fun State<RecipeItem>.reduce(msg: Message<RecipeItem>): State<RecipeItem> =
            when (msg) {
                is Message.ProductList -> copy(
                    productList = msg.productList,
                )
            }
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.Init)
        }
    }
}