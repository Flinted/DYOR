package makes.flint.doppel.doppelState.state

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import makes.flint.doppel.doppelState.doppelbuilder.configuration.DoppelConfigurable

/**
 * ViewStateFactory
 */
object ViewStateFactory {

    fun makeStateFor(view: View, configuration: DoppelConfigurable, layer: Int): ViewState<*> {
        val background = configuration.getBackgroundFor(view, layer)
        val color = configuration.getColorFor(view, layer)
        return when {
            layer > configuration.depth -> HideViewState(view)
            view is ImageButton -> ImageButtonState(view, background, color)
            view is TextView -> TextViewState(view, background)
            view is ImageView -> ImageViewState(view, background)
            else -> BackgroundOnlyState(view, background)
        }
    }
}
