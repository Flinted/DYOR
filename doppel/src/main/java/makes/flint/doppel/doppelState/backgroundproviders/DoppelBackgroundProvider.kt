package makes.flint.doppel.doppelState.backgroundproviders

import android.graphics.drawable.Drawable
import android.view.View

/**
 * DoppelBackgroundProvider
 */
interface DoppelBackgroundProvider {

    fun getBackgroundFor(view: View, layer: Int, depth: Int): Drawable
    fun getColorFor(view: View, layer: Int, depth: Int): Int
}