package makes.flint.doppel.doppelState.backgroundproviders

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import makes.flint.doppel.doppelState.backgroundproviders.drawables.DoppelColorDrawable

/**
 * DoppelViewTypeColorProvider
 */
class DoppelViewTypeColorProvider(private val defaultColor: Int = Color.GRAY
) : DoppelBackgroundProvider {

    private var animationSpeed: Long = 1000
    private var minAlpha: Float = 0.3f
    private var maxAlpha: Float = 1f
    private var radius: Float = 0f

    private val defaultDrawable = lazy { DoppelColorDrawable(defaultColor, animationSpeed, minAlpha, maxAlpha, radius) }

    private var map: MutableMap<Class<*>, Int> = mutableMapOf()

    override fun getBackgroundFor(view: View, layer: Int, depth: Int): Drawable {
        val color = map[view.javaClass] ?: return defaultDrawable.value
        return DoppelColorDrawable(color, animationSpeed, minAlpha, maxAlpha, radius)
    }

    override fun getColorFor(view: View, layer: Int, depth: Int): Int {
        return map[view.javaClass] ?: return defaultColor
    }

    fun addViewTypeColors(vararg viewPairs: DoppelViewTypeColor<*>) {
        map.clear()
        viewPairs.forEach { viewTypeColor ->
            map[viewTypeColor.viewType] = viewTypeColor.color
        }
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