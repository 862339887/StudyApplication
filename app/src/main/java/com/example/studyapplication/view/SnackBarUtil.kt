package com.example.studyapplication.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar

object SnackBarUtil {

    fun showSnackBar(
        context: Context,
        containerView: ViewGroup,
        toastView: View,
        displayTime: Int
    ): Snackbar {
        val snackBar = Snackbar.make(containerView, "", displayTime)
        val snackBarView = snackBar.view as ViewGroup
        val layoutParams = snackBarView.layoutParams as FrameLayout.LayoutParams
        layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT
        layoutParams.gravity = Gravity.CENTER or Gravity.BOTTOM
        snackBarView.setPadding(0,0,0,0)
        snackBarView.setBackgroundColor(Color.TRANSPARENT)
        if (toastView.parent != null) {
            val viewGroup = toastView.parent as ViewGroup
            viewGroup.removeView(toastView)
        }
        if (snackBarView.childCount != 0) {
            snackBarView.removeAllViews()
        }
//        toastView.background = drawable
        toastView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        snackBarView.addView(
            toastView
        )
        snackBar.show()
        return snackBar
    }
}