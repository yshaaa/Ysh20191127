package com.bawei.yanshenghao20191127.glide;

import android.widget.ImageView;

import com.bawei.yanshenghao20191127.R;
import com.bawei.yanshenghao20191127.app.MyApp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;

/**
 *  * @ActivityName: Cached
 *  * @Description: Activity介绍
 *  * @author: 闫圣豪
 *  * @date: 2019/11/27
 */
public class GlideUtil {

    public static void LoadImage(String url, ImageView imageView){
        Glide.with(MyApp.context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new CenterCrop()))
                .priority(Priority.HIGH)
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

}
