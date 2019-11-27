package com.bawei.yanshenghao20191127;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.File;
/**
 *  * @ActivityName: Cached
 *  * @Description: Activity介绍
 *  * @author: 闫圣豪
 *  * @date: 2019/11/27
 */
public class Cached implements GlideModule {
    //sd卡缓存
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int mySize=1021*1021*20;
        File file = new File(Environment.getExternalStorageDirectory(),"aaaa");
        String path = file.getPath();
        builder.setDiskCache(new DiskLruCacheFactory(path,mySize));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }
}
