package makes.flint.doppel.doppelState.backgroundproviders

import android.graphics.drawable.Drawable
import android.view.View
import makes.flint.doppel.doppelState.backgroundproviders.drawables.DoppelColorDrawable

/**
 * DoppelColorDrawablesProvider
 */
class DoppelColorDrawablesProvider(private val colors: List<Int>,
                                   private val animationSpeed: Long = 1000,
                                   private val minAlpha: Float = 0.3f,
                                   private val maxAlpha: Float = 1f,
                                   private val radius: Float = 0f ) : DoppelBackgroundProvider {

    override fun getBackgroundFor(view: View, layer: Int, depth: Int): Drawable {
        val color = getColorFor(layer, depth)
        return DoppelColorDrawable(color, animationSpeed, minAlpha, maxAlpha, radius)
    }

    private fun getColorFor(layer: Int, depth: Int): Int {
        if (layer > colors.size) {
            return colors.first()
        }
        return colors[layer - 1]
    }
}