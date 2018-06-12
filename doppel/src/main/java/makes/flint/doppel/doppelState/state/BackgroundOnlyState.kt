package makes.flint.doppel.doppelState.state

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import makes.flint.doppel.doppelState.backgroundproviders.drawables.DoppelAnimatable
import makes.flint.doppel.doppelState.state.overridedimensions.OverrideDimensions
import java.lang.ref.WeakReference

/**
 * BackgroundOnlyState
 */
class BackgroundOnlyState(view: View,
                          val background: Drawable
) : ViewState<View> {

    override val view = WeakReference(view)
    override val originallyEnabled = view.isEnabled
    private val originalBackground = view.background
    private var overrideDimensions: OverrideDimensions? = null

    override fun doppel(context: Context) {
        originalBackground ?: return
        val view = view.get() ?: return
        view.background = background
        (view.background as? DoppelAnimatable)?.start(view)
        view.isEnabled = false
        overrideDimensions ?: return
    }

    override fun restore(context: Context) {
        originalBackground ?: return
        val view = view.get() ?: return
        (view.background as? DoppelAnimatable)?.stop(view)
        view.background = originalBackground
        view.isEnabled = originallyEnabled
        overrideDimensions ?: return
    }

    override fun setOverrideDimensions(height: Int, width: Int) {
    }
}