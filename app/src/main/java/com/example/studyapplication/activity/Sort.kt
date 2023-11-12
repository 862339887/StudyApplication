//
//import com.example.studyapplication.util.ListNode
//
//
///**
// * Created by zhouxin on 2023/2/25
// * @author zhouxin.3012143@bytedance.com
// */
//
//
//fun main() {
//    val array = arrayOf(5, 4, 3, 6, 2, 8, 1, -1, 1)
//    val array1 = intArrayOf(5, 4, 3, 6, 2, 8, 1, -1, 1)
////    maoPaoSort(array)
////    selectSort(array)
////    insertSort(array)
////    quickSort(array, 0, array.size - 1)
////    heapSort(array)
//    mergeSort(array1)
//    array1.forEach {
//        println(it)
//    }
//}
//
////归并排序
//fun mergeSort(array: IntArray){
//    mergeSortHelper(array,0,array.size-1,IntArray(array.size))
//}
//
//fun mergeSortHelper(array: IntArray, start: Int, end: Int, temp: IntArray) {
//    if(start<end) {
//        val mid = (start + end) /2
//        mergeSortHelper(array,start,mid,temp)
//        mergeSortHelper(array,mid+1,end,temp)
//        merge(array,start,mid,end,temp)
//    }
//}
//
//fun merge(array: IntArray, start: Int, mid:Int, end: Int, temp: IntArray) {
//    var left = start
//    var right = mid+1
//    var tempIndex = 0
//    while(left<=mid && right<=end) {
//        if(array[left] < array[right]) {
//            temp[tempIndex++] = array[left++]
//        } else{
//            temp[tempIndex++] = array[right++]
//        }
//    }
//    while(left<=mid) {
//        temp[tempIndex++] = array[left++]
//    }
//    while(right<=end) {
//        temp[tempIndex++] = array[right++]
//    }
//    left = start
//    right = end
//    tempIndex = 0
//    while(left<=right) {
//        array[left++] = temp[tempIndex++]
//    }
////    temp.forEachIndexed { index, value ->
////        array[index] = value
////    }
//}
//
////堆排序也很难理解
//fun heapSort(array: Array<Int>) {
//    for (i in array.size / 2 - 1 downTo 0) {
//        heapSortHelper(array, i, array.size - 1)
//    }
//    for (j in array.size - 1 downTo 0) {
//        val temp = array[0]
//        array[0] = array[j]
//        array[j] = temp
//        heapSortHelper(array, 0, j)
//    }
//}
//
//fun heapSortHelper(array: Array<Int>, index: Int, end: Int) {
//    var father = index
//    var child = 2*index+1
//    var temp=array[father]
//    while (child<end) {
//        if(child+1<end && array[child]<array[child+1]){
//            child++
//        }
//        if(temp<array[child]){
//            array[father] = array[child]
//            array[child] = temp
//            if(child*2+1<end) {
//                temp=array[child]
//                father = child
//                child = child*2+1
//            }else{
//                break
//            }
//        }else{
//            break
//        }
//    }
//
//}
//
//fun maoPaoSort(array: Array<Int>) {
//    for (i in array.indices) {
//        for (j in i + 1 until array.size step 1) {
//            if (array[i] > array[j]) {
//                val temp = array[i]
//                array[i] = array[j]
//                array[j] = temp
//            }
//        }
//    }
//    array.forEach {
//        println(it)
//    }
//}
//
//fun selectSort(array: Array<Int>) {
//    for (i in array.indices) {
//        var min = i
//        for (j in i + 1 until array.size) {
//            if (array[min] > array[j]) {
//                min = j
//            }
//        }
//        val temp = array[min]
//        array[min] = array[i]
//        array[i] = temp
//    }
//    array.forEach {
//        println(it)
//    }
//}
//
//fun insertSort(array: Array<Int>) {
//    //插入排序
//    for (i in 1 until array.size) {
//        val temp = array[i]
//        var j = i - 1
//        while (j >= 0 && temp < array[j]) {
//            array[j + 1] = array[j]
//            j--
//        }
//        array[j + 1] = temp
//    }
//    array.forEach {
//        println(it)
//    }
//}
//
////重点要记忆
////快拍
//fun quickSort(array: Array<Int>, start: Int, end: Int) {
//    if (start >= end) return
//    var left = start
//    var right = end
//    val baseNum = array[end]
//    while (left < right) {
//        while (left != right && array[left] <= baseNum) {
//            left++
//        }
//        array[right] = array[left]
//        while (left != right && array[right] >= baseNum) {
//            right--
//        }
//        array[left] = array[right]
//    }
//    array[left] = baseNum
//    quickSort(array, start, left - 1)
//    quickSort(array, left + 1, end)
//}
//
//fun quickSortHelper(array: Array<Int>, start: Int, end: Int): Int {
//    var left = start
//    var right = end
//    val baseNum = array[end]
//    val index = end
//    while (left < right) {
//        while (left != right && array[left] <= baseNum) left++
//        while (left != right && array[right] >= baseNum) right--
//        if (left != right) {
//            val temp = array[left]
//            array[left] = array[right]
//            array[right] = temp
//        }
//    }
//    val temp1 = array[left]
//    array[left] = array[index]
//    array[index] = temp1
//    return left
//}
//
//
//
//
//
///**
// * Definition for singly-linked list.
// * public class ListNode {
// * int val;
// * ListNode next;
// * ListNode(int x) { val = x; }
// * }
// */
//internal class Solution {
//    fun sortList(head: ListNode?): ListNode? {
//        val dummy = ListNode(0)
//        dummy.next = head
//        val len = getLen(head)
//        var i = 1
//        while (i < len) {
//            var left = dummy.next
//            var tail: ListNode? = dummy
//            var right: ListNode?
//            while (left != null) {
//                right = cutFrom(left, i)
//                val temp = cutFrom(right, i)
//                tail!!.next = mergeList(left, right)
//                left = temp
//                while (tail!!.next != null) tail = tail.next
//            }
//            i = i * 2
//        }
//        return dummy.next
//    }
//
//    fun cutFrom(head: ListNode?, count: Int): ListNode? {
//        var head = head ?: return null
//        var temp: ListNode? = null
//        var i = 0
//        while (i < count) {
//            temp = head
//            head = head?.next
//            i++
//        }
//        temp!!.next = null
//        return head
//    }
//
//    fun mergeList(left: ListNode?, right: ListNode?): ListNode? {
//        var left = left
//        var right = right
//        val dummy = ListNode(0)
//        var p: ListNode? = dummy
//        while (left != null && right != null) {
//            if (left.`val` < right.`val`) {
//                p!!.next = left
//                left = left.next
//            } else {
//                p!!.next = right
//                right = right.next
//            }
//            p = p.next
//        }
//        if (left != null) p!!.next = left
//        if (right != null) p!!.next = right
//        return dummy.next
//    }
//
//    fun getLen(head: ListNode?): Int {
//        var head = head
//        var len = 0
//        while (head != null) {
//            head = head.next
//            len++
//        }
//        return len
//    }
//}