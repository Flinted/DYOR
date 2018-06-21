package makes.flint.doppel.doppelbuilder.configuration

import android.view.View
import makes.flint.doppel.backgroundproviders.DoppelBackgroundProvider

/**
 * DoppelConfigurable
 */
interface DoppelConfigurable {

    var depth: Int
    var parentViewInclusive: Boolean
    var backgroundProvider: DoppelBackgroundProvider

    fun validate(view: View): Boolean
}
