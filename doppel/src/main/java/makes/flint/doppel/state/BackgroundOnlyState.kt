package makes.flint.doppel.doppelState.state

import android.graphics.drawable.Drawable
import android.view.View
import makes.flint.doppel.backgroundproviders.drawables.DoppelAnimatable

/**
 * BackgroundOnlyState
 */
class BackgroundOnlyState(view: View,
                          private val background: Drawable
) :BaseViewState<View>(view), ViewState<View> {

    private val originalBackground = view.background

    override fun doppel() {
        super.doppel()
        originalBackground ?: return
        val view = view.get() ?: return
        view.background = background
        (view.background as? DoppelAnimatable)?.start(view)
    }

    override fun restore() {
        super.doppel()
        originalBackground ?: return
        val view = view.get() ?: return
        (view.background as? DoppelAnimatable)?.stop(view)
        view.background = originalBackground
    }
}
