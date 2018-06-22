package makes.flint.doppel

import android.view.View
import android.view.ViewGroup
import makes.flint.doppel.doppelState.state.ViewState
import makes.flint.doppel.doppelState.state.ViewStateFactory
import makes.flint.doppel.doppelbuilder.configuration.DoppelConfigurable
import java.lang.ref.WeakReference

/**
 * ViewProcessor
 */
internal class ViewProcessor(private val configuration: DoppelConfigurable) {

    private val viewMap: MutableMap<View, ViewState<*>> = mutableMapOf()

    internal fun process(parentViews: List<WeakReference<View>>): MutableMap<View, ViewState<*>> {
        parentViews.forEach { parentHolder ->
            processParentView(parentHolder)
        }
        return viewMap
    }

    private fun processParentView(parentHolder: WeakReference<View>) {
        val parent = parentHolder.get() ?: return
        addParentView(parent)
        val startingLayer = if (configuration.parentViewInclusive) 2 else 1
        byLayer(parent, startingLayer)
    }

    private fun byLayer(view: View, layer: Int) {
        if (view !is ViewGroup) {
            return
        }
        val layerViewsCount = view.childCount
        for (i in 0 until layerViewsCount) {
            val child = view.getChildAt(i)
            processChildView(child, layer)
        }
    }

    private fun processChildView(child: View, layer: Int) {
        addView(child, layer)
        byLayer(child, layer + 1)
    }

    private fun addParentView(parent: View) {
        if (!configuration.parentViewInclusive) {
        }
        addView(parent, 1)
    }

    private fun addView(view: View, layer: Int) {
        if (!configuration.validate(view)) {
            return
        }
        val state = ViewStateFactory.makeStateFor(view, configuration, layer)
        this.viewMap[view] = state
    }
}
