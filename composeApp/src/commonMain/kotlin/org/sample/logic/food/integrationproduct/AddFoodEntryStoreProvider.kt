package org.sample.logic.food.integrationproduct

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
import org.sample.logic.food.ProductItem

class AddFoodEntryStoreProvider(
    private val storeFactory: StoreFactory,
    private val foodRepository: FoodRepository,
) {

    fun provide(): AddFoodEntryStore<ProductItem> =
        object : AddFoodEntryStore<ProductItem>,
            Store<Intent, State<ProductItem>, Label> by storeFactory.create(
                name = "AddFoodEntryStoreProvider",
                initialState = State(),
                bootstrapper = BootstrapperImpl(),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl,
            ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<Intent, Action, State<ProductItem>, Message<ProductItem>, Label>() {

        override fun executeAction(action: Action, getState: () -> State<ProductItem>) =
            when (action) {
                Action.Init -> dispatch(Message.ProductList(foodRepository.getProducts()))
            }
    }

    private object ReducerImpl : Reducer<State<ProductItem>, Message<ProductItem>> {
        override fun State<ProductItem>.reduce(msg: Message<ProductItem>): State<ProductItem> =
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