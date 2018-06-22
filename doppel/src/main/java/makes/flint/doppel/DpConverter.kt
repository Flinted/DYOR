package makes.flint.doppel

import android.util.DisplayMetrics
import android.util.TypedValue

/**
 * DpConverter
 */
object DpConverter {

    internal fun calculatePixelsFromDp(valueDp: Int, displayMetrics: DisplayMetrics): Int {
        val pixelValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueDp.toFloat(), displayMetrics)
        return pixelValue.toInt()
    }
}