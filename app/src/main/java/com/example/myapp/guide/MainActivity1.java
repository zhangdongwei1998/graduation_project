package com.example.myapp.guide;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.myapp.HomeMenuActivity;
import com.example.myapp.MainActivity;
import com.example.myapp.R;

public class MainActivity1 extends AppCompatActivity {

    TextView tv;
    int time=5;
    SharedPreferences preferences;//存储键值对数据
    private SharedPreferences.Editor editor;


    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                time--;
                if(time==0){
                    //跳转页面
                    Intent intent=new Intent();
                    boolean isfirst=preferences.getBoolean("isfirst",true);
                    if(isfirst){
                        intent.setClass(MainActivity1.this, GuideActivity.class);
                        editor.putBoolean("isfirst",false);//写入不是第一次进入的记录
                        editor.commit();//提交本次修改记录
                    }else {
                        intent.setClass(MainActivity1.this, MainActivity.class);
                    }

                    startActivity(intent);
                    finish();
                }else {
                    //设置
                    tv.setText(time+"");
                    handler.sendEmptyMessageDelayed(1,1000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        tv=findViewById(R.id.main_tv);
        preferences=getSharedPreferences("health_pref",MODE_PRIVATE);
        editor=preferences.edit();//写入数据的对象
        handler.sendEmptyMessageDelayed(1,1000);
    }
}