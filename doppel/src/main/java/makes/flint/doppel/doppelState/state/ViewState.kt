package makes.flint.doppel.doppelState.state

import android.content.Context
import android.view.View
import java.lang.ref.WeakReference

/**
 * ViewState
 */
interface ViewState<T : View> {
    val view: WeakReference<T>
    val originallyEnabled: Boolean

    fun doppel(context: Context)
    fun restore(context: Context)
    fun setOverrideDimensions(height: Int, width: Int)
}