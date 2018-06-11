package makes.flint.doppel.doppelState

import android.content.Context
import android.view.View
import android.view.ViewGroup
import makes.flint.doppel.doppelState.doppelbuilder.DoppelConfiguration
import makes.flint.doppel.doppelState.state.ViewState
import makes.flint.doppel.doppelState.state.ViewStateFactory

/**
 * Doppel
 */
class Doppel(parent: ViewGroup, private val configuration: DoppelConfiguration = DoppelConfiguration()) {

    private var active = false
    private var views: MutableMap<View, ViewState<*>> = mutableMapOf()
    private var parentView: ViewGroup? = null

    init {
        this.parentView = parent
        if (configuration.parentViewInclusive) {
            addView(parent, 1)
        }
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
        if (layer >= configuration.depth) {
            return
        }
        byLayer(child, layer + 1)
    }

    private fun addView(view: View, layer: Int) {
        if (!configuration.validate(view)) {
            return
        }
        val state = ViewStateFactory.makeStateFor(view, configuration, layer)
        this.views[view] = state
    }

    fun excludeViewsById(vararg ids: Int) {
        ids.forEach { id ->
            val view = parentView?.findViewById<View>(id)
            views.remove(view)
        }
    }

    fun targetViewsById(vararg ids: Int) {
        val targetedMap: MutableMap<View, ViewState<*>> = mutableMapOf()
        for (id in ids) {
            val view = parentView?.findViewById<View>(id) ?: continue
            val state = views[view] ?: continue
            targetedMap[view] = state
        }
        views = targetedMap
    }

    fun addOverride(viewId: Int, height: Int, width: Int) {
//        val viewToOverride = views[viewId] ?: let {
//            //VIEW NOT ADDED YET ERROR!
//            return
//        }
//        viewToOverride.setOverrideDimensions(height, width)
    }

    fun on(context: Context) {
        if (isActive()) {
            return
        }
        active = true
        views.values.forEach { state ->
            state.doppel(context)
        }
    }

    fun off(context: Context) {
        if (!isActive()) {
            return
        }
        active = false
        views.values.forEach { state ->
            state.restore(context)
        }
    }

    fun isActive(): Boolean {
        return active
    }
}