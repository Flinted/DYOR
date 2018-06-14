package makes.flint.doppel.doppelbuilder.configuration

import android.graphics.drawable.Drawable
import android.view.View

/**
 * DoppelConfigurable
 */
interface DoppelConfigurable {

    var depth: Int
    var parentViewInclusive: Boolean

    fun validate(view: View): Boolean
    fun getBackgroundFor(view: View, layer: Int): Drawable
    fun getColorFor(view: View, layer: Int): Int
}
