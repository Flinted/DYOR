package makes.flint.doppel.doppelbuilder

import android.content.Context
import android.view.View
import makes.flint.doppel.backgroundproviders.DoppelBackgroundProvider
import makes.flint.doppel.backgroundproviders.DoppelColorDrawablesProvider
import makes.flint.doppel.backgroundproviders.backgroundconvenience.DoppelColors
import makes.flint.doppel.doppelbuilder.configuration.DoppelConfiguration

/**
 * DoppelConfigurationBuilder
 */
class DoppelConfigurationBuilder(context: Context) {

    private val configuration = DoppelConfiguration(DoppelColorDrawablesProvider(DoppelColors.GRAYS(context)))

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
