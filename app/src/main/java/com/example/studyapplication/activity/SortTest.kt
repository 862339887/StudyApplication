package com.example.studyapplication.activity

/**
 * Created by zhouxin on 2023/10/31
 * @author zhouxin.3012143@bytedance.com
 */

fun main() {
    val array = intArrayOf(5, 4, 3, 6, 2, 8, 1, -1, 1)
//    maoPaoSort(array)
//    selectSort(array)
//    insertSort(array)
//    quickSort(array, 0, array.size - 1)
    heapSort(array)
//    mergeSort(array, 0, array.size - 1, IntArray(array.size))
    array.forEach {
        println(it)
    }
}


fun maoPaoSort(array: IntArray) {
    for (i in array.indices) {
        var isChange = false
        for (j in i + 1 until array.size) {
            if (array[i] > array[j]) {
                val temp = array[i]
                array[i] = array[j]
                array[j] = temp
                isChange = true
            }
        }
        if (!isChange) break
    }
}


fun selectSort(array: IntArray) {
    for (i in array.indices) {
        var minIndex = i
        for (j in i + 1 until array.size) {
            if (array[minIndex] > array[j]) {
                minIndex = j
            }

        }
        val temp = array[minIndex]
        array[minIndex] = array[i]
        array[i] = temp
    }
}

fun insertSort(array: IntArray) {
    for (i in 1 until array.size) {
        val temp = array[i]
        var j = i - 1
        while (j >= 0 && array[j] > temp) {
            array[j + 1] = array[j]
            j--
        }
        array[j + 1] = temp
    }
}

fun quickSort(array: IntArray, left: Int, right: Int) {
    var start = left
    var end = right
    if (start <= end) {
        val temp = array[start]
        while (start != end) {
            while (start < end && array[end] >= temp) {
                end--
            }
            array[start] = array[end]
            while (start < end && array[start] <= temp) {
                start++
            }
            array[end] = array[start]
        }
        array[end] = temp
        quickSort(array, left, end - 1)
        quickSort(array, end + 1, right)
    }
}

fun mergeSort(array: IntArray, start: Int, end: Int, temp: IntArray) {
    if (start < end) {
        val mid = start + (end - start) / 2
        mergeSort(array, start, mid, temp)
        mergeSort(array, mid + 1, end, temp)
        mergeSortHelper(array, start, mid, end, temp)
    }
}

fun mergeSortHelper(array: IntArray, start: Int, mid: Int, end: Int, temp: IntArray) {
    var left = start
    var right = mid + 1
    var index = 0
    while (left <= mid && right <= end) {
        if (array[left] < array[right]) {
            temp[index++] = array[left++]
        } else {
            temp[index++] = array[right++]
        }
    }
    while (left <= mid) {
        temp[index++] = array[left++]
    }
    while (right <= end) {
        temp[index++] = array[right++]
    }
    index = 0
    left = start
    right = end
    while (left <= right) {
        array[left++] = temp[index++]
    }
}


fun heapSort(array: IntArray) {
    for (j in array.size/2 - 1 downTo 0) {
        adjustHeap(array, j, array.size)
    }

    for (j in array.size-1 downTo 0 ) {
        swap(array, 0, j)
        adjustHeap(array, 0, j)
    }
}

fun swap(array: IntArray, i: Int, j: Int) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}

fun adjustHeap(array: IntArray, index: Int, length: Int) {
    val temp = array[index]
    var k = index * 2 + 1
    var i = index
    while (k < length) {
        if (k + 1 < length && array[k + 1] > array[k]) {
            k++
        }
        if (array[k] > temp) {
            array[i] = array[k]
            i = k
        } else {
            break
        }
        k = k * 2 + 1
    }
    array[i] = temp
}