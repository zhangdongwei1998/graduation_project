package com.example.myapp.medicine_list;

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
public class testAdapter extends BaseAdapter {

        Context context;
        List<Medicine> mDates;

        public testAdapter(Context context, List<Medicine> mDates) {
            this.context = context;
            this.mDates = mDates;
        }

        //决定了ListView列表展示的行数

        @Override
        public int getCount() {
            return mDates.size();
        }

        //返回指定位置对应的数据
        @Override
        public Object getItem(int position) {
            return mDates.get(position);
        }

        //返回指定位置所对应的id
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            testAdapter.ViewHolder holder=null;
            if(convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.item_infolist_lv,null );//将布局转换成view对象的方法
                holder= new testAdapter.ViewHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder=(testAdapter.ViewHolder) convertView.getTag();
            }
            //加载控件显示的内容
            //获取集合指定位置的数据
            Medicine medicine=mDates.get(position);
            holder.titleTv.setText(medicine.getName());
            holder.notTv.setText("药物类别："+medicine.getCategory());
//            System.out.println("图片："+medicine.getPicId());
            holder.iv.setImageResource(Integer.parseInt(medicine.getPicId()));
            return convertView;
        }

        class ViewHolder{
            ImageView iv;
            TextView titleTv,notTv;
            public ViewHolder(View view){
                iv=view.findViewById(R.id.item_info_iv);
                titleTv=view.findViewById(R.id.item_info_tv_title);
                notTv=view.findViewById(R.id.item_info_notmatch);
            }
        }
    }
