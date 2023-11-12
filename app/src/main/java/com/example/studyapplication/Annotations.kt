package com.example.studyapplication

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

/**
 * Created by zhouxin on 2023/11/6
 * @author zhouxin.3012143@bytedance.com
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
annotation class Trace


@Aspect
class TraceAspect {
    @Around("execution(@com.example.studyapplication.Trace * *(..))")
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint): Any? {
        val startMillis = System.currentTimeMillis()
        val result = joinPoint.proceed()
        val timeTakenMillis = System.currentTimeMillis() - startMillis
        Log.e("zhouxin-fdfsds", "${joinPoint.signature} took $timeTakenMillis ms")
        return result
    }
}