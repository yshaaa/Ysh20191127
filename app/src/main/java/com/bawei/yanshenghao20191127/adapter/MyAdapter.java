package com.bawei.yanshenghao20191127.adapter;
/**
 *  * @ActivityName: Cached
 *  * @Description: Activity介绍
 *  * @author: 闫圣豪
 *  * @date: 2019/11/27
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.yanshenghao20191127.MainActivity;
import com.bawei.yanshenghao20191127.R;
import com.bawei.yanshenghao20191127.bean.ShopBean;
import com.bawei.yanshenghao20191127.glide.GlideUtil;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MainActivity mainActivity;
    private ArrayList<ShopBean.ResultBean> alist;
    private final int one=1;
    private final int two=2;

    public MyAdapter(MainActivity mainActivity, ArrayList<ShopBean.ResultBean> alist) {

        this.mainActivity = mainActivity;
        this.alist = alist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        View view=null;
        switch (viewType){
            case one:
                view= LayoutInflater.from(mainActivity).inflate(R.layout.item,null);
                holder= new OneHolder(view);
                break;
            case two:
                view= LayoutInflater.from(mainActivity).inflate(R.layout.item,null);
                holder= new TwoHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof OneHolder){
            ((OneHolder) holder).name.setText(alist.get(position).getCommodityName());
            ((OneHolder) holder).price.setText(alist.get(position).getPrice()+"");
            GlideUtil.LoadImage(alist.get(position).getMasterPic(),((OneHolder) holder).imageView);
        }else if(holder instanceof TwoHolder){
            ((TwoHolder) holder).name.setText(alist.get(position).getCommodityName());
            ((TwoHolder) holder).price.setText(alist.get(position).getPrice()+"");
            GlideUtil.LoadImage(alist.get(position).getMasterPic(),((TwoHolder) holder).imageView);
        }

              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      if (setItemView != null) {
                          setItemView.getItem(position);
                      }
                  }
              });
    }

    @Override
    public int getItemCount() {
        return alist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==1){
            return one;
        }else{
            return two;
        }
    }



    private class OneHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;
        public OneHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.image);
            name=view.findViewById(R.id.name);
            price=view.findViewById(R.id.price);
        }
    }

    private class TwoHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;
        public TwoHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.image);
            name=view.findViewById(R.id.name);
            price=view.findViewById(R.id.price);
        }
    }


    //定义接口回调
    public interface setItemView{
        void getItem(int postion);
    }
    private  setItemView setItemView;
    public  void setSetItemView(setItemView setItemView){
        this.setItemView=setItemView;
    }
}
