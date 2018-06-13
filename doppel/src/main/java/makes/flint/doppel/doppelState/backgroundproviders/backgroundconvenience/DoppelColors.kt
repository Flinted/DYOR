package makes.flint.doppel.doppelState.backgroundproviders.backgroundconvenience

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import makes.flint.doppel.R

/**
 * DoppelColors
 */
object DoppelColors {
    fun GRAY() = listOf(Color.GRAY)
    fun BLUE() = listOf(Color.BLUE)
    fun RED() = listOf(Color.RED)
    fun GREEN() = listOf(Color.GREEN)
    fun GRAYS() = listOf(Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.YELLOW)
    fun MULTICOLOR() = listOf(Color.MAGENTA, Color.BLUE, Color.GREEN, Color.RED)
    fun PASTELS(context: Context) = listOf(ContextCompat.getColor(context, R.color.red_doppel))
}