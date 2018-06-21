package makes.flint.doppel.backgroundproviders

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import makes.flint.doppel.backgroundproviders.drawables.DoppelColorDrawable

/**
 * DoppelColorDrawablesProvider
 */
class DoppelColorDrawablesProvider(private val colors: List<Int>) : DoppelBackgroundProvider {

    private var animationSpeed = 1000L
    private var minAlpha = 0.3f
    private var maxAlpha = 1f
    private var shrinkage = 3
    private var strokeThickness = 0
    private var strokeColor = Color.TRANSPARENT
    private var radius = 0f

    override fun getBackgroundFor(view: View, layer: Int, depth: Int): Drawable {
        val color = getColorFor(view, layer, depth)
        return DoppelColorDrawable(color, animationSpeed, minAlpha, maxAlpha, strokeThickness, strokeColor, shrinkage, radius)
    }

    fun setAnimationSpeed(speedMs: Long) {
        animationSpeed = speedMs
    }

    fun setMinAlpha(alpha: Float) {
        if (alpha < 0f) {
            minAlpha = 0f
            return
        }
        minAlpha = alpha
    }

    fun setMaxAlpha(alpha: Float) {
        if (alpha > 1f) {
            minAlpha = 1f
            return
        }
        maxAlpha = alpha
    }

    fun setCornerRadius(radiusPixels: Float) {
        radius = radiusPixels
    }

    fun setShrinkage(shrinkagePercent: Int) {
        shrinkage = shrinkagePercent
    }

    fun setStroke(thickness: Int, color: Int) {
        strokeThickness = thickness
        strokeColor = color
    }

    override fun getColorFor(view: View, layer: Int, depth: Int): Int {
        var layerAdjusted = layer
        while (layerAdjusted > colors.size) {
            layerAdjusted -= colors.size
        }
        return colors[layer - 1]
    }
}
