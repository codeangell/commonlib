package common.lib;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by libin on 2015/4/1.
 */
public class DensityUtil {

    // 根据屏幕密度转换
    private static float mPixels = 0.0F;
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }
    /**
     * metric.widthPixels;  // 屏幕宽度（像素）
     * metric.heightPixels;  // 屏幕高度（像素）
     * metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
     * metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    public static int getImageWeidth(Context context , float pixels) {
        return context.getResources().getDisplayMetrics().widthPixels - 66 - getDisplayMetrics(context, pixels);
    }

    /**
     *
     * @param context
     * @param pixels
     * @return
     */
    public static int getDisplayMetrics(Context context, float pixels) {
        if (mPixels == 0.0F)
            mPixels = context.getResources().getDisplayMetrics().density;
        return (int) (0.5F + pixels * mPixels);
    }
    private static float density;
    /**
     *
     * @param context
     * @return
     */
    public static float getDensity(Context context) {
        if (density < 0.0F){
            density = context.getResources().getDisplayMetrics().density;
        }
        return density;
    }
}
