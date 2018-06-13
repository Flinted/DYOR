package makes.flint.doppel.doppelState.state

import android.view.View
import makes.flint.doppel.doppelState.state.overridedimensions.OverrideDimensions
import java.lang.ref.WeakReference

/**
 * BaseViewState
 */
abstract class BaseViewState<T : View>(view: T) : ViewState<T> {

    override var view = WeakReference(view)
    override val originallyEnabled = view.isEnabled

    private var overrideDimensions: OverrideDimensions? = null

    override fun doppel() {
        val view = view.get() ?: return
        view.isEnabled = false
        val dimensions = overrideDimensions ?: return
        val params = view.layoutParams
        params.height = dimensions.getOverrideStateHeight()
        params.width = dimensions.getOverrideStateWidth()
        view.layoutParams = params
    }

    override fun restore() {
        val view = view.get() ?: return
        view.isEnabled = originallyEnabled
        val dimensions = overrideDimensions ?: return
        val params = view.layoutParams
        params.height = dimensions.originalHeight
        params.width = dimensions.originalWidth
        view.layoutParams = params
    }

    override fun setOverrideDimensions(height: Int?, width: Int?) {
        val view = view.get() ?: return
        val params = view.layoutParams
        overrideDimensions = OverrideDimensions(params.height, params.width, height, width)
    }
}
