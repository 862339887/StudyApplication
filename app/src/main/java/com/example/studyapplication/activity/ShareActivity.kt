package com.example.studyapplication.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider.getUriForFile
import com.example.studyapplication.R
import com.example.studyapplication.constants
import com.example.studyapplication.util.FileUtil
import com.twitter.sdk.android.tweetcomposer.TweetComposer
import kotlinx.android.synthetic.main.activity_share.*
import java.io.File


class ShareActivity : AppCompatActivity() {
    private var bmpPath: String? = null

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        initView()
        println(constants.WX_TIMELINE.name)
        val bmp = BitmapFactory.decodeResource(resources, R.drawable.shoe_bag)
        val savePath = "share_image_" + System.currentTimeMillis() + ".jpeg"
        val saveDir: String? = FileUtil.getCacheFilePathDir(this)
        FileUtil.saveBitmapToSD(bmp, saveDir, savePath)
        bmpPath =
            "/storage/emulated/0/Android/data/com.example.studyapplication/cache/share_content_cache/$savePath"
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun initView() {
        share_open.setOnClickListener {
            shareToTwitter()
//            shareToWhatsApp()
//            showLocationShare()
//            sendEmail("fdfa","fdafa","fafafeef")
        }
    }


    private fun shareToTwitter() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, "hello！") //注意：这里只是分享文本内容
        sendIntent.type = "text/plain"
        val file = File(bmpPath!!)
        val contentUri = getUriForFile(this, "com.example.studyapplication.fileProvider", file);
        sendIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
        sendIntent.type = "image/jpeg"
        val packManager: PackageManager = packageManager
        val resolvedInfoList = packManager.queryIntentActivities(
            sendIntent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
//        sendIntent.setPackage("com.whatsapp")
        for (resolveInfo in resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                sendIntent.setClassName(
                    resolveInfo.activityInfo.packageName,
                    resolveInfo.activityInfo.name
                )
//                sendIntent.setPackage(
//                    resolveInfo.activityInfo.packageName
//                )
            }
        }
        startActivity(sendIntent)
    }


    private fun shareToWhatsApp() {

        val file = File(bmpPath!!)
        val contentUri = getUriForFile(this, "com.example.studyapplication.fileProvider", file);
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.setPackage("com.whatsapp")
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "fdafda")
        whatsappIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
        whatsappIntent.type = "image/jpeg"
        whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        try {
            startActivity(whatsappIntent)
        } catch (ex: ActivityNotFoundException) {
            Log.e("shareactivity", "shareToWhatsApp: ")
        }
    }

    /**
     * 调用本地分享文本
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun showLocationShare() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
//        sendIntent.data=Uri.parse("mms://")
        sendIntent.putExtra("sms_body", "hello！") //注意：这里只是分享文本内容
//        sendIntent.setClassName("com.android.mms", "com.android.mms.ui.ComposeMessageActivity");
        val file = File(bmpPath!!)

        val contentUri = getUriForFile(this, "com.example.studyapplication.fileProvider", file);
        sendIntent.putExtra(Intent.EXTRA_STREAM, contentUri)

        val defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this)
        if (defaultSmsPackage != null) {
            sendIntent.setPackage(defaultSmsPackage)
        }
//        sendIntent.data = Uri.parse("mailto:")
//        sendIntent.type = "message/rfc822"
        sendIntent.type = "image/*"
        startActivity(sendIntent)
    }

    fun sendEmail(title: String?, content: String?, emailUrl: String?) {
//        val email = Intent(Intent.ACTION_SENDTO)
//        email.type = "plain/text"
//        email.data = Uri.parse("mailto:")
//        email.putExtra(Intent.EXTRA_EMAIL, emailUrl)
//        //邮件主题
//        email.putExtra(Intent.EXTRA_SUBJECT, title)
//        //邮件内容
//        email.putExtra(Intent.EXTRA_TEXT, content)
//        val intent= Intent.createChooser(email, "请选择邮件发送内容")
//        startActivity(intent)


        val i = Intent(Intent.ACTION_SEND)
        // i.setType("text/plain"); //模拟器请使用这行
        // i.setType("text/plain"); //模拟器请使用这行
        i.type = "message/rfc822" // 真机上使用这行

        i.putExtra(Intent.EXTRA_EMAIL, arrayOf("FxMarginTrading@feib.com.tw"))
        i.putExtra(Intent.EXTRA_SUBJECT, "您的建议")
        i.putExtra(Intent.EXTRA_TEXT, "我们很希望能得到您的建议！！！")
        startActivity(
            Intent.createChooser(
                i,
                "Select email application."
            )
        )
    }
}