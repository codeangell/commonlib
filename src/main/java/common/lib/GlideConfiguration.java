package common.lib;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public class GlideConfiguration extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //自定义缓存目录，磁盘缓存给200M
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "image_cache", 200 * 1024 * 1024));
        builder.setMemoryCache(new LruResourceCache(20 * 1024 * 1024));

    }

}