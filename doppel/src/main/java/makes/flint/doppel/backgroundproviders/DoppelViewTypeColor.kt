package makes.flint.doppel.backgroundproviders

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * DoppelViewTypeColor
 */
class DoppelViewTypeColor(context: Context, colorId: Int, vararg val viewTypes: Class<*>) {
    val color = ContextCompat.getColor(context, colorId)
}