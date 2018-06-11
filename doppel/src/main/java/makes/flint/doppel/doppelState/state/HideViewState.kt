package makes.flint.doppel.doppelState.state

import android.content.Context
import android.view.View

/**
 * HideViewState
 */
class HideViewState(override val view: View) : ViewState<View> {

    override val originallyEnabled = view.isEnabled
    private val originalVisibility = view.visibility

    override fun doppel(context: Context) {
        if (originalVisibility == View.GONE) {
            return
        }
        view.visibility = View.INVISIBLE
    }

    override fun restore(context: Context) {
        view.visibility = originalVisibility
    }

    override fun setOverrideDimensions(height: Int, width: Int) {
    }
}