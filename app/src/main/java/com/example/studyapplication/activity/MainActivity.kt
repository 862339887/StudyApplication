package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyapplication.AnrTestActivity
import com.example.studyapplication.adapter.EntranceListViewAdapter
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private var activityGroup = mutableListOf<Pair<String, Any>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initActivityGroup()
        initView()

    }

    private fun initActivityGroup() {
        activityGroup.apply {
            add(Pair("Test3DRotateActivity", Test3DRotateActivity::class.java))
            add(Pair("EqualsTestActivity", EqualsTestActivity::class.java))
            add(Pair("SwitchTestActivity", SwitchTestActivity::class.java))
            add(Pair("ReflectActivity", ReflectActivity::class.java))
            add(Pair("LogTestActivity", LogTestActivity::class.java))
            add(Pair("ViewPagerFragmentActivity", ViewPagerFragmentActivity::class.java))
            add(Pair("DialogActivity", DialogActivity::class.java))
            add(Pair("DrawableTextViewActivity", DrawableTextViewActivity::class.java))
            add(Pair("ValueTestActivity", ValueTestActivity::class.java))
            add(Pair("LifeCycleTestActivity", LifeCycleTestActivity::class.java))
            add(Pair("CoroutineScopeActivity", CoroutineScopeActivity::class.java))
            add(Pair("CanvasDrawActivity", CanvasDrawActivity::class.java))
            add(Pair("DialogLifeCycleActivity", DialogLifeCycleActivity::class.java))
            add(Pair("RecyclerViewTestActivity", RecyclerViewTestActivity::class.java))
            add(Pair("TimerTestActivity", TimerTestActivity::class.java))
            add(Pair("FileTestActivity", FileTestActivity::class.java))
            add(Pair("KotlinFunctionActivity", KotlinFunctionActivity::class.java))
            add(Pair("AssetsActivity", AssetsActivity::class.java))
            add(Pair("ViewTestActivity", ViewTestActivity::class.java))
            add(Pair("AnimationActivity", AnimationActivity::class.java))
            add(Pair("EditTextActivity", EditTextActivity::class.java))
            add(Pair("TimeTestActivity", TimeTestActivity::class.java))
            add(Pair("LiveDataActivity", LiveDataActivity::class.java))
            add(Pair("SnackBarActivity", SnackBarActivity::class.java))
            add(Pair("PopWindowTestActivity", PopWindowTestActivity::class.java))
            add(Pair("NormalZoomImageActivity", NormalZoomImageActivity::class.java))
            add(Pair("ZoomImgViewPagerActivity", ZoomImgViewPagerActivity::class.java))
            add(Pair("ZoomImageViewPagerActivity2", ZoomImageViewPagerActivity2::class.java))
            add(Pair("DispatchEventTestActivity", DispatchEventTestActivity::class.java))
            add(Pair("ZoomImageActivity", ZoomImageActivity::class.java))
            add(Pair("ActivityForResultActivity", ActivityForResultActivity::class.java))
            add(Pair("TestActivity", TestActivity::class.java))
            add(Pair("MatrixActivity", MatrixActivity::class.java))
            add(Pair("MaskActivity", MaskActivity::class.java))
            add(Pair("TestActivity1", TestActivity1::class.java))
            add(Pair("ShareActivity", ShareActivity::class.java))
            add(Pair("SerializableTestActivity", SerializableTestActivity::class.java))
            add(Pair("MeasureText11Activity", MeasureText11Activity::class.java))
            add(Pair("BitmapRecycleActivity", BitmapRecycleActivity::class.java))
            add(Pair("BitmapMemoryTestActivity", BitmapMemoryTestActivity::class.java))
            add(Pair("BitmapTestCompressActivity", BitmapTestCompressActivity::class.java))
            add(Pair("LeetCodeActivity", LeetCodeActivity::class.java))
            add(Pair("SpanTestActivity", SpanTestActivity::class.java))
            add(Pair("AnrTestActivity", AnrTestActivity::class.java))
            add(Pair("TraceTestActivity", TraceTestActivity::class.java))
            add(Pair("TraceActivity", TraceActivity::class.java))
            add(Pair("NetApiTestActivity", NetApiTestActivity::class.java))
            add(Pair("MemoryTestActivity", MemoryTestActivity::class.java))
        }
    }

    private fun initView() {
        val adapter = EntranceListViewAdapter()
        adapter.initData(activityGroup)
        study_entrance.adapter = adapter
    }
}