package com.example.studyapplication.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.studyapplication.R
import com.example.studyapplication.util.FileUtil
import kotlinx.android.synthetic.main.activity_file_test.*
import java.io.File

class FileTestActivity : AppCompatActivity() {
    companion object {
        private const val INFIX_DOT_PATH = "english_word_writing_demo/dot/bean/index"
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_test)

        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = this.window.decorView;
            v.systemUiVisibility = View.GONE;
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView = window.decorView;
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) or View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.systemUiVisibility = uiOptions;
        }

//        delete_file_btn.setOnClickListener {
//            val localPath = "${Environment.getExternalStorageDirectory()}/$INFIX_DOT_PATH/1_2"
//            FileUtil.delete(localPath)
//        }
        println_file_path_btn.setOnClickListener {
            val dir = File("${Environment.getExternalStorageDirectory()}/$INFIX_DOT_PATH")
            val fileList = dir.listFiles()
            val result = ArrayList<String>()
            fileList?.forEach {
                result.add(it.absolutePath)
            }
            result.forEach {
                println("aaaaaaaaa/$it")
            }
        }
        write_file_btn.setOnClickListener {
            var localPath = ""
            val data = "111"
            for (i in 1..10) {
                localPath = "${Environment.getExternalStorageDirectory()}/$INFIX_DOT_PATH/$i"
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    //没有授权，编写申请权限代码
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        100
                    );
                } else {
                    Log.d("1111", "requestMyPermissions: 有写SD权限");
                }
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    //没有授权，编写申请权限代码
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        100
                    );
                } else {
                    Log.d("111", "requestMyPermissions: 有读SD权限");
                }
                FileUtil.saveFile(data, localPath)
            }
        }
    }

    @Override
    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return if (event?.keyCode == KeyEvent.KEYCODE_BACK ) {
            //do something.
            true;
        } else {
            super.dispatchKeyEvent(event);
        }
    }
}
