package common.lib;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;


/**
 * Introduction: 字体样式改变工具
 */
public class SpannableStringUtil {
    /**
     * 设置连接文本
     *
     * @param spanString 文本修饰类
     * @param url        域名地址
     * @param start      开始的文字位置
     * @param end        到结束的文字位置
     */
    public static void addUrlSpan(SpannableString spanString, String url, int start, int end) {
        URLSpan span = new URLSpan(url);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置背景色文本
     *
     * @param spanString 文本修饰类
     * @param color      文本颜色
     * @param start      开始的文字位置
     * @param end        到结束的文字位置
     */
    public static void addBackColorSpan(SpannableString spanString, int color, int start, int end) {
        BackgroundColorSpan span = new BackgroundColorSpan(color);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置文本顏色
     *
     * @param spanString 文本修饰类
     * @param color      文本颜色
     * @param start      开始的文字位置
     * @param end        到结束的文字位置
     */
    public static void addForeColorSpan(SpannableString spanString, int color, int start, int end) {
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置文本顏色
     *
     * @param str   文本修饰类
     * @param color 文本颜色
     * @param start 开始的文字位置
     * @param end   到结束的文字位置
     */
    public static SpannableString addForeColorSpan(String str, int color, int start, int end) {
        SpannableString spanString = new SpannableString(str);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }

    /**
     * 设置文本大小
     *
     * @param spanString 文本修饰类
     * @param textSize   文字大小
     * @param start      开始的文字位置
     * @param end        到结束的文字位置
     */
    public static void addFontSpan(SpannableString spanString, int textSize, int start, int end) {
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(textSize);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置文本状态
     *
     * @param spanString 文本修饰类
     * @param style      0为正常，1加粗，2斜体，3加粗和斜体
     * @param start      开始的文字位置
     * @param end        到结束的文字位置
     */
    public static void addStyleSpan(SpannableString spanString, int style, int start, int end) {
        StyleSpan span = new StyleSpan(style);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置删除线
     *
     * @param spanString 文本修饰类
     * @param start      开始的文字位置
     * @param end        到结束的文字位置
     */
    public static void addStrikeSpan(SpannableString spanString, int start, int end) {
        StrikethroughSpan span = new StrikethroughSpan();
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置下划线
     *
     * @param spanString 文本修饰类
     * @param start      开始的文字位置
     * @param end        到结束的文字位置
     */
    public static void addUnderLineSpan(SpannableString spanString, int start, int end) {
        UnderlineSpan span = new UnderlineSpan();
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    /**
     * 设置图片
     *
     * @param spanString 文本修饰类
     * @param drawable   图片
     * @param start      图片的开始位置
     * @param end        到图片结束的位置
     */
    public static void addImageSpan(SpannableString spanString, Drawable drawable, int start, int end) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    public static void addImageSpanAlignBottom(SpannableString spanString, Drawable drawable, int start, int end) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

}
