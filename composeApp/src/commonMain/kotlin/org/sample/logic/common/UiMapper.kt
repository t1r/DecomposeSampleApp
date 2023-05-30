package org.sample.logic.common

import org.sample.logic.food.AddFoodEntryComponent
import org.sample.logic.food.AddFoodEntryStore

internal fun <T, R> stateToModel(
    state: AddFoodEntryStore.State<T>,
    mapItemAction: (AddFoodEntryStore.State<T>) -> List<R>,
): AddFoodEntryComponent.Model<R> {
    return AddFoodEntryComponent.Model(
        itemList = mapItemAction(state),
    )
}