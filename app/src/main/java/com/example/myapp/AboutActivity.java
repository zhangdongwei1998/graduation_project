package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapp.adapter.AboutAdapter;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager aboutVp;
    TextView shareTv;
    LinearLayout pointLayout;
    List<View>viewList;//viewpager的数据源
    int []picIds={R.drawable.ab1,R.drawable.ab2,R.drawable.ab3,R.drawable.ab4,R.drawable.ab5};
    private AboutAdapter adapter;
    List<ImageView>pointList;//存放显示器小点点的集合

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                //接收到消息后，需要使ViewPager页面向下滑动一页
                int currentItem=aboutVp.getCurrentItem();
                aboutVp.setCurrentItem(currentItem+1);
                handler.sendEmptyMessageDelayed(1,5000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutVp=findViewById(R.id.about_vp);
        shareTv=findViewById(R.id.about_tv_share);
        pointLayout=findViewById(R.id.about_layout_point);
        shareTv.setOnClickListener(this);
        viewList=new ArrayList<>();
        pointList=new ArrayList<>();

        //初始化viewpager的页面信息
        for(int i=0;i<picIds.length;i++){
            View view= LayoutInflater.from(this).inflate(R.layout.item_aboutvp,null);
            ImageView iv=view.findViewById(R.id.item_aboutvp_iv);
            iv.setImageResource(picIds[i]);
            viewList.add(view);
            //创建指示器内容
            ImageView pointIv=new ImageView(this);
            //在代码中设置控件的宽高和外边距等属性
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,20,0);
            //将布局参数设置给ImageView
            pointIv.setLayoutParams(lp);
            pointIv.setImageResource(R.drawable.a2);
            pointList.add(pointIv);//添加到集合当中便于统一管理
            pointLayout.addView(pointIv);//添加到布局当中显示出来
        }
        pointList.get(0).setImageResource(R.drawable.a3);//设置第一个小圆点为选中状态
        //创建适配器对象
        adapter=new AboutAdapter(viewList);
        //设置适配器
        aboutVp.setAdapter(adapter);
        //发送切换页面消息
        handler.sendEmptyMessageDelayed(1,5000);
        //设置ViewPager页面监听器
        setVPListener();

    }

    private void setVPListener() {
        //设置ViewPager的监听器
        aboutVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<pointList.size();i++){
                    pointList.get(i).setImageResource(R.drawable.a2);
                }
                pointList.get(position%pointList.size()).setImageResource(R.drawable.a3);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        //调用系统自带的分享功能
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String msg="个人健康非常的重要，了解饮食各种药物和管理，查询正确的药品，让你变得更健康，想要了解的更多吗，快来下载个人药管家app吧~";
        intent.putExtra(intent.EXTRA_TEXT,msg);
        startActivity(Intent.createChooser(intent,"个人药管家分享"));
    }
}