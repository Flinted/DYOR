package makes.flint.doppel.doppelState.state

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import makes.flint.doppel.doppelState.doppelbuilder.DoppelAnimatable

/**
 * ImageViewState
 */
class ImageViewState(override val view: ImageView,
                     val background: Drawable) : ViewState<ImageView> {

    override val originallyEnabled = view.isEnabled
    private val originalBackground = view.background
    private val originalImage = view.drawable
    private var overrideDimensions: OverrideDimensions? = null

    override fun doppel(context: Context) {
        view.background = background
        view.setImageDrawable(ColorDrawable(Color.TRANSPARENT))
        (view.background as? DoppelAnimatable)?.start(view)
        view.isEnabled = false
        overrideDimensions ?: return
    }

    override fun restore(context: Context) {
        (view.background as? DoppelAnimatable)?.stop(view)
        view.background = originalBackground
        view.setImageDrawable(originalImage)
        view.isEnabled = originallyEnabled
        overrideDimensions ?: return
    }

    override fun setOverrideDimensions(height: Int, width: Int) {
    }
}