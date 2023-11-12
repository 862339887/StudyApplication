package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.studyapplication.R
import com.example.studyapplication.model.BaseImageUrlModel
import kotlinx.android.synthetic.main.activity_equals_test.*

class EqualsTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equals_test)
        val oldData = ToolEntryVO(
            cardType = 1,
            title = "2323",
            titleColor = "fdaf",
            link = "fdaf",
            linkTitleText = "fdafa",
            imageUrls = BaseImageUrlModel(listOf("43rrw")),
            buttonName = "fdsaf",
            notify = false
            )
        val newState = ToolEntryVO(
            cardType = 1,
            title = "2323",
            titleColor = "fdaf",
            link = "fdaf",
            linkTitleText = "fdafa",
            imageUrls = BaseImageUrlModel(listOf("43rrw")),
            buttonName = "fdsaf",
            notify = false
        )

        test_equals.setOnClickListener{
            val result = listOf(oldData).equals(listOf(newState))
            Log.e("zhouxin-result",result.toString())
        }
    }
}


data class ToolEntryVO(
    val cardType: Int,
    val title: String = "",
    val titleColor: String? = null,  // title颜色
    var link: String = "",
    val linkTitleText: String = "",
    val imageUrls: BaseImageUrlModel? = null,
    val buttonName: String = "",
    var notify: Boolean = false
)




