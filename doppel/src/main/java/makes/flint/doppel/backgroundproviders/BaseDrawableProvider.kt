package makes.flint.doppel.backgroundproviders

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

    fun setCornerRadius(radiusPixels: Float) {
        viewConfiguration.radius = radiusPixels
    }

    fun setShrinkage(shrinkagePercent: Int) {
        viewConfiguration.shrinkage = shrinkagePercent
    }

    fun setStroke(thickness: Int, color: Int) {
        viewConfiguration.strokeThickness = thickness
        viewConfiguration.strokeColor = color
    }
}