package makes.flint.dyor.injection.components

import dagger.Component
import makes.flint.dyor.deck.models.DeckViewModel
import makes.flint.dyor.injection.modules.DataModule
import makes.flint.dyor.injection.modules.ViewModelModule
import javax.inject.Singleton

/**
 * PresenterComponent
 * Copyright © 2018 Intelligent Loyalty Limited. All rights reserved.
 */

@Singleton
@Component(modules = [(ViewModelModule::class), (DataModule::class)])
interface ViewModelComponent {

    fun provideDeckViewModel(): DeckViewModel
}
