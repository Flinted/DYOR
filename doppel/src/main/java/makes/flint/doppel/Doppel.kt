package makes.flint.doppel

import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import makes.flint.doppel.configuration.DoppelExclusion
import makes.flint.doppel.doppelState.state.ViewState
import makes.flint.doppel.doppelState.state.ViewStateFactory
import makes.flint.doppel.doppelState.state.overridedimensions.DoppelOverride
import makes.flint.doppel.doppelbuilder.configuration.DoppelConfigurable
import java.lang.ref.WeakReference

/**
 * Doppel
 */
class Doppel(private val configuration: DoppelConfigurable, vararg parents: View) {

    var active = false
        private set

    private var views: MutableMap<View, ViewState<*>> = mutableMapOf()
    private var parentViews: List<WeakReference<View>> = listOf()

    init {
        parentViews = parents.map { WeakReference(it) }
        processParentViews()
    }

    fun on() {
        if (active) {
            return
        }
        active = true
        views.values.forEach { state ->
            state.doppel()
        }
        parentViews.forEach { viewWrapper ->
            val view = viewWrapper.get() ?: return@forEach
            view.parent.recomputeViewAttributes(view)
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

    fun excludeViewsById(vararg exclusions: DoppelExclusion) {
        exclusions.forEach {
            if (it.excludeChildren) {
                removeViewAndChildrenById(it.ids)
                return@forEach
            }
            removeViewsById(*it.ids)
        }
    }

    private fun removeViewsById(vararg ids: Int) {
        ids.forEach { id ->
            val view = findViewById(id)
            views.remove(view)
        }
    }

    private fun removeViewAndChildrenById(ids: IntArray) {
        ids.forEach { id ->
            val view = findViewById(id) ?: return@forEach
            removeViewsRecursively(view)
        }
    }

    private fun removeViewsRecursively(view: View) {
        views.remove(view)
        val viewGroup = view as? ViewGroup ?: return
        for (count in 0 until viewGroup.childCount) {
            removeViewsRecursively(viewGroup.getChildAt(count))
        }
    }

    fun targetViewsById(vararg ids: Int) {
        val targetedMap: MutableMap<View, ViewState<*>> = mutableMapOf()
        ids.forEach { id ->
            val view = findViewById(id) ?: return@forEach
            val state = views[view] ?: return@forEach
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
        override.viewId.forEach { id ->
            addOverrideForId(id, override)
        }
    }

    private fun addOverrideForId(id: Int, override: DoppelOverride) {
        val view = findViewById(id)
        val viewToOverride = views[view] ?: let {
            return
        }
        val height = calculatePixelsFromDp(override.heightDp?.toFloat())
        val width = calculatePixelsFromDp(override.widthDp?.toFloat())
        viewToOverride.setOverrideDimensions(height, width)
    }

    private fun processParentViews() {
        parentViews.forEach { parentHolder ->
            processParentView(parentHolder)
        }
    }

    private fun processParentView(parentHolder: WeakReference<View>) {
        val parent = parentHolder.get() ?: return
        val startingLayer = processParentView(parent)
        byLayer(parent, startingLayer)
    }

    private fun findViewById(id: Int): View? {
        parentViews.forEach { viewWrapper ->
            val view = viewWrapper.get()?.findViewById<View>(id)
            view ?: return@forEach
            return view
        }
        return null
    }

    private fun calculatePixelsFromDp(valueDp: Float?): Int? {
        valueDp ?: return null
        val displayMetrics = parentViews.first().get()?.resources?.displayMetrics ?: return null
        val pixelValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueDp, displayMetrics)
        return pixelValue.toInt()
    }
}