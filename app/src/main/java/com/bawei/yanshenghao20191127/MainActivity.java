package com.bawei.yanshenghao20191127;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.yanshenghao20191127.adapter.MyAdapter;
import com.bawei.yanshenghao20191127.base.BaseActivity;
import com.bawei.yanshenghao20191127.base.BasePresenter;
import com.bawei.yanshenghao20191127.bean.ShopBean;
import com.bawei.yanshenghao20191127.presenter.Presenter;
import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.ArrayList;
/**
 *  * @ActivityName: Cached
 *  * @Description: Activity介绍
 *  * @author: 闫圣豪
 *  * @date: 2019/11/27
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{


    private EditText edit;
    private Button sousuo;
    private FloatView aFloat;
    private String name;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<ShopBean.ResultBean> alist = new ArrayList<>();
    private MyAdapter myAdapter;
    private RecyclerView recy;

    @Override
    protected void startCoding() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter();
    }

    //获取id
    @Override
    protected void initView() {
        edit = findViewById(R.id.edit);
        sousuo = findViewById(R.id.sousuo);
        aFloat = findViewById(R.id.Float);
        //点击事件
        aFloat.setOnClickListener(this);
        sousuo.setOnClickListener(this);
        recy = findViewById(R.id.recy);
        //网格布局
        recy.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected int Layout() {
        return R.layout.activity_main;
    }

    @Override
    public void Success(String json) {
        //gosn解析数据
        ShopBean shopBean = new Gson().fromJson(json, ShopBean.class);
        alist.clear();
        alist.addAll(shopBean.getResult());
        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //点击搜索对流式布局进行判断
            case R.id.sousuo:
                name = edit.getText().toString().trim();
                if(name.isEmpty()){
                    Toast.makeText(this, "请输入参数", Toast.LENGTH_SHORT).show();
                }else{
                    if(!list.contains(name)){
                        aFloat.addTag(name);
                        list.add(name);
                    }else{
                        Toast.makeText(this, "已有重复内容", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
                //点击流式布局进行列表展示
            case R.id.Float:
                String url="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword="+ URLEncoder.encode(name)+"&page=1&count=5";
                mPresenter.start(url);
                myAdapter = new MyAdapter(MainActivity.this,alist);
                recy.setAdapter(myAdapter);
                myAdapter.setSetItemView(new MyAdapter.setItemView() {
                    @Override
                    public void getItem(int postion) {
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                break;
        }


    }
}
