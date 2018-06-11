package makes.flint.doppel.doppelState.state

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.TextView
import makes.flint.doppel.doppelState.doppelbuilder.DoppelAnimatable

/**
 * TextViewState
 */
class TextViewState(override val view: TextView,
                    val background: Drawable) : ViewState<TextView> {

    private val originalBackground = view.background
    private val originalTextColor = view.textColors
    private val originalHintColor = view.hintTextColors
    override val originallyEnabled = view.isEnabled
    private var overrideDimensions: OverrideDimensions? = null

    override fun doppel(context: Context) {
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
        overrideDimensions = OverrideDimensions(view.measuredHeight, view.measuredWidth, height, width)
    }
}
