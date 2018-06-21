package makes.flint.doppel.backgroundproviders

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import makes.flint.doppel.backgroundproviders.drawables.DoppelColorDrawable

/**
 * DoppelGradientDrawableProvider
 */
class DoppelGradientDrawableProvider(private val colors: List<Pair<Int, Int>>, private val orientation: GradientDrawable.Orientation) : BaseDrawableProvider(), DoppelBackgroundProvider {

    override fun getBackgroundFor(view: View, layer: Int, depth: Int): Drawable {
        val colorPair = getColorPairFor(view, layer, depth)
        return DoppelColorDrawable(colorPair.first, colorPair.second, orientation, animationConfiguration, viewConfiguration)
    }

    private fun getColorPairFor(view: View, layer: Int, depth: Int): Pair<Int, Int> {
        var layerAdjusted = layer
        while (layerAdjusted > colors.size) {
            layerAdjusted -= colors.size
        }
        return colors[layer - 1]
    }

    override fun getColorFor(view: View, layer: Int, depth: Int): Int {
        return getColorPairFor(view, layer, depth).first
    }
}
