import org.sample.app.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("DecomposeSampleApp") {
            App()
        }
    }
}
