package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.studyapplication.R
import com.example.studyapplication.view.QuickIndexBar
import com.example.studyapplication.view.SnackBarUtil
import com.google.android.material.snackbar.BaseTransientBottomBar
import kotlinx.android.synthetic.main.activity_snack_bar.*

class SnackBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack_bar)
        show_snack_bar.setOnClickListener {
            val snackBarView =
                LayoutInflater.from(this).inflate(R.layout.snack_bar_view, snackBar_root, false) as ConstraintLayout
           SnackBarUtil.showSnackBar(
                this, snackBar_root, snackBarView!!,
                BaseTransientBottomBar.LENGTH_INDEFINITE
            )
        }

    }
}