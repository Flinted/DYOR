package makes.flint.doppel.doppelState.state

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import makes.flint.doppel.doppelbuilder.configuration.DoppelConfigurable

/**
 * ViewStateFactory
 */
object ViewStateFactory {

    fun makeStateFor(view: View, configuration: DoppelConfigurable, layer: Int): ViewState<*> {
        val depth = configuration.depth
        val background = configuration.backgroundProvider.getBackgroundFor(view, layer, depth)
        val color = configuration.backgroundProvider.getColorFor(view, layer, depth)
        return when {
            layer > configuration.depth -> HideViewState(view)
            view is ImageButton -> ImageButtonState(view, background, color)
            view is TextView -> TextViewState(view, background)
            view is ImageView -> ImageViewState(view, background)
            else -> BackgroundOnlyState(view, background)
        }
    }
}
