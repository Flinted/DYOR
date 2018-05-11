package makes.flint.dyor.deck.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import makes.flint.dyor.R
import makes.flint.dyor.base.BaseActivity
import makes.flint.dyor.databinding.ActivityDeckBinding
import makes.flint.dyor.deck.models.DeckItemViewModel
import makes.flint.dyor.deck.models.DeckViewModel

/**
 * DeckActivity
 * Copyright © 2018 Intelligent Loyalty Limited. All rights reserved.
 */
class DeckActivity : BaseActivity() {

    private lateinit var deckViewModel: DeckViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        deckViewModel = getViewModelComponent().provideDeckViewModel()
        val binding: ActivityDeckBinding = DataBindingUtil.setContentView(this, R.layout.activity_deck)
        binding.viewModel = deckViewModel
        binding.executePendingBindings()
        println("LOADED")
        val adapter = DeckBaseAdapter(binding.viewModel?.entries!!)
        binding.cardStack.setAdapter(adapter)
        binding.navigationBottomBar.setOnClickListener {
            val dvm = DeckItemViewModel("tetsts")
            binding.viewModel?.entries?.add(dvm)
            adapter.notifyDataSetChanged()
        }
    }
}