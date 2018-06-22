package makes.flint.doppel.backgroundproviders

import android.content.Context
import makes.flint.doppel.DpConverter
import makes.flint.doppel.backgroundproviders.configurations.AnimationConfiguration
import makes.flint.doppel.backgroundproviders.configurations.ViewConfiguration

/**
 * BaseDrawableProvider
 */
abstract class BaseDrawableProvider : DoppelBackgroundProvider {

    internal var animationConfiguration = AnimationConfiguration()
    internal var viewConfiguration = ViewConfiguration()

    fun setAnimationSpeed(speedMs: Long) {
        animationConfiguration.animationSpeed = speedMs
    }

    fun setMinAlpha(alpha: Float) {
        if (alpha < 0f) {
            animationConfiguration.minAlpha = 0f
            return
        }
        animationConfiguration.minAlpha = alpha
    }

    fun setMaxAlpha(alpha: Float) {
        if (alpha > 1f) {
            animationConfiguration.maxAlpha = 1f
            return
        }
        animationConfiguration.maxAlpha = alpha
    }

    fun setCornerRadius(context: Context, radiusDp: Int) {
        viewConfiguration.radius = calculatePixelsForDp(context, radiusDp).toFloat()
    }

    fun setShrinkage(context: Context, shrinkageDp: Int) {
        viewConfiguration.shrinkage = calculatePixelsForDp(context, shrinkageDp)
    }

    fun setStroke(context: Context, thicknessDp: Int, color: Int) {
        viewConfiguration.strokeThickness = calculatePixelsForDp(context, thicknessDp)
        viewConfiguration.strokeColor = color
    }

    private fun calculatePixelsForDp(context: Context, value: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return DpConverter.calculatePixelsFromDp(value, displayMetrics)
    }
}