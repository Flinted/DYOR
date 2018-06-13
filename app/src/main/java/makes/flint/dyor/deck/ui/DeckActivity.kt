package makes.flint.dyor.deck.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.ViewGroup
import android.widget.Toast
import makes.flint.doppel.doppelState.Doppel
import makes.flint.doppel.doppelState.backgroundproviders.DoppelColorDrawablesProvider
import makes.flint.doppel.doppelState.backgroundproviders.DoppelViewTypeColorProvider
import makes.flint.doppel.doppelState.backgroundproviders.backgroundconvenience.DoppelColors
import makes.flint.doppel.doppelState.doppelbuilder.DoppelConfigurationBuilder
import makes.flint.dyor.R
import makes.flint.dyor.base.BaseActivity
import makes.flint.dyor.databinding.ActivityDeckBinding
import makes.flint.dyor.deck.models.DeckViewModel

/**
 * DeckActivity
 */
class DeckActivity : BaseActivity() {

    private lateinit var deckViewModel: DeckViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        deckViewModel = getViewModelComponent().provideDeckViewModel()
        val binding: ActivityDeckBinding = DataBindingUtil.setContentView(this, R.layout.activity_deck)
        binding.viewModel = deckViewModel
        binding.executePendingBindings()

        val viewTypeProvider = DoppelViewTypeColorProvider()
        viewTypeProvider.addViewTypeColours(
                Pair(AppCompatTextView::class.java, ContextCompat.getColor(this, R.color.red_doppel)),
                Pair(AppCompatImageView::class.java, ContextCompat.getColor(this, R.color.orange_doppel)))

        val colorProvider = DoppelColorDrawablesProvider(DoppelColors.GREEN())
        colorProvider.setAnimationSpeed(200)
        colorProvider.setCornerRadius(50f)

        val configuration = DoppelConfigurationBuilder()
                .withBackgroundProvider(viewTypeProvider)
                .targetSpecificViewTypes(AppCompatImageView::class.java, AppCompatTextView::class.java, AppCompatEditText::class.java)
                .build()

        val configuration2 = DoppelConfigurationBuilder()
                .withBackgroundProvider(colorProvider)
                .targetSpecificViewTypes(AppCompatTextView::class.java, AppCompatEditText::class.java)
                .build()

        val doppel1 = Doppel(binding.testUserProfileCard as ViewGroup, configuration)
        val doppel2 = Doppel(binding.testUserProfileCard2 as ViewGroup, configuration)
        val doppel3 = Doppel(binding.testUserProfileCard3 as ViewGroup, configuration2)

        binding.navigationBottomBar.setOnClickListener {
            if (doppel1.active) {
                doppel1.off()
                doppel2.off()
                doppel3.off()
                return@setOnClickListener
            }
            doppel1.on()
            doppel2.on()
            doppel3.on()
        }
        binding.testUserType3.setOnClickListener {
            Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT).show()
        }
    }
}