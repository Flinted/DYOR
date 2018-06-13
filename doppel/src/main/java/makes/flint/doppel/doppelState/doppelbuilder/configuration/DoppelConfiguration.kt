package makes.flint.doppel.doppelState.doppelbuilder.configuration

import android.view.View
import makes.flint.doppel.doppelState.backgroundproviders.DoppelBackgroundProvider
import makes.flint.doppel.doppelState.backgroundproviders.DoppelColorDrawablesProvider
import makes.flint.doppel.doppelState.backgroundproviders.backgroundconvenience.DoppelColors

/**
 * DoppelConfiguration
 */
class DoppelConfiguration internal constructor() : DoppelConfigurable {

    override var depth = 8
    override var parentViewInclusive = true
    internal var backgroundProvider: DoppelBackgroundProvider = DoppelColorDrawablesProvider(DoppelColors.GRAYS())

    internal var viewTypeList: List<Class<*>> = listOf()
    internal var targeting: Boolean? = null

    override fun validate(view: View): Boolean {
        val targeted = targeting ?: return true
        val presentInList = viewTypeList.contains(view::class.java)
        if (targeted) {
            return presentInList
        }
        return !presentInList
    }

    override fun getBackgroundFor(view: View, layer: Int) = backgroundProvider.getBackgroundFor(view, layer, depth)
}
