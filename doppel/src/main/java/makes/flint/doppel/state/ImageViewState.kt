package makes.flint.doppel.doppelState.state

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import makes.flint.doppel.backgroundproviders.drawables.DoppelAnimatable

/**
 * ImageViewState
 */
class ImageViewState(view: ImageView,
                     val background: Drawable) : BaseViewState<ImageView>(view), ViewState<ImageView> {

    private val originalBackground = view.background
    private val originalImage = view.drawable

    override fun doppel() {
        super.doppel()
        val view = view.get() ?: return
        view.background = background
        view.setImageDrawable(ColorDrawable(Color.TRANSPARENT))
        (view.background as? DoppelAnimatable)?.start(view)
    }

    override fun restore() {
        super.doppel()
        val view = view.get() ?: return
        (view.background as? DoppelAnimatable)?.stop(view)
        view.background = originalBackground
        view.setImageDrawable(originalImage)
        view.isEnabled = originallyEnabled
    }
}
