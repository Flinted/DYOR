package makes.flint.doppel.backgroundproviders.drawables

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation


/**
 * DoppelColorDrawable
 */
class DoppelColorDrawable(val color: Int,
                          private val animationSpeed: Long,
                          private val minAlpha: Float,
                          private val maxAlpha: Float,
                          shrinkage: Int,
                          radius: Float
) : LayerDrawable(arrayOf(GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, intArrayOf(color, color)))), DoppelAnimatable {

    init {
        (getDrawable(0) as GradientDrawable).cornerRadius = radius
        setLayerInset(0, shrinkage, shrinkage, shrinkage, shrinkage)
    }

    override fun start(view: View) {
        if (animationSpeed <= 0) {
            return
        }
        val fadeAnimation = AlphaAnimation(minAlpha, maxAlpha)
        fadeAnimation.duration = animationSpeed
        fadeAnimation.repeatMode = Animation.REVERSE
        fadeAnimation.repeatCount = Animation.INFINITE
        view.startAnimation(fadeAnimation)
    }

    override fun stop(view: View) {
        view.animation?.cancel()
        view.animation?.reset()
    }
}