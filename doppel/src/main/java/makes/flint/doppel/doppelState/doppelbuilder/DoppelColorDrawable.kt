package makes.flint.doppel.doppelState.doppelbuilder

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation


/**
 * DoppelColorDrawable
 */
class DoppelColorDrawable(color: Int,
                          private val animationSpeed: Long,
                          private val minAlpha: Float,
                          private val maxAlpha: Float,
                          radius: Float
) : GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, intArrayOf(color, color)), DoppelAnimatable {

    init {
        this.cornerRadius = radius
    }

    override fun start(view: View) {
        if (animationSpeed <= 0) {
            return
        }
        val fadeAnimation = AlphaAnimation(maxAlpha, minAlpha)
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