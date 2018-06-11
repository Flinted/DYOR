package makes.flint.doppel.doppelState.doppelbuilder

import android.view.View
import makes.flint.doppel.doppelState.backgroundproviders.DoppelBackgroundProvider
import makes.flint.doppel.doppelState.backgroundproviders.DoppelColorDrawablesProvider

/**
 * DoppelConfiguration
 */
class DoppelConfiguration internal constructor() {

    internal var backgroundProvider: DoppelBackgroundProvider = DoppelColorDrawablesProvider(DoppelColorDrawablesProvider.GRAYS)
    internal var depth: Int = 5
    internal var parentViewInclusive = true
    var viewTypeList: List<Class<*>> = listOf()
    var targeting: Boolean? = null

    fun validate(view: View): Boolean {
        val targeted = targeting ?: return true
        val presentInList = viewTypeList.contains(view::class.java)
        if (targeted) {
            return presentInList
        }
        return !presentInList
    }

    fun getBackgroundFor(view: View, layer: Int) = backgroundProvider.getBackgroundFor(view, layer, depth)
}
