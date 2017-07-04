package common.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Date: 2015/11/18
 * Time: 14:29
 * Introduction:图片加载
 */
public class ImageLoader {


    /**
     * 通过URL加载图片
     * @param context 使用 ApplicationContext
     * @param url
     * @param imageView
     */
    public static void loadByUrl(Context context, String url, ImageView imageView) {
        GlideApp.with(context).load(url).transition(new DrawableTransitionOptions().crossFade(200)).into(imageView);
    }

    /**
     * 通过URL加载图片,并设置请求时的默认图片以及请求失败时的图片
     * @param context
     * @param url
     * @param imageView
     * @param holderResId 请求中的图片
     * @param failResId  请求失败时的图片
     */
    public static void loadByUrl(Context context, String url, ImageView imageView, int holderResId, int failResId) {
        GlideApp.with(context).load(url).transition(new DrawableTransitionOptions().crossFade(200)).placeholder(holderResId).error(failResId).into(imageView);
    }

    /**
     * 通过URL加载图片,并设置请求失败时的图片
     * @param context
     * @param url
     * @param imageView
     * @param failResId  请求失败时的图片
     */
    public static void loadByUrl(Context context, String url, ImageView imageView, int failResId) {
        GlideApp.with(context).load(url).transition(new DrawableTransitionOptions().crossFade(200)).error(failResId).into(imageView);
    }
    public static void loadSize(Context context, String url, ImageView imageView, int failResId, int size) {
        GlideApp.with(context).load(url).transition(new DrawableTransitionOptions().crossFade(200)).error(failResId).into(imageView);
    }

    public static String getImagePath(Context context, String url) {
        FutureTarget<File> futureTask = GlideApp.with(context).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        try {
            File file = futureTask.get();
            return file.getAbsolutePath();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
