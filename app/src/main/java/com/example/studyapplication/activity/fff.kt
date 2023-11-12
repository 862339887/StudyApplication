//package com.example.studyapplication.activity
//
//private var list2 = ArrayList<Person>()
//private var list1: List<Person>? = null
//val person1 = Person("小明", 14)
////fun main() {
////    for (i in 1..10) {
////        list2.add(person1)
////        list1 = list2
////    }
//////    var list1 = list2.map { it.copy() }
//////    list2[1].name = "xxxxx"
////
////    list1!!.forEach {
////        println(it.name + "   ${it.age}")
////    }
////
////    list2.forEach {
////        println(it.name + "   ${it.age}")
////    }
////}
//
//val array = arrayOf(5,4,3,6,8,1,-1,1)
//fun main(){
//    for(i in array.indices){
//        var j = i+1
//        while(j< array.size){
//            if(array[i]< array[j]){
//                val temp = array[i]
//                array[i] = array[j]
//                array[j]=temp
//            }
//            j++
//        }
//    }
//    array.forEach {
//        println(it)
//    }
//}
//
//
//
//
//

/**
 * 饿汉式 线程安全
 */

//class SingleTon private constructor() {
//    companion object {
//        val instance = SingleTon()
//    }
//}

/**
 * 懒汉式，线程不安全
 */
//
//class SingleTon private constructor() {
//
//    companion object {
//        private var instance: SingleTon? = null
//
//        fun getInstance() : SingleTon {
//            if (instance == null) {
//                instance = SingleTon()
//            }
//            return instance!!
//        }
//    }
//}

/**
 * 懒汉式，线程安全
 */

//class SingleTon private constructor() {
//    companion object {
//        private var instance: SingleTon? = null
//        fun getInstance(): SingleTon {
//            synchronized(this) {
//                if (instance == null) {
//                    instance = SingleTon()
//                }
//            }
//            return instance!!
//        }
//    }
//}

/**
 * 双重校验锁，线程不安全模式
 */
//class SingleTon private constructor() {
//    companion object {
//        private var instance : SingleTon? =null
//        fun getInstance (): SingleTon {
//            if (instance == null) {
//                synchronized(this) {
//                    if(instance == null) {
//                        instance = SingleTon()
//                    }
//                }
//            }
//            return instance!!
//        }
//    }
//}

/**
 * 双重校验锁，线程安全
 */
//class SingleTon private constructor() {
//    companion object {
//        @Volatile
//        private var instance: SingleTon? = null
//
//        fun getInstance (): SingleTon {
//            if (instance == null) {
//                synchronized(this) {
//                    if(instance ==null) {
//                        instance = SingleTon()
//                    }
//                }
//            }
//            return instance!!
//        }
//    }
//}

/**
 * 枚举类,线程安全
 */
//enum class SingleTon () {
//    INSTANCE
//}

/**
 * 静态内部类
 */


class SingleTon private constructor() {
    companion object {
        val instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = SingleTon()
    }
}



