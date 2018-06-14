package makes.flint.doppel.doppelState.state

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.TextView
import makes.flint.doppel.backgroundproviders.drawables.DoppelAnimatable

/**
 * TextViewState
 */
class TextViewState(view: TextView,
                    private val background: Drawable) : BaseViewState<TextView>(view), ViewState<TextView> {

    private val originalBackground = view.background
    private val originalTextColor = view.textColors
    private val originalHintColor = view.hintTextColors

    override fun doppel() {
        super.doppel()
        val view = view.get() ?: return
        view.background = background
        view.setTextColor(Color.TRANSPARENT)
        view.setHintTextColor(Color.TRANSPARENT)
        (view.background as? DoppelAnimatable)?.start(view)
    }

    override fun restore() {
        super.restore()
        val view = view.get() ?: return
        (view.background as? DoppelAnimatable)?.stop(view)
        view.background = originalBackground
        view.setTextColor(originalTextColor)
        view.setHintTextColor(originalHintColor)
        view.isEnabled = originallyEnabled
    }
}
