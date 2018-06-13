package makes.flint.doppel.doppelState.doppelbuilder

import android.view.View
import makes.flint.doppel.doppelState.backgroundproviders.DoppelBackgroundProvider
import makes.flint.doppel.doppelState.doppelbuilder.configuration.DoppelConfiguration

/**
 * DoppelConfigurationBuilder
 */
class DoppelConfigurationBuilder {

    private val configuration = DoppelConfiguration()

    fun <T : View> targetSpecificViewTypes(vararg viewTypes: Class<out T>): DoppelConfigurationBuilder {
        configuration.viewTypeList = viewTypes.toList()
        configuration.targeting = true
        return this
    }

    fun <T : View> excludeSpecificViewTypes(vararg viewTypes: Class<out T>): DoppelConfigurationBuilder {
        configuration.viewTypeList = viewTypes.toList()
        configuration.targeting = false
        return this
    }

    fun withBackgroundProvider(backgroundProvider: DoppelBackgroundProvider): DoppelConfigurationBuilder {
        configuration.backgroundProvider = backgroundProvider
        return this
    }

    fun toDepth(depth: Int): DoppelConfigurationBuilder {
        configuration.depth = depth
        return this
    }

    fun parentViewInclusive(inclusive: Boolean): DoppelConfigurationBuilder {
        configuration.parentViewInclusive = inclusive
        return this
    }

    fun build(): DoppelConfiguration {
        return configuration
    }
}
