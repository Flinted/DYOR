package makes.flint.dyor.deck.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import makes.flint.doppel.Doppel
import makes.flint.doppel.backgroundproviders.DoppelColorDrawablesProvider
import makes.flint.doppel.backgroundproviders.backgroundconvenience.DoppelColors
import makes.flint.doppel.doppelbuilder.DoppelConfigurationBuilder
import makes.flint.doppel.doppelbuilder.configuration.DoppelConfiguration
import makes.flint.dyor.R
import makes.flint.dyor.base.BaseActivity
import makes.flint.dyor.databinding.ActivityDeckBinding
import makes.flint.dyor.deck.models.DeckViewModel

/**
 * DeckActivity
 */
class DeckActivity : BaseActivity() {

    private lateinit var deckViewModel: DeckViewModel
    private lateinit var doppelSettings: DoppelSettings
    var doppel: Doppel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        deckViewModel = getViewModelComponent().provideDeckViewModel()
        val binding: ActivityDeckBinding = DataBindingUtil.setContentView(this, R.layout.activity_deck)
        doppelSettings = DoppelSettings(this, binding)
        binding.viewModel = deckViewModel
        binding.executePendingBindings()
        setAlphaListeners(binding)
        setNumberListeners(binding)
        setSpinnerListeners(binding)
        binding.toggleButton.setOnClickListener {
            onToggleChanged(binding)
        }
        binding.testImageProfile.setOnClickListener {
            Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onToggleChanged(binding: ActivityDeckBinding) {
        doppel ?: let {
            createDoppelInstanceForSettings()
            doppel?.on()
            return
        }
        doppel?.off()
        doppel = null
    }

    private fun setSpinnerListeners(binding: ActivityDeckBinding) {
        val context = this as Context
        binding.colorSelectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                doppelSettings.setColors(context, binding.colorSelectSpinner.selectedItem.toString())
            }
        }
        binding.typeSelectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                doppelSettings.setType(context, binding.typeSelectSpinner.selectedItem.toString())
            }
        }
        binding.scopeSelectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                doppelSettings.setScope(context, binding.scopeSelectSpinner.selectedItem.toString(), binding)
            }
        }
        binding.strokeSelectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                doppelSettings.setStrokeColor(context, binding.strokeSelectSpinner.selectedItem.toString())
            }

        }
    }

    private fun setNumberListeners(binding: ActivityDeckBinding) {
        binding.radius.minValue = 0
        binding.radius.maxValue = 15
        binding.radius.setFormatter {
            (it * 10).toString()
        }
        binding.shrinkage.minValue = 0
        binding.shrinkage.maxValue = 40

        binding.speed.minValue = 0
        binding.speed.maxValue = 30
        binding.speed.setFormatter {
            (it * 100).toString()
        }
        binding.strokeWidth.minValue = 0
        binding.strokeWidth.maxValue = 20

        binding.radius.setOnValueChangedListener { _, _, new ->
            doppelSettings.radius = (new * 10).toFloat()
        }
        binding.speed.setOnValueChangedListener { _, _, new ->
            doppelSettings.speed = (new * 100).toLong()
        }
        binding.shrinkage.setOnValueChangedListener { _, _, new ->
            doppelSettings.shrinkage = new
        }
        binding.strokeWidth.setOnValueChangedListener { _, _, new ->
            doppelSettings.strokeWidth = new
        }
        binding.shrinkage.value = 5
        binding.radius.value = 0
        binding.speed.value = 10
        binding.strokeWidth.value = 0
    }

    private fun setAlphaListeners(binding: ActivityDeckBinding) {
        binding.minAlpha.minValue = 0
        binding.minAlpha.maxValue = 10
        binding.minAlpha.setFormatter {
            (it * 10).toString()
        }
        binding.maxAlpha.minValue = 0
        binding.maxAlpha.maxValue = 10
        binding.maxAlpha.setFormatter {
            (it * 10).toString()
        }
        binding.minAlpha.setOnValueChangedListener { _, _, new ->
            doppelSettings.minAlpha = (new.toFloat() / 10)
        }
        binding.maxAlpha.setOnValueChangedListener { _, _, new ->
            doppelSettings.maxAlpha = (new.toFloat() / 10)
        }
        binding.minAlpha.value = 5
        binding.maxAlpha.value = 10
    }

    private fun createDoppelInstanceForSettings() {
        val configuration = makeDoppelConfiguration()
        doppel = Doppel(configuration, *doppelSettings.scope)
    }

    private fun makeDoppelConfiguration(): DoppelConfiguration {
        val colorDrawablesProvider = DoppelColorDrawablesProvider(doppelSettings.colors)
        colorDrawablesProvider.setAnimationSpeed(doppelSettings.speed)
        colorDrawablesProvider.setMinAlpha(doppelSettings.minAlpha)
        colorDrawablesProvider.setMaxAlpha(doppelSettings.maxAlpha)
        colorDrawablesProvider.setCornerRadius(doppelSettings.radius)
        colorDrawablesProvider.setStroke(doppelSettings.strokeWidth, doppelSettings.strokeColor)
        colorDrawablesProvider.setShrinkage(doppelSettings.shrinkage)
        val configurationBuilder = DoppelConfigurationBuilder(this)
                .withBackgroundProvider(colorDrawablesProvider)
        when {
            doppelSettings.type.isEmpty() -> {
            }
            else -> configurationBuilder.targetSpecificViewTypes(*doppelSettings.getTypes<View>())
        }
        return configurationBuilder.build()
    }
}

