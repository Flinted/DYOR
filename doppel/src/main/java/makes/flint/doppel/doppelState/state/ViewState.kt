package makes.flint.doppel.doppelState.state

import android.content.Context
import android.view.View

/**
 * ViewState
 */
interface ViewState<T : View> {
    val view: T
    val originallyEnabled: Boolean

    fun doppel(context: Context)
    fun restore(context: Context)
    fun setOverrideDimensions(height: Int, width: Int)
}