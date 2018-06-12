package makes.flint.doppel.doppelState.state

import android.content.Context
import android.view.View
import java.lang.ref.WeakReference

/**
 * HideViewState
 */
class HideViewState(view: View) : ViewState<View> {

    override val view = WeakReference(view)
    override val originallyEnabled = view.isEnabled
    private val originalVisibility = view.visibility

    override fun doppel(context: Context) {
        if (originalVisibility == View.GONE) {
            return
        }
        val view = view.get() ?: return
        view.visibility = View.INVISIBLE
    }

    override fun restore(context: Context) {
        val view = view.get() ?: return
        view.visibility = originalVisibility
    }

    override fun setOverrideDimensions(height: Int, width: Int) {
    }
}