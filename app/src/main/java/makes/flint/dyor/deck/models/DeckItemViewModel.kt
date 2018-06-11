package makes.flint.dyor.deck.models

import android.databinding.ObservableField

/**
 * DeckItemViewModel
 */

class DeckItemViewModel(value: String) {
    var value: ObservableField<String> = ObservableField(value)
}