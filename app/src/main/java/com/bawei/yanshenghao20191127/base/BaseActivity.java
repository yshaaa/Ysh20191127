package com.bawei.yanshenghao20191127.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.yanshenghao20191127.contract.Contract;
/**
 *  * @ActivityName: Cached
 *  * @Description: Activity介绍
 *  * @author: 闫圣豪
 *  * @date: 2019/11/27
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements Contract.IView {
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Layout()!=0){
            setContentView(Layout());
            initView();
            mPresenter=initPresenter();
            mPresenter.Attch(this);
            startCoding();
        }
    }

    protected abstract void startCoding();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract int Layout();

    //内存泄露
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onEnd();
        }
    }
}
