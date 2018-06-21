package makes.flint.doppel.doppelState.state.overridedimensions

/**
 * OverrideDimensions
 */
class OverrideDimensions internal constructor(val originalHeight: Int,
                                              val originalWidth: Int,
                                              private val overrideHeight: Int?,
                                              private val overrideWidth: Int?) {

    internal fun getOverrideStateHeight(): Int {
        return overrideHeight ?: originalHeight
    }

    internal fun getOverrideStateWidth(): Int {
        return overrideWidth ?: originalWidth
    }
}
