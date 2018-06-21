package makes.flint.doppel.backgroundproviders

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import makes.flint.doppel.backgroundproviders.drawables.DoppelColorDrawable

/**
 * DoppelColorDrawablesProvider
 */
class DoppelColorDrawablesProvider(private val colors: List<Int>) : BaseDrawableProvider(), DoppelBackgroundProvider {

    override fun getBackgroundFor(view: View, layer: Int, depth: Int): Drawable {
        val color = getColorFor(view, layer, depth)
        return DoppelColorDrawable(color, color, GradientDrawable.Orientation.TL_BR, animationConfiguration, viewConfiguration)
    }

    override fun getColorFor(view: View, layer: Int, depth: Int): Int {
        var layerAdjusted = layer
        while (layerAdjusted > colors.size) {
            layerAdjusted -= colors.size
        }
        return colors[layer - 1]
    }
}
