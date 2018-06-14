package makes.flint.doppel.backgroundproviders

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * DoppelViewTypeColor
 */
class DoppelViewTypeColor(context: Context, colorId: Int, vararg viewTypes: Class<*>) {
    val viewTypes = viewTypes
    val color = ContextCompat.getColor(context, colorId)
}