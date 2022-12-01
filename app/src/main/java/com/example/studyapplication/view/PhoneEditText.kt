package com.example.studyapplication.view

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatEditText
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * @description:
 * @author: zgl
 * @date: 2020-09-22 14:05
 */

class PhoneEditText : AppCompatEditText, TextWatcher {
    private var preCharSequence: String? = null
    private var onPhoneEditTextChangeListener: OnPhoneEditTextChangeListener? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    interface OnPhoneEditTextChangeListener {
        fun onTextChange(s: String, isPhoneNumber: Boolean)
    }

    fun setOnPhoneEditTextChangeListener(listener: OnPhoneEditTextChangeListener?) {
        onPhoneEditTextChangeListener = listener
    }

    private fun initView() {
        //设置输入过滤器
        filters = arrayOf(
            InputFilter { source, start, end, spanned, dstart, dend ->
                if (TextUtils.equals(source, preCharSequence)) {
                    return@InputFilter null
                }

                if (" " == source.toString() || source.toString().contentEquals("\n")
                    || dstart == 13
                ) {
                    ""
                } else if (getPhoneText().length == 11) {
                    ""
                } else {
                    null
                }
            },
            InputFilter { source, _, _, _, _, _ ->
                val speChat =
                    "[`~!@#$%^&*()+\\-=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？wp]"
                val pattern: Pattern = Pattern.compile(speChat)
                val matcher: Matcher = pattern.matcher(source.toString())
                if (matcher.find()) {
                    ""
                } else {
                    null
                }
            }
        )
    }

    override fun beforeTextChanged(
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16.0f)
    }

    override fun onTextChanged(
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        if (TextUtils.isEmpty(s)) {
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16.0f)
        } else {
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24.0f)
        }


        if (TextUtils.equals(preCharSequence, s)) {
            return
        }
        if (null != onPhoneEditTextChangeListener) {

            val phoneText = getPhoneText()
            onPhoneEditTextChangeListener!!.onTextChange(phoneText, isMobile(phoneText))
        }
        if (s == null || s.isEmpty()) {
            return
        }
        val sb = StringBuilder()
        for (i in s.indices) {
            if (i != PHONE_INDEX_3 && i != PHONE_INDEX_8 && s[i] == ' '
            ) {
                continue
            } else {
                sb.append(s[i])
                if ((sb.length == PHONE_INDEX_4 || sb.length == PHONE_INDEX_9)
                    && sb[sb.length - 1] != ' '
                ) {
                    sb.insert(sb.length - 1, ' ')
                }
            }
        }

        //这里主要处理添加空格后的字符串，before=0为输入字符，before=1为删除字符，将光标移动到正确的位置
        if (sb.isNotEmpty() && sb.toString() != s.toString()) {
            var index = start + 1
            if (sb[start] == ' ') {
                if (before == 0) {
                    index++
                } else {
                    index--
                }
            } else {
                if (before == 1) {
                    index--
                }
            }
            preCharSequence = sb.toString()
            setText(sb.toString())

            try {
                setSelection(index)
            } catch (e: Exception) {

            }
        }
    }

    override fun afterTextChanged(s: Editable?) {}

    private fun getPhoneText(): String {
        val str = text.toString()
        return replaceBlank(str)
    }

    private fun replaceBlank(str: String?): String {
        var dest = ""
        if (str != null) {
            val p: Pattern = Pattern.compile("\\s*|\t|\r|\n")
            val m: Matcher = p.matcher(str)
            if (m.find()) {
                dest = m.replaceAll("")
            }
        }
        return dest
    }

    private fun isMobile(str: String?): Boolean {
        var pattern: Pattern? = null
        var matcher: Matcher? = null
        pattern = Pattern.compile("^[1][2,3,4,5,6,7,8,9][0-9]{9}$") // 验证手机号
        matcher = pattern.matcher(str)
        return matcher.matches()
    }

    companion object {
        // 特殊下标位置
        private const val PHONE_INDEX_3 = 3
        private const val PHONE_INDEX_4 = 4
        private const val PHONE_INDEX_8 = 8
        private const val PHONE_INDEX_9 = 9
    }
}