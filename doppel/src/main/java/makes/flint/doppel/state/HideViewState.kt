package makes.flint.doppel.doppelState.state

import android.view.View

/**
 * HideViewState
 */
class HideViewState(view: View) : BaseViewState<View>(view), ViewState<View> {

    private val originalVisibility = view.visibility

    override fun doppel() {
        if (originalVisibility == View.GONE) {
            return
        }
        super.doppel()
        val view = view.get() ?: return
        view.visibility = View.INVISIBLE
    }

    override fun restore() {
        super.doppel()
        val view = view.get() ?: return
        view.visibility = originalVisibility
    }
}
