package makes.flint.doppel.backgroundproviders.drawables

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import makes.flint.doppel.backgroundproviders.AnimationConfiguration
import makes.flint.doppel.backgroundproviders.ViewConfiguration


/**
 * DoppelColorDrawable
 */
internal class DoppelColorDrawable(startColor: Int,
                                   endColor: Int,
                                   orientation: GradientDrawable.Orientation,
                                   private val animationConfiguration: AnimationConfiguration,
                                   viewConfiguration: ViewConfiguration
) : LayerDrawable(arrayOf(GradientDrawable(orientation, intArrayOf(startColor, endColor)))), DoppelAnimatable {

    init {
        (getDrawable(0) as GradientDrawable).cornerRadius = viewConfiguration.radius
        (getDrawable(0) as GradientDrawable).setStroke(viewConfiguration.strokeThickness, viewConfiguration.strokeColor)
        val shrinkage = viewConfiguration.shrinkage
        setLayerInset(0, shrinkage, shrinkage, shrinkage, shrinkage)
        invalidateSelf()
    }

    override fun start(view: View) {
        if (animationConfiguration.animationSpeed <= 0) {
            return
        }
        val fadeAnimation = AlphaAnimation(animationConfiguration.minAlpha, animationConfiguration.maxAlpha)
        fadeAnimation.duration = animationConfiguration.animationSpeed
        fadeAnimation.repeatMode = Animation.REVERSE
        fadeAnimation.repeatCount = Animation.INFINITE
        view.startAnimation(fadeAnimation)
    }

    override fun stop(view: View) {
        view.animation?.cancel()
        view.animation?.reset()
    }
}