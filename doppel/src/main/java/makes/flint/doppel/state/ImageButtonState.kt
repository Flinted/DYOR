package makes.flint.doppel.doppelState.state

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageButton
import makes.flint.doppel.doppelState.backgroundproviders.drawables.DoppelAnimatable

/**
 * ImageButtonState
 */
class ImageButtonState(view: ImageButton,
                       val background: Drawable,
                       val color: Int) : BaseViewState<ImageButton>(view), ViewState<ImageButton> {

    private val originalBackground = view.background
    private val originalImage = view.drawable
    private val originalTint = view.backgroundTintList

    override fun doppel() {
        super.doppel()
        val view = view.get() ?: return
        view.background = background
        view.backgroundTintList = ColorStateList.valueOf(color)
        view.setImageDrawable(ColorDrawable(Color.TRANSPARENT))
        (view.background as? DoppelAnimatable)?.start(view)
    }

    override fun restore() {
        super.restore()
        val view = view.get() ?: return
        view.background = originalBackground
        view.backgroundTintList = originalTint
        view.setImageDrawable(originalImage)
        (view.background as? DoppelAnimatable)?.stop(view)
    }
}