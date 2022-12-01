package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyapplication.R
import com.example.studyapplication.util.FileUtil
import kotlinx.android.synthetic.main.activity_assets.*
import java.io.BufferedReader
import java.io.InputStreamReader

class AssetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assets)
        read_json.setOnClickListener {
            val result = getAssetsData("EnglishWordLibrary.json")
            val libraryPath =
                "${getExternalFilesDir("englishwordLibrary")}/english_word_library.txt"
            FileUtil.saveFile(result, libraryPath)
        }
    }

    private fun getAssetsData(path: String): String {
        try {
            val inputReader =
                InputStreamReader(resources.assets.open(path))
            val bufReader = BufferedReader(inputReader);
            var line: String?
            var result = ""
            while ((bufReader.readLine()).also { line = it } != null)
                result += line
            return result
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}