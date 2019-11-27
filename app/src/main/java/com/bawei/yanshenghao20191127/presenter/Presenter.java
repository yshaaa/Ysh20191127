package com.bawei.yanshenghao20191127.presenter;

import com.bawei.yanshenghao20191127.base.BasePresenter;
import com.bawei.yanshenghao20191127.contract.Contract;
import com.bawei.yanshenghao20191127.model.Model;
/**
 *  * @ActivityName: Cached
 *  * @Description: Activity介绍
 *  * @author: 闫圣豪
 *  * @date: 2019/11/27
 */
public class Presenter extends BasePresenter {
    private Contract.IModel model;
    @Override
    protected void initModel() {
        model=new Model();
    }

    @Override
    public void start(String url) {
        model.getInfo(url, new Contract.Mycallback() {
            @Override
            public void Success(String json) {
                getView().Success(json);
            }

            @Override
            public void Error(String error) {
                getView().Error(error);
            }
        });
    }
}
