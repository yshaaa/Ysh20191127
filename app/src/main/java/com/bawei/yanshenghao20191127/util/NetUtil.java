package com.bawei.yanshenghao20191127.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.yanshenghao20191127.app.MyApp;

import java.util.Map;
/**
 *  * @ActivityName: Cached
 *  * @Description: Activity介绍
 *  * @author: 闫圣豪
 *  * @date: 2019/11/27
 */
public class NetUtil {

    private final RequestQueue requestQueue;

    private NetUtil(){
        requestQueue = Volley.newRequestQueue(MyApp.context);
    }
    private static class NetUrl{
        private static NetUtil netUtil=new NetUtil();
    }
    public static NetUtil getInstance(){
        return NetUrl.netUtil;
    }

    //volley的get方法
    public void get(String url, final MyCallBack myCallBack){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             myCallBack.onError(error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
    }

    //volley的post方法
    public void post(String url, final Map<String,String>map, final MyCallBack myCallBack){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myCallBack.onError(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (map != null) {
                    return map;
                }
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }

    //联网判断
    public boolean hasNext(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo==null&&activeNetworkInfo.isAvailable()){
            return true;
        }else{
            return false;
        }
    }

    public boolean Wifi(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo==null&&activeNetworkInfo.isAvailable()&&activeNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            return true;
        }else{
            return false;
        }
    }

    public boolean phone(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo==null&&activeNetworkInfo.isAvailable()&&activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            return true;
        }else{
            return false;
        }
    }


    public interface MyCallBack{
        void onSuccess(String json);
        void onError(String error);
    }

}
