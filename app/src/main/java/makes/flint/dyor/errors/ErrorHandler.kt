package makes.flint.dyor.errors

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import makes.flint.dyor.R

/**
 * ErrorHandler
 */

object ErrorHandler {

    var GENERAL_ERROR = R.string.error_general_error

    fun showError(context: Context, id: Int?) {
        val errorId = id ?: GENERAL_ERROR
        val errorString = context.getString(errorId)
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
    }

    fun showSnackBarError(view: View, id: Int?) {
        val errorId = id ?: GENERAL_ERROR
        Snackbar.make(view, errorId, Snackbar.LENGTH_SHORT)
    }
}