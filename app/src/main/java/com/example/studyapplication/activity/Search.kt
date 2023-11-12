package com.example.studyapplication.activity

/**
 * Created by zhouxin on 2023/2/28
 * @author zhouxin.3012143@bytedance.com
 */

fun main(){
    val array = arrayOf(5, 4, 3, 6, 2, 8, 1, -1, 1)
    println(binarySearch(array,4))
}

//二分查找
fun binarySearch(array: Array<Int>,target:Int):Boolean{

    if(array.isEmpty()) return false
    array.sort()
    var left = 0
    var right = array.size-1
    while(left<=right){
        val mid = left + (right - left) /2
        when{
            array[mid] == target  -> return true
            array[mid] > target ->{
                right = mid -1
            }
            array[mid] < target -> {
                left = mid +1
            }
        }
    }
    return false
}