package makes.flint.doppel.doppelState.state

import android.view.View
import java.lang.ref.WeakReference

/**
 * HideViewState
 */
class HideViewState(view: View) : ViewState<View> {

    override var view = WeakReference(view)
    override val originallyEnabled = view.isEnabled
    private val originalVisibility = view.visibility

    override fun doppel() {
        if (originalVisibility == View.GONE) {
            return
        }
        val view = view.get() ?: return
        view.visibility = View.INVISIBLE
    }

    override fun restore() {
        val view = view.get() ?: return
        view.visibility = originalVisibility
    }

    override fun setOverrideDimensions(height: Int?, width: Int?) {
        return
    }
}
