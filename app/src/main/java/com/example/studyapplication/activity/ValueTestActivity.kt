package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_value_test.*

open class TestObject(var name1: String, var age: Int)
class ValueTestActivity : AppCompatActivity() {
    companion object{
        private const val TAG="ValueTestActivity"
    }
    var array1 = ArrayList<TestObject>()
    var array2 = ArrayList<TestObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val testObject1 = TestObject("小明", 18)
        val testObject2 = TestObject("小红", 18)
        val testObject3 = TestObject("小明", 20)
        setContentView(R.layout.activity_value_test)
        button_invoke1.setOnClickListener {
            array1.apply {
                add(testObject1)
                add(testObject2)

            }
            array2.apply {
                add(testObject1)
                add(testObject2)
            }
        }
        button_invoke2.setOnClickListener {
            array1.filter { it.name1==testObject3.name1 }.forEach{
                it.age=testObject3.age
            }

            array1.forEach {
                Log.e(TAG, "onCreate: ${it.name1},${it.age}")
            }
            array2.forEach {
                Log.e(TAG, "onCreate: ${it.name1},${it.age}")
            }
        }
    }
}