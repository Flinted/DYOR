package makes.flint.doppel.doppelbuilder.configuration

import android.view.View
import makes.flint.doppel.backgroundproviders.DoppelBackgroundProvider

/**
 * DoppelConfiguration
 */
class DoppelConfiguration internal constructor(provider: DoppelBackgroundProvider) : DoppelConfigurable {

    override var depth = 10
    override var parentViewInclusive = true
    override var backgroundProvider = provider

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
}
