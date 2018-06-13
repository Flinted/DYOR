package makes.flint.doppel.doppelState

import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import makes.flint.doppel.doppelState.doppelbuilder.configuration.DoppelConfigurable
import makes.flint.doppel.doppelState.doppelbuilder.configuration.DoppelConfiguration
import makes.flint.doppel.doppelState.state.ViewState
import makes.flint.doppel.doppelState.state.ViewStateFactory
import makes.flint.doppel.doppelState.state.overridedimensions.DoppelOverride

/**
 * Doppel
 */
class Doppel(parent: View, private val configuration: DoppelConfigurable = DoppelConfiguration()) {

    var active = false
        private set

    private var views: MutableMap<View, ViewState<*>> = mutableMapOf()
    private var parentView: View? = null

    init {
        val startingLayer = processParentView(parent)
        byLayer(parent, startingLayer)
    }

    fun on() {
        if (active) {
            return
        }
        active = true
        views.values.forEach { state ->
            state.doppel()
        }
    }

    fun off() {
        if (!active) {
            return
        }
        active = false
        views.values.forEach { state ->
            state.restore()
        }
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

    fun addOverrides(vararg overrides: DoppelOverride) {
        overrides.forEach {
            addOverride(it)
        }
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

    private fun processParentView(parent: View): Int {
        this.parentView = parent
        if (!configuration.parentViewInclusive) {
            return 1
        }
        addView(parent, 1)
        return 2
    }

    private fun processChildView(child: View, layer: Int) {
        addView(child, layer)
        byLayer(child, layer + 1)
    }

    private fun addView(view: View, layer: Int) {
        if (!configuration.validate(view)) {
            return
        }
        val state = ViewStateFactory.makeStateFor(view, configuration, layer)
        this.views[view] = state
    }

    private fun addOverride(override: DoppelOverride) {
        val view = parentView?.findViewById<View>(override.viewId)
        val viewToOverride = views[view] ?: let {
            //VIEW NOT ADDED ERROR!
            return
        }
        val height = calculatePixelsFromDp(override.heightDp?.toFloat())
        val width = calculatePixelsFromDp(override.widthDp?.toFloat())
        viewToOverride.setOverrideDimensions(height, width)
    }

    private fun calculatePixelsFromDp(valueDp: Float?): Int? {
        valueDp ?: return null
        val displayMetrics = parentView?.resources?.displayMetrics ?: return null
        val pixelValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueDp, displayMetrics)
        return pixelValue.toInt()
    }
}