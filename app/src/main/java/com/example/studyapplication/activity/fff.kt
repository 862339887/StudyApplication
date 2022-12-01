package com.example.studyapplication.activity

private var list2 = ArrayList<Person>()
private var list1: List<Person>? = null
val person1 = Person("小明", 14)
fun main() {
    for (i in 1..10) {
        list2.add(person1)
        list1 = list2
    }
//    var list1 = list2.map { it.copy() }
//    list2[1].name = "xxxxx"

    list1!!.forEach {
        println(it.name + "   ${it.age}")
    }

    list2.forEach {
        println(it.name + "   ${it.age}")
    }

}

