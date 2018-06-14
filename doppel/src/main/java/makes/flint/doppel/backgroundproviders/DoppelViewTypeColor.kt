package makes.flint.doppel.doppelState.backgroundproviders

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View

/**
 * DoppelViewTypeColor
 */
class DoppelViewTypeColor<T : View>(context: Context, colorId: Int, val viewType: Class<T>) {
    val color = ContextCompat.getColor(context, colorId)
}