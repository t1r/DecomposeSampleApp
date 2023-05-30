package org.sample.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import org.sample.app.main.MainContent
import org.sample.logic.root.RootComponent
import org.sample.logic.root.RootComponent.Child

@Composable
internal fun App(
    component: RootComponent,
) = AppTheme {

    Children(
        stack = component.childStack,
        animation = stackAnimation(fade() + scale()),
        modifier = Modifier.fillMaxSize(),
    ) {
        when (val child = it.instance) {
            is Child.Main -> MainContent(
                foodComponent = child.foodComponent,
                recipeComponent = child.recipeComponent,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

internal expect fun openUrl(url: String?)