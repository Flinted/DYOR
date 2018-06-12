package makes.flint.doppel.doppelState.state

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.TextView
import makes.flint.doppel.doppelState.backgroundproviders.drawables.DoppelAnimatable
import makes.flint.doppel.doppelState.state.overridedimensions.OverrideDimensions
import java.lang.ref.WeakReference

/**
 * TextViewState
 */
class TextViewState(view: TextView,
                    private val background: Drawable) : ViewState<TextView> {
    override val view = WeakReference(view)
    override val originallyEnabled = view.isEnabled
    private val originalBackground = view.background
    private val originalTextColor = view.textColors
    private val originalHintColor = view.hintTextColors
    private var overrideDimensions: OverrideDimensions? = null

    override fun doppel(context: Context) {
        val view = view.get() ?: return
        view.background = background
        view.setTextColor(Color.TRANSPARENT)
        view.setHintTextColor(Color.TRANSPARENT)
        (view.background as? DoppelAnimatable)?.start(view)
        view.isEnabled = false
        overrideDimensions ?: return
        view.height = overrideDimensions!!.overrideHeight ?: overrideDimensions!!.originalHeight
        view.width = overrideDimensions!!.overrideWidth ?: overrideDimensions!!.originalWidth
    }

    override fun restore(context: Context) {
        val view = view.get() ?: return
        (view.background as? DoppelAnimatable)?.stop(view)
        view.background = originalBackground
        view.setTextColor(originalTextColor)
        view.setHintTextColor(originalHintColor)
        view.isEnabled = originallyEnabled
        overrideDimensions ?: return
        view.height = overrideDimensions!!.originalHeight
        view.width = overrideDimensions!!.originalWidth
    }

    override fun setOverrideDimensions(height: Int, width: Int) {
        val view = view.get() ?: return
        overrideDimensions = OverrideDimensions(view.measuredHeight, view.measuredWidth, height, width)
    }
}
