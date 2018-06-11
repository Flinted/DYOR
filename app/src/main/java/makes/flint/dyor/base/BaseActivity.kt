package makes.flint.dyor.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import makes.flint.dyor.errors.ErrorHandler
import makes.flint.dyor.injection.components.ViewModelComponent

/**
 * BaseActivity
 */

abstract class BaseActivity : AppCompatActivity() {

    // Overrides

    override fun onDestroy() {
        super.onDestroy()
    }

    // Protected Functions

    protected fun getBaseApplication(): BaseApplication = application as BaseApplication

    protected fun getViewModelComponent(): ViewModelComponent = getBaseApplication().getViewModelComponent()


    // Internal Functions

    internal fun showToast(stringId: Int, toastLength: Int) {
        Toast.makeText(this, getString(stringId), toastLength).show()
    }

    open fun showLoading() {

    }

    open fun hideLoading() {

    }

    open fun showError(stringId: Int?) {
        ErrorHandler.showError(this, stringId)
    }
}