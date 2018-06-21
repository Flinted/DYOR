package makes.flint.doppel.backgroundproviders

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import makes.flint.doppel.backgroundproviders.drawables.DoppelColorDrawable

/**
 * DoppelViewTypeColorProvider
 */
class DoppelViewTypeColorProvider(private val defaultColor: Int = Color.GRAY
) : BaseDrawableProvider(), DoppelBackgroundProvider {

    private val defaultDrawable = lazy { DoppelColorDrawable(defaultColor, defaultColor, GradientDrawable.Orientation.TL_BR, animationConfiguration, viewConfiguration) }

    private var map: MutableMap<Class<*>, Int> = mutableMapOf()

    override fun getBackgroundFor(view: View, layer: Int, depth: Int): Drawable {
        val color = map[view.javaClass] ?: return defaultDrawable.value
        return DoppelColorDrawable(color, color, GradientDrawable.Orientation.TL_BR, animationConfiguration, viewConfiguration)
    }

    override fun getColorFor(view: View, layer: Int, depth: Int): Int {
        return map[view.javaClass] ?: return defaultColor
    }

    fun addViewTypeColors(vararg viewPairs: DoppelViewTypeColor) {
        map.clear()
        viewPairs.forEach { viewTypeColor ->
            for (viewType in viewTypeColor.viewTypes) {
                map[viewType] = viewTypeColor.color
            }
        }
    }
}
