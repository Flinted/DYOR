package makes.flint.dyor.deck.ui

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import makes.flint.doppel.Doppel
import makes.flint.doppel.backgroundproviders.DoppelColorDrawablesProvider
import makes.flint.doppel.backgroundproviders.DoppelViewTypeColor
import makes.flint.doppel.backgroundproviders.DoppelViewTypeColorProvider
import makes.flint.doppel.doppelState.state.overridedimensions.DoppelOverride
import makes.flint.doppel.doppelbuilder.DoppelConfigurationBuilder
import makes.flint.dyor.R

/**
 * DoppelExample
 */
class DoppelExample : Activity() {

    private val view1 = layoutInflater.inflate(R.layout.activity_deck, null)
    private val view2 = layoutInflater.inflate(R.layout.deck_item, null)
    private val injectedConfiguration = DoppelConfigurationBuilder(this).build()

    // This makes a default instance of Doppel for convenience. This has default settings for colours,
    // targeting, animation etc.
    private fun doppelBasic() {
        val doppel = Doppel(injectedConfiguration, view1)
        doppel.on()
    }

    // You can pass any number of views to Doppel.
    private fun doppelMultipleViews() {
        val doppel = Doppel(injectedConfiguration, view1, view2)
        doppel.on()
    }

    // You can override default heights and widths for any number of views when in Doppel state. If
    // override is null, the views current dimensions are used.
    private fun doppelInstanceCustomisation() {
        val override1 = DoppelOverride(25, 25, R.id.last_order, R.id.last_order_date)
        val override2 = DoppelOverride(null, 70, R.id.test_name_input)

        val doppel = Doppel(injectedConfiguration, view1, view2)
        doppel.addOverrides(override1, override2)
        doppel.excludeViewsById(R.id.test_image_profile, R.id.test_image_overlay)
        doppel.on()

    }

    // You can set various things on custome configuration.
    // .toDepth() :  Set how many layers deep doppel goes before hiding remaining child views.
    // .parentViewInclusive() :  Say whether the top level view being passed in should be included in the Doppel.
    // .targetSpecificViewTypes() :  Targets specific views by type, ignores all others.
    // .excludeSpecificViewTypes() :  Excludes specific view by Id, targets all others.
    private fun doppelWithCustomConfiguration() {
        val doppelConfiguration = DoppelConfigurationBuilder(this)
                .toDepth(3)
                .parentViewInclusive(true)
                .targetSpecificViewTypes(AppCompatImageView::class.java, AppCompatTextView::class.java)
                .build()

        val doppel = Doppel(doppelConfiguration, view1)
        doppel.on()
    }

    // Provide a different Background Provider.  Doppel includes two as standard. One that takes a
    // list of colours and chooses background by layer.  Another that allows you to set colours by view type.
    // You can adjust Animation Speed, MinAlpha, MaxAlpha and CornerRadius for both providers.
    // This provider is then set on a default, or customised DoppelConfigurable.
    private fun doppelWithCustomBackgroundProvider() {
        val colors = resources.getIntArray(R.array.mixed_doppel).toList()

        val backgroundProvider = DoppelColorDrawablesProvider(this, colors)
        backgroundProvider.setAnimationSpeed(500)
        backgroundProvider.setMinAlpha(0.4f)
        backgroundProvider.setMaxAlpha(0.9f)
        backgroundProvider.setCornerRadius(30f)

        val doppelConfiguration = DoppelConfigurationBuilder(this)
                .withBackgroundProvider(backgroundProvider)
                .build()

        val doppel = Doppel(doppelConfiguration, view1)
        doppel.on()
    }

    // This is the secondary build in view provider.
    // You can set colours for multiple view types, and have to provide a default for other views.
    private fun doppelWithSecondCustomBackgroundProvider() {
        val defaultColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        val textViewColorHolder = DoppelViewTypeColor(this, R.color.colorAccent, AppCompatTextView::class.java, AppCompatEditText::class.java)
        val imageViewColorHolder = DoppelViewTypeColor(this, R.color.colorPrimary, AppCompatImageView::class.java)

        val backgroundProvider = DoppelViewTypeColorProvider(defaultColor)
        backgroundProvider.setAnimationSpeed(500)
        backgroundProvider.setMinAlpha(0.4f)
        backgroundProvider.setMaxAlpha(0.9f)
        backgroundProvider.setCornerRadius(30f)
        backgroundProvider.addViewTypeColors(textViewColorHolder, imageViewColorHolder)

        val doppelConfiguration = DoppelConfigurationBuilder(this)
                .withBackgroundProvider(backgroundProvider)
                .build()

        val doppel = Doppel(doppelConfiguration, view1)
        doppel.on()
    }
}