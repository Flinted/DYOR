package makes.flint.doppel.backgroundproviders.backgroundconvenience

import android.content.Context
import makes.flint.doppel.R

/**
 * DoppelColors
 */
object DoppelColors {

    fun GRAYS(context:Context) = context.resources.getIntArray(R.array.grays_doppel).toList()
    fun GRAYS_INVERT(context:Context) = GRAYS(context).reversed()
    fun BLUEGRAYS(context:Context) = context.resources.getIntArray(R.array.bluegrays_doppel).toList()
    fun BLUEGRAYS_INVERT(context:Context) = BLUEGRAYS(context).reversed()
    fun ORANGES(context:Context) = context.resources.getIntArray(R.array.oranges_doppel).toList()
    fun ORANGES_INVERT(context:Context) = ORANGES(context).reversed()
    fun REDS(context:Context) = context.resources.getIntArray(R.array.reds_doppel).toList()
    fun REDS_INVERT(context:Context) = REDS(context).reversed()
    fun BLUES(context:Context) = context.resources.getIntArray(R.array.blues_doppel).toList()
    fun BLUES_INVERT(context:Context) = BLUES(context).reversed()
    fun LIGHT_GREENS(context:Context) = context.resources.getIntArray(R.array.light_greens_doppel).toList()
    fun LIGHT_GREENS_INVERT(context:Context) = LIGHT_GREENS(context).reversed()
    fun YELLOWS(context:Context) = context.resources.getIntArray(R.array.yellows_doppel).toList()
    fun YELLOWS_INVERT(context:Context) = YELLOWS(context).reversed()
    fun PALE_MIXED(context:Context) = context.resources.getIntArray(R.array.mixed_doppel).toList()
    fun PALE_MIXED_INVERT(context:Context) = PALE_MIXED(context).reversed()
}
