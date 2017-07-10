package com.linkct.hzdlzcgl.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.linkct.hzdlzcgl.utils.FileUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

/**
 * Created by wlh on 2017/7/4.
 */

public class APP extends Application {

    private DisplayImageOptions mNormalImageOptions;

    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context context) {

        mNormalImageOptions = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565).cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .denyCacheImageMultipleSizesInMemory()
                .defaultDisplayImageOptions(mNormalImageOptions)
                .discCache(
                        new UnlimitedDiskCache(new File(FileUtils.CACHE_PATH)))
                // .discCacheFileNameGenerator(new
                // Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .threadPriority(Thread.NORM_PRIORITY - 2).threadPoolSize(3)
                .build();

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
