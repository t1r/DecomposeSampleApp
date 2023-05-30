package org.sample.logic.food.integrationproduct

import org.sample.logic.food.AddFoodEntryComponent.Event
import org.sample.logic.food.AddFoodEntryStore

internal val labelToEvent: AddFoodEntryStore.Label.() -> Event = {
    when (this) {
        else -> TODO()
    }
}
