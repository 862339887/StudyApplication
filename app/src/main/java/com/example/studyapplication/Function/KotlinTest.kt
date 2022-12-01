package com.example.studyapplication

import kotlin.math.absoluteValue

fun main(){
    val a= arrayOf<Int>(1,2,3,4)
    val b= arrayListOf<Int>(-1,*a,0)
    for(item in b){
        print(item)
    }
    b.apply {
        a.forEach {
            add(it.absoluteValue)
        }
    }

}