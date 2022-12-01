package com.example.studyapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.EditText

class MultiEditTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    def: Int=0
) : androidx.appcompat.widget.AppCompatEditText(context, attrs, android.R.attr.editTextStyle) {

    override fun onCreateInputConnection(outAttrs: EditorInfo?): InputConnection? {
        val inputConnection = super.onCreateInputConnection(outAttrs)
        if (inputConnection != null) {
            outAttrs!!.imeOptions =
                outAttrs.imeOptions and EditorInfo.IME_FLAG_NO_ENTER_ACTION.inv()
        }
        return inputConnection
    }

}