package com.example.myapp.medicine_grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.bean.Medicine;

import java.util.List;

/**
 * Author zhangdongwei
 */
public class testAdapter1 extends BaseAdapter {

    Context context;
    List<Medicine> mDates;

    public testAdapter1(Context context, List<Medicine> mDates) {
        this.context = context;
        this.mDates = mDates;
    }

    @Override
    public int getCount() {
        return mDates.size();
    }

    @Override
    public Object getItem(int position) {
        return mDates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1、声明ViewHolder
        testAdapter1.ViewHolder holder=null;
        if(convertView==null){
            //2、判断是否有复用的view，如果没有就创建
            convertView= LayoutInflater.from(context).inflate(R.layout.item_foodgrid,null);
            holder= new testAdapter1.ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder=(testAdapter1.ViewHolder) convertView.getTag();
        }
        //获取指定位置的数据
        Medicine medicine=mDates.get(position);

        holder.tv.setText(medicine.getName());
//        System.out.println("图片："+medicine.getPicId());
        holder.iv.setImageResource(Integer.parseInt(medicine.getPicId()));
        return convertView;
    }

    //将view所有控件封装在一个类中
    class ViewHolder{
        ImageView iv;
        TextView tv;
        public ViewHolder(View view){
            iv=view.findViewById(R.id.item_grid_iv);
            tv=view.findViewById(R.id.item_grid_tv);
        }
    }
    }
