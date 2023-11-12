package com.example.studyapplication.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by zhouxin on 2023/4/30
 * @author zhouxin.3012143@bytedance.com
 */
@RequiresApi(Build.VERSION_CODES.N)
fun main(){
//    val node = createListNode(intArrayOf(1,2,3,4))
    val root = createTreeNode(arrayListOf(2,null,3),0)
    val result = Solution().inorderSuccessor(root, TreeNode(2))
    val maxHeap =  PriorityQueue<Int>(Collections.reverseOrder());

    println(result)
}

//class Solution {
//    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
//        val result = mutableListOf<List<Int>>()
//        if (root == null) return result
//        val queue = LinkedList<TreeNode>()
//        var flag = false
//        var newRoot = root
//        queue.add(newRoot)
//        while (queue.isNotEmpty()) {
//            val list = mutableListOf<Int>()
//            for (item in queue) {
//                val node = queue.poll()
//                if (item.left != null) {
//                    queue.add(item.left!!)
//                }
//                if (item.right != null) {
//                    queue.add(item.right!!)
//                }
//                if (flag) {
//                    list.add(0,node.`val`)
//                } else {
//                    list.add(node.`val`)
//                }
//            }
//            flag = !flag
//            result.add(list)
//        }
//        return result
//    }
//}

  class TreeNode(var `val`: Int) {
      var left: TreeNode? = null
      var right: TreeNode? = null
  }
//class Solution {
//    fun recoverTree(root: TreeNode?): Unit {
//        if (root == null) return
//        var node1: TreeNode?=null
//        var node2:TreeNode?=null
//        val stack = Stack<TreeNode>()
//        var newRoot = root
//        var pre: TreeNode?=null
//        val queue  = LinkedList<Int>()
//        queue.poll()
//        while (newRoot!=null || stack.isNotEmpty()) {
//            if (newRoot == null) {
//                val temp = stack.pop()
//                newRoot = temp.right
//                if (((pre?.`val`)?:Integer.MIN_VALUE) > temp.`val`) {
//                    if (node1!=null) {
//                        node2= temp
//                    } else {
//                        node1 = pre
//                    }
//                }
//                pre = temp
//            } else {
//                stack.push(newRoot!!)
//                newRoot = newRoot?.left
//            }
//        }
//        if (node1 != null && node2 != null ) {
//            val newTemp = node1?.`val`
//            node1?.`val` = node2?.`val`
//            node2?.`val` = newTemp
//        }
//
//    }
//}

class Solution {
    fun inorderSuccessor(root: TreeNode?, p: TreeNode?): TreeNode? {
        if (root == null || p == null) return null
        var result : TreeNode?=null
        val stack = Stack<TreeNode>()
        var newRoot = root
        var target = p!!.`val`
        var flag = false
        while(stack.isNotEmpty() || newRoot!=null) {
            if (newRoot!=null) {
                stack.add(newRoot)
                newRoot = newRoot?.left
            } else {
                val node = stack.pop()
                if (flag == true){
                    return  node

                }
                if (node.`val` == target) flag = true
                newRoot = newRoot?.right
            }
        }
        return result
    }
}
fun createTreeNode (array: ArrayList<Int?>, index:Int):TreeNode?{
    var treeNode: TreeNode? = null
    if (index < array.size) {
        if (array[index] == null) return null
        treeNode = TreeNode(array[index]!!)
        // 对于顺序存储的完全二叉树，如果某个节点的索引为index，其对应的左子树的索引为2*index+1，右子树为2*index+1
        treeNode.left = createTreeNode(array, 2 * index + 1)
        treeNode.right = createTreeNode(array, 2 * index + 2)
    }
    return treeNode
}
/**
 * 创建链表
 */
fun createListNode(array:IntArray):ListNode? {
    var dummy = ListNode(-1)
    var p = dummy
    array.forEach {
        p?.next = ListNode(it)
        p = p.next!!
    }
    return dummy.next
}


  class ListNode(var `val`: Int) {
      var next: ListNode? = null
  }


//internal class Solution {
//    fun reorderList(head: ListNode?) {
//        if (head == null) return
//        var fast = head
//        var slow = head
//        var pre = head
//        while (fast != null && fast.next != null) {
//            fast = fast.next!!.next
//            pre = slow
//            slow = slow!!.next
//        }
//        pre?.next = null
//        val secondHead = reverse(slow)
//        reorder(head, secondHead)
//    }
//
//    fun reorder(head1: ListNode?, head2: ListNode?) {
//        var head1 = head1
//        var head2 = head2
//        while (head1 != null && head2 != null) {
//            val temp1 = head1.next
//            val temp2 = head2.next
//            head2.next = null
//            head1.next = head2
//            head2.next = temp1
//            head1 = temp1
//            head2 = temp2
//        }
//    }
//
//    fun reverse(head: ListNode?): ListNode? {
//        var head = head
//        var pre: ListNode? = null
//        while (head != null) {
//            val temp = head.next
//            head.next = pre
//            pre = head
//            head = temp
//        }
//        return pre
//    }
//}
//class Solution {
//    fun deleteNode(nod: ListNode?) {
//        var node:ListNode? = nod
//        var pre = node
//        while(node?.next != null) {
//            val temp = node?.next
//            node?.`val` = node?.next?.`val`!!
//            pre = node
//            node = temp
//        }
//        pre?.next = null
//    }
//}
//internal class Solution {
//    fun deleteNode(node: ListNode?) {
//        //ListNode temp=new ListNode(-1);
//        var node = node
//        var pre = node
//        while (node!!.next != null) {
//            val temp = node.next
//            node.`val` = node.next!!.`val`
//            pre = node
//            node = temp
//        }
//        pre!!.next = null
//    }
//}
//class Solution {
//    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
//        if (head == null || head?.next == null || left == right ) return head
//        var dummy = ListNode(-1)
//        dummy?.next = head
//        var before  = dummy
//        var after: ListNode?= null
//        var reverseHead:ListNode?=null
//        var reverseTail:ListNode?=null
//        var p = head
//        for (i in 1..left-1) {
//            before = p
//            p = p?.next
//            reverseTail = p
//        }
//        var pre: ListNode? =null
//        for (i in 1 ..right-left+1) {
//            val temp = p?.next
//            p?.next = pre
//            pre = p
//            p = temp
//        }
//        reverseHead = pre
//        after = p
//        before?.next = reverseHead
//        reverseTail?.next = after
//        return dummy?.next
//    }
//
//}

