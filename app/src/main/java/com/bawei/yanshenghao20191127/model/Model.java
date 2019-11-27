package com.bawei.yanshenghao20191127.model;

import com.bawei.yanshenghao20191127.contract.Contract;
import com.bawei.yanshenghao20191127.util.NetUtil;
/**
 *  * @ActivityName: Cached
 *  * @Description: Activity介绍
 *  * @author: 闫圣豪
 *  * @date: 2019/11/27
 */
public class Model implements Contract.IModel {
    @Override
    public void getInfo(String url, final Contract.Mycallback mycallback) {
        NetUtil.getInstance().get(url, new NetUtil.MyCallBack() {
            @Override
            public void onSuccess(String json) {
                mycallback.Success(json);
            }

            @Override
            public void onError(String error) {
                mycallback.Error(error);
            }
        });
    }
}
