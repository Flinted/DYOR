package makes.flint.dyor.base

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import makes.flint.dyor.injection.components.DaggerViewModelComponent
import makes.flint.dyor.injection.components.ViewModelComponent
import makes.flint.dyor.injection.modules.ViewModelModule

/**
 * BaseApplication
 */

class BaseApplication : Application() {

    // Properties

    private var viewModelComponent: ViewModelComponent? = null

    // Overrides

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

    // Internal Functions

    internal fun getViewModelComponent(): ViewModelComponent = viewModelComponent ?: initialiseViewModelComponent()

    // Private Functions

    private fun initialiseViewModelComponent(): ViewModelComponent {
        viewModelComponent = DaggerViewModelComponent.builder().viewModelModule(ViewModelModule()).build()
        return viewModelComponent as ViewModelComponent
    }
}