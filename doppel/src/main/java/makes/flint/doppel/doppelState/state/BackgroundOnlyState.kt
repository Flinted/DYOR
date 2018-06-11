package makes.flint.doppel.doppelState.state

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import makes.flint.doppel.doppelState.doppelbuilder.DoppelAnimatable

/**
 * BackgroundOnlyState
 */
class BackgroundOnlyState(override val view: View,
                          val background: Drawable
) : ViewState<View> {

    private val originalBackground = view.background
    override val originallyEnabled = view.isEnabled
    private var overrideDimensions: OverrideDimensions? = null

    override fun doppel(context: Context) {
        originalBackground ?: return
        view.background = background
        (view.background as? DoppelAnimatable)?.start(view)
        view.isEnabled = false
        overrideDimensions ?: return
    }

    override fun restore(context: Context) {
        originalBackground ?: return
        (view.background as? DoppelAnimatable)?.stop(view)
        view.background = originalBackground
        view.isEnabled = originallyEnabled
        overrideDimensions ?: return
    }

    override fun setOverrideDimensions(height: Int, width: Int) {
    }
}