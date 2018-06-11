package makes.flint.doppel.doppelState.state

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import makes.flint.doppel.doppelState.doppelbuilder.DoppelConfiguration

/**
 * ViewStateFactory
 */
object ViewStateFactory {

    fun makeStateFor(view: View, configuration: DoppelConfiguration, layer: Int): ViewState<*> {
        val background = configuration.getBackgroundFor(view, layer)
        return when {
            layer > configuration.depth -> HideViewState(view)
            view is TextView -> TextViewState(view, background)
            view is ImageView -> ImageViewState(view, background)
            else -> BackgroundOnlyState(view, background)
        }
    }
}