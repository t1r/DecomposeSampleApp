package org.sample.logic.food.integrationrecipe

import org.sample.logic.food.AddFoodEntryComponent.Event
import org.sample.logic.food.AddFoodEntryStore

internal val labelToEvent: AddFoodEntryStore.Label.() -> Event = {
    when (this) {
        else -> TODO()
    }
}