private class DoppelSettings(context: Context, binding: ActivityDeckBinding) {

    var colors = DoppelColors.GRAYS(context)
    var type: Array<Class<*>> = arrayOf()
    var minAlpha = 0.5f
    var maxAlpha = 1f
    var speed = 1000L
    var radius = 0f
    var shrinkage = 5
    var strokeWidth = 0
    var strokeColor = Color.BLACK
    var scope: Array<View> = arrayOf(binding.testUserProfileCard, binding.testOrderCard)

    fun setColors(context: Context, value: String) {
        colors = when (value) {
            "Greys" -> DoppelColors.GRAYS(context)
            "Greys Inverted" -> DoppelColors.GRAYS_INVERT(context)
            "Blue Greys" -> DoppelColors.BLUEGRAYS(context)
            "Blue Greys Inverted" -> DoppelColors.BLUEGRAYS_INVERT(context)
            "Blues" -> DoppelColors.BLUES(context)
            "Blues Inverted" -> DoppelColors.BLUES_INVERT(context)
            "Reds" -> DoppelColors.REDS(context)
            "Reds Inverted" -> DoppelColors.REDS_INVERT(context)
            "Greens" -> DoppelColors.LIGHT_GREENS(context)
            "Greens Inverted" -> DoppelColors.LIGHT_GREENS_INVERT(context)
            "Yellows" -> DoppelColors.YELLOWS(context)
            "Yellows Inverted" -> DoppelColors.YELLOWS_INVERT(context)
            "Random" -> DoppelColors.PALE_MIXED(context).shuffled()
            else -> DoppelColors.GRAYS(context)
        }
    }

    fun setType(context: Context, value: String) {
        type = when (value) {
            "Target All Text and Images" -> arrayOf(AppCompatTextView::class.java, AppCompatImageView::class.java, AppCompatEditText::class.java)
            "Target TextViews" -> arrayOf(AppCompatTextView::class.java)
            "Target EditTexts" -> arrayOf(AppCompatEditText::class.java)
            "Target Images" -> arrayOf(AppCompatImageView::class.java)
            "All, Exclude Images" -> arrayOf(AppCompatImageView::class.java)
            else -> arrayOf()
        }
    }

    fun <T : View> getTypes(): Array<Class<out T>> {
        return type as Array<Class<out T>>
    }

    fun setStrokeColor(context: Context, value: String) {
        strokeColor = when (value) {
            "Black" -> Color.BLACK
            "White" -> Color.WHITE
            "Green" -> Color.GREEN
            "Yellow" -> Color.YELLOW
            "Red" -> Color.YELLOW
            "Blue" -> Color.BLUE
            else -> Color.TRANSPARENT
        }
    }

    fun setScope(context: Context, value: String, binding: ActivityDeckBinding) {
        scope = when (value) {
            "Order Card" -> arrayOf(binding.testOrderCard)
            "Profile Card" -> arrayOf(binding.testUserProfileCard)
            "Profile Card and Order Info" -> arrayOf(binding.testUserProfileCard, binding.orderInfoCard)
            "Entire Screen" -> arrayOf(binding.fullLayout)
            else -> arrayOf(binding.testOrderCard, binding.testUserProfileCard)
        }
    }
}