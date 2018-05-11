package makes.flint.dyor.deck.models

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import javax.inject.Inject

/**
 * DeckViewModel
 * Copyright © 2018 Intelligent Loyalty Limited. All rights reserved.
 */
class DeckViewModel @Inject constructor(){

    var visible: ObservableBoolean = ObservableBoolean()
    var value: ObservableField<String> = ObservableField("TESTING")
    var entries: ObservableArrayList<DeckItemViewModel> = ObservableArrayList()

    init {
        visible.set(true)
        entries.add(DeckItemViewModel("This"))
        entries.add(DeckItemViewModel("Is"))
        entries.add(DeckItemViewModel("A"))
        entries.add(DeckItemViewModel("Test"))
        entries.add(DeckItemViewModel("Of"))
        entries.add(DeckItemViewModel("Data"))
        entries.add(DeckItemViewModel("Binding"))
        entries.add(DeckItemViewModel("YEAH!"))
    }
}
