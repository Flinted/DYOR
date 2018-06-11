package makes.flint.dyor.deck.ui

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.CardView
import android.view.ViewGroup
import android.widget.Toast
import makes.flint.doppel.doppelState.Doppel
import makes.flint.doppel.doppelState.DoppelConfigurationBuilder
import makes.flint.doppel.doppelState.backgroundproviders.DoppelViewTypeColorProvider
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

        val provider = DoppelViewTypeColorProvider(defaultColor = Color.LTGRAY, radius = 15f)
        provider.addViewTypeColours(
                Pair(AppCompatTextView::class.java, Color.YELLOW),
                Pair(AppCompatImageView::class.java, Color.RED))

        val configuration = DoppelConfigurationBuilder()
                .withBackgroundProvider(provider)
                .excludeSpecificViewTypes(ConstraintLayout::class.java, CardView::class.java, BottomNavigationView::class.java)
                .build()

        val doppel = Doppel(binding.root as ViewGroup, configuration)
        doppel.targetViewsById(R.id.test_user_type, R.id.test_user_type2, R.id.test_user_type3)
        binding.navigationBottomBar.setOnClickListener {
            if (doppel.isActive()) {
                doppel.off(this)
                return@setOnClickListener
            }
            doppel.on(this)
        }
        binding.testImageProfile.setOnClickListener {
            Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT).show()
        }
    }
}