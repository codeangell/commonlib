package common.lib

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.graphics.Color
import android.support.design.widget.Snackbar
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import java.util.regex.Pattern

/**
 * Created by liuzipeng on 2017/2/15.
 */

val View.ctx: Context
    get() = context

fun Activity.showSnackBar(view: View, msg: String, time: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, msg, time).show()
}

fun Activity.showSnackBar(view: View, msg: String, time: Int = Snackbar.LENGTH_SHORT, actionMsg: String = "重试", action: (View) -> Unit) {
    Snackbar.make(view, msg, time).setAction(actionMsg, View.OnClickListener { action.invoke(view) }).show()
}

fun Fragment.showSnackBar(view: View, msg: String, time: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, msg, time).show()
}

fun Fragment.showSnackBar(view: View, msg: String, time: Int = Snackbar.LENGTH_SHORT, actionMsg: String = "重试", action: (View) -> Unit) {
    Snackbar.make(view, msg, time).setAction(actionMsg, View.OnClickListener { action.invoke(view) }).show()
}

fun Any.log(msg: String?) {
    Log.d(this.javaClass.simpleName, msg)
}

fun CharSequence.getCustomLenth() : Int {
    var unicodeCount = 0
    for (i in 0..length - 1) {
        val c = get(i)
        if (Pattern.matches("^[\\u4E00-\\u9FFF]+$", c.toString())) {
            unicodeCount++
            unicodeCount++
        } else {
            unicodeCount++
        }
    }
    return unicodeCount

}

fun CharSequence.showPartStr(front:Int, end:Int) : CharSequence {
    if (length < front + end) {
        return this
    }
    val len = length
    val str = StringBuilder()
    for (i in 0..len - front - end - 1) {
        str.append("*")
    }
    return substring(0, front) + str.toString() + substring(len - end)
}

fun CharSequence.maxLength(maxLength:Int) : CharSequence {
    var maxLength = maxLength * 2
    if (getCustomLenth() <= maxLength) {
        return this
    }
    var cs = subSequence(0, if (length >= maxLength) maxLength else length)
    while (true) {
        if (getCustomLenth() > maxLength) {
            cs = cs.subSequence(0, cs.length - 1)
        } else {
            break
        }
    }
    return "${cs}..."
}

fun CharSequence.setKeyWordHighlight(keyword:String) : CharSequence {
    if (TextUtils.isEmpty(keyword)) {
        return this
    }
    val s = SpannableString(this)
    val p = Pattern.compile(keyword)
    val m = p.matcher(s)
    while (m.find()) {
        val start = m.start()
        val end = m.end()
        s.setSpan(ForegroundColorSpan(Color.RED), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    return s
}
