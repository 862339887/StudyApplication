package com.example.studyapplication.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R
import com.example.studyapplication.others.Reflect
import kotlinx.android.synthetic.main.activity_reflect.*
import java.lang.reflect.Method

class ReflectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflect)
        reflect_page.setOnClickListener {
            Reflect().initView()
        }
        var index=0
//        while (index<3){
//            when(index){
//                1-> {
//                    Log.e("zhouxin--ff","$index")
//                }
//            }
//            try {
//                Log.e("zhouxin--ff","$index")
//                index++
//                if(index==1)break
//            }catch (e:Exception){
//
//            }
//
//        }
    }

//    private fun initView(){
//        val c = Thread.currentThread().contextClassLoader.loadClass("com.mypackage.AcessCheck")
//        val m: Method = c.getDeclaredMethod("printStuff", *null as Array<Class<*>?>?)
//        m.setAccessible(true)
//        m.invoke(null, null as Array<Any?>?)
//    }

}


