package com.bawei.yanshenghao20191127.base;

import com.bawei.yanshenghao20191127.contract.Contract;

import java.lang.ref.WeakReference;
/**
 *  * @ActivityName: Cached
 *  * @Description: Activity介绍
 *  * @author: 闫圣豪
 *  * @date: 2019/11/27
 */
public abstract class BasePresenter <V extends Contract.IView> implements Contract.IPresnter {


    private WeakReference<V> vWeakReference;

    public BasePresenter(){
        initModel();
    }

    protected abstract void initModel();

    //引用
    protected void Attch(V iView){
        vWeakReference = new WeakReference<>(iView);
    }
    //销毁
    protected void onEnd(){
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference=null;
        }
    }
    protected V getView(){
        return vWeakReference.get();
    }

}
