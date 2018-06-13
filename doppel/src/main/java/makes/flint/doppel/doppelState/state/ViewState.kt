package makes.flint.doppel.doppelState.state

import android.view.View
import java.lang.ref.WeakReference

/**
 * ViewState
 */
interface ViewState<T : View> {
    var view: WeakReference<T>
    val originallyEnabled: Boolean

    fun doppel()
    fun restore()
    fun setOverrideDimensions(height: Int?, width: Int?)
}