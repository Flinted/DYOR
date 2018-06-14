package makes.flint.doppel.backgroundproviders.drawables

import android.view.View

/**
 * DoppelAnimatable
 */
interface DoppelAnimatable {
    fun start(view: View)
    fun stop(view: View)
}