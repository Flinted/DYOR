package makes.flint.doppel.doppelState.doppelbuilder.configuration

import android.content.Context
import android.view.View
import makes.flint.doppel.doppelState.backgroundproviders.DoppelBackgroundProvider
import makes.flint.doppel.doppelState.backgroundproviders.DoppelColorDrawablesProvider

/**
 * DoppelConfiguration
 */
class DoppelConfiguration internal constructor(context:Context) : DoppelConfigurable {

    override var depth = 8
    override var parentViewInclusive = true
    internal var backgroundProvider: DoppelBackgroundProvider = DoppelColorDrawablesProvider(context)

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
