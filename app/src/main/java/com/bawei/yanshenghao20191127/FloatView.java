package com.bawei.yanshenghao20191127;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 *  * @ActivityName: Cached
 *  * @Description: Activity介绍
 *  * @author: 闫圣豪
 *  * @date: 2019/11/27
 */
public class FloatView extends ViewGroup {
    private Context context;
    public FloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    //摆放
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //各个方向初始值
        int left=0;
        int right=0;
        int top=0;
        int bottom=0;
        int space=10;
        //获取总数
        int childCount = getChildCount();
        for (int j = 0; j < childCount; j++) {
            //获取子条目
            View childAt = getChildAt(j);
            childAt.measure(0,0);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            //位置下一次出现的方位
            left=space+right;
            right=left+measuredWidth;
            int width=getWidth();
            if(right>width){
                left=space;
                top=bottom+space;
            }
            right=left+measuredWidth;
            bottom=top+measuredHeight;
            childAt.layout(left,top,right,bottom);
        }
    }
    //颜色，字体大小等等
    public void addTag(String content){
        TextView textView = new TextView(context);
        textView.setTextColor(Color.GREEN);
        textView.setTextSize(14);
        textView.setText(content);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.style);
        addView(textView);
    }
}
