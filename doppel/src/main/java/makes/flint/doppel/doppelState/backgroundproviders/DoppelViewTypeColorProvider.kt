package makes.flint.doppel.doppelState.backgroundproviders

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import makes.flint.doppel.doppelState.backgroundproviders.drawables.DoppelColorDrawable

/**
 * DoppelViewTypeColorProvider
 */
class DoppelViewTypeColorProvider(private val animationSpeed: Long = 1000,
                                  private val minAlpha: Float = 0.3f,
                                  private val maxAlpha: Float = 1f,
                                  private val radius: Float = 0f,
                                  defaultColor: Int = Color.GRAY) : DoppelBackgroundProvider {

    private val defaultDrawable = DoppelColorDrawable(defaultColor, animationSpeed, minAlpha, maxAlpha, radius)

    private var map: Map<Class<*>, Int>? = null

    override fun getBackgroundFor(view: View, layer: Int, depth: Int): Drawable {
        map ?: return defaultDrawable
        val color = map?.get(view.javaClass) ?: return defaultDrawable
        return DoppelColorDrawable(color, animationSpeed, minAlpha, maxAlpha, radius)
    }

    fun addViewTypeColours(vararg viewPairs: Pair<Class<*>, Int>) {
        map = viewPairs.toMap()
    }
}