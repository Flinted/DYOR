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
    private val originalPaddingTop = view.paddingTop
    private val originalPaddingBottom = view.paddingBottom
    private val originalPaddingLeft = view.paddingLeft
    private val originalPaddingRight = view.paddingRight

    private var overrideDimensions: OverrideDimensions? = null

    override fun doppel() {
        val view = view.get() ?: return
        view.isEnabled = false
        val params = view.layoutParams
        overrideDimensions?.let {
            params.height = it.getOverrideStateHeight()
            params.width = it.getOverrideStateWidth()
        }
        view.layoutParams = params
    }

    override fun restore() {
        val view = view.get() ?: return
        view.isEnabled = originallyEnabled
        val params = view.layoutParams
        overrideDimensions?.let {
            params.height = it.originalHeight
            params.width = it.originalWidth
        }
        view.layoutParams = params
    }

    override fun setOverrideDimensions(height: Int?, width: Int?) {
        val view = view.get() ?: return
        val params = view.layoutParams
        overrideDimensions = OverrideDimensions(params.height, params.width, height, width)
    }
}
