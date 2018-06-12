package makes.flint.doppel.doppelState.state

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import makes.flint.doppel.doppelState.backgroundproviders.drawables.DoppelAnimatable
import makes.flint.doppel.doppelState.state.overridedimensions.OverrideDimensions
import java.lang.ref.WeakReference

/**
 * ImageViewState
 */
class ImageViewState(view: ImageView,
                     val background: Drawable) : ViewState<ImageView> {

    override val view = WeakReference(view)
    override val originallyEnabled = view.isEnabled
    private val originalBackground = view.background
    private val originalImage = view.drawable
    private var overrideDimensions: OverrideDimensions? = null

    override fun doppel(context: Context) {
        val view = view.get() ?: return
        view.background = background
        view.setImageDrawable(ColorDrawable(Color.TRANSPARENT))
        (view.background as? DoppelAnimatable)?.start(view)
        view.isEnabled = false
        overrideDimensions ?: return
    }

    override fun restore(context: Context) {
        val view = view.get() ?: return
        (view.background as? DoppelAnimatable)?.stop(view)
        view.background = originalBackground
        view.setImageDrawable(originalImage)
        view.isEnabled = originallyEnabled
        overrideDimensions ?: return
    }

    override fun setOverrideDimensions(height: Int, width: Int) {
    }
}