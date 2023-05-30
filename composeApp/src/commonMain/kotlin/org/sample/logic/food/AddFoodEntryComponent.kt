package org.sample.logic.food

import kotlinx.coroutines.flow.Flow

interface AddFoodEntryComponent<T> {
    val models: Flow<Model<T>>
    val events: Flow<Event>

    data class Model<T>(
        val itemList: List<T>? = null,
    )

    sealed class Event
}