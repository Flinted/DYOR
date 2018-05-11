package makes.flint.dyor.deck.models

import android.databinding.ObservableField

/**
 * DeckItemViewModel
 * Copyright © 2018 Intelligent Loyalty Limited. All rights reserved.
 */

class DeckItemViewModel(value: String) {
    var value: ObservableField<String> = ObservableField(value)
}