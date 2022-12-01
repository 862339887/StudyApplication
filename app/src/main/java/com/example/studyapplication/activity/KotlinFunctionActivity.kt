package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyapplication.R

 data class Person(var name: String, var age: Int)
class KotlinFunctionActivity : AppCompatActivity() {
    private var array1 = arrayListOf<Person>().apply {
        add(Person("小明", 15))
        add(Person("小红", 14))
    }
    private var array2 = arrayListOf<Person>().apply {
        Person("小明", 12)
        Person("小芳", 13)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_function)
        val result = (array1.sortedBy { it.age }.reversed())
        result.forEach {
            println("bbbbbbbbbbbb${it.age}")
        }

    }
}