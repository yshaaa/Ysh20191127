package com.bawei.yanshenghao20191127.contract;

public interface Contract {

    //契约类
    //m层
    interface IModel{
        void getInfo(String url,Mycallback mycallback);
    }
    //v层
    interface IView{
        void Success(String json);
        void Error(String error);
    }
    //p层
    interface IPresnter{
        void start(String url);
    }
    interface Mycallback{
        void Success(String json);
        void Error(String error);
    }
}
