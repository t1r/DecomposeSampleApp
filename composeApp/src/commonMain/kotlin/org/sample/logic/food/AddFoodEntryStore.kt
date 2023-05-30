package org.sample.logic.food

import com.arkivanov.mvikotlin.core.store.Store
import org.sample.logic.food.AddFoodEntryStore.Intent
import org.sample.logic.food.AddFoodEntryStore.Label
import org.sample.logic.food.AddFoodEntryStore.State

interface AddFoodEntryStore<T> : Store<Intent, State<T>, Label> {

    data class State<T>(
        val productList: List<T>? = null,
    )

    sealed class Action {
        object Init : Action()
    }

    sealed class Intent

    sealed class Message<T> {
        data class ProductList<T>(val productList: List<T>?) : Message<T>()
    }

    sealed class Label
}