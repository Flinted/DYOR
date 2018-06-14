package makes.flint.doppel.doppelState.state.overridedimensions

/**
 * OverrideDimensions
 */
class OverrideDimensions(val originalHeight: Int,
                         val originalWidth: Int,
                         private val overrideHeight: Int?,
                         private val overrideWidth: Int?) {

    fun getOverrideStateHeight(): Int {
        return overrideHeight ?: originalHeight
    }

    fun getOverrideStateWidth(): Int {
        return overrideWidth ?: originalWidth
    }
}
