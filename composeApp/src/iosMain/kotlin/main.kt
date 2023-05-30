import androidx.compose.ui.window.ComposeUIViewController
import org.sample.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}
