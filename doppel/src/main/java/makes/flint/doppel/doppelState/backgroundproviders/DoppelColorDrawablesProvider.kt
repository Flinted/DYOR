package makes.flint.doppel.doppelState.backgroundproviders

import android.graphics.drawable.Drawable
import android.view.View
import makes.flint.doppel.doppelState.backgroundproviders.backgroundconvenience.DoppelColors
import makes.flint.doppel.doppelState.backgroundproviders.drawables.DoppelColorDrawable

/**
 * DoppelColorDrawablesProvider
 */
class DoppelColorDrawablesProvider(private val colors: List<Int> = DoppelColors.GRAYS()) : DoppelBackgroundProvider {

    private var animationSpeed: Long = 1000
    private var minAlpha: Float = 0.3f
    private var maxAlpha: Float = 1f
    private var radius: Float = 0f

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

    fun setAnimationSpeed(speedMs: Long) {
        animationSpeed = speedMs
    }

    fun setMinAlpha(alpha: Float) {
        minAlpha = alpha
    }

    fun setMaxAlpha(alpha: Float) {
        maxAlpha = alpha
    }

    fun setCornerRadius(radiusPixels: Float) {
        radius = radiusPixels
    }
}