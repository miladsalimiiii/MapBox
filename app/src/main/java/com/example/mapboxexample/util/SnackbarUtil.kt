package com.example.mapboxexample.util

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import com.example.mapboxexample.R
import com.google.android.material.snackbar.Snackbar

class SnackbarUtil(private val mContext: Context) {

    fun showSnackbarNotify(
        view: View?,
        string: String?,
        anchorView: View? = null,
        snackbarLength: Int? = null
    ): Snackbar {
        val snackbar = Snackbar.make(view!!, string!!, snackbarLength ?: Snackbar.LENGTH_LONG)
        config(snackbar)
        anchorView.let { snackbar.anchorView = anchorView }
        snackbar.show()
        return snackbar
    }

    fun showSnackbarError(view: View?, string: String?, snackbarLength: Int? = null): Snackbar {
        val snackbar = Snackbar.make(view!!, string!!, snackbarLength ?: Snackbar.LENGTH_LONG)
        config(snackbar)
        snackbar.show()
        return snackbar
    }

    private fun config(snackbar: Snackbar) {
        val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
        val textViewSnackbarAction = snackbar.view.findViewById<TextView>(R.id.snackbar_action)
        val typeface = ResourcesCompat.getFont(mContext, R.font.habit_regular)
        textView.typeface = typeface
        textViewSnackbarAction.typeface = typeface
        textView.maxLines = 1
        ViewCompat.setElevation(snackbar.view, 6f)
    }

}