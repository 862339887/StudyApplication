package com.example.studyapplication.Function

import com.example.studyapplication.activity.Person
import java.nio.channels.Selector

fun main() {
    val person1 = Person("小明", 12)
    val person2 = Person("小红", 13)
    val person3 = Person("小明", 12)
    val person4 = Person("小红", 12)
    var array3 = listOf<Int>(1, 2, 3)
    var array4 = ArrayList<Int>()
    val array1 = arrayListOf<Person>().apply {
        add(person1)
        add(person2)
        add(person3)
        add(person4)
    }
    val array2 = arrayListOf<Person>().apply {
        add(person1)
        add(person2)

    }
//    array1.distinctBy { Pair(it.age, it.name) }.forEach {
//        println("${it.name},${it.age}")
//    }
    array3.mapTo(array4) { it + 3 }.forEach { println(it) }

    array4.forEach { println(it) }
}