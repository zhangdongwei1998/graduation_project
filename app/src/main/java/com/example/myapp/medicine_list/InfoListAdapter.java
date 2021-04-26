package com.example.myapp.medicine_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.bean.MedicineBean;

import java.util.List;

/**
 * Author zhangdongwei
 */
public class InfoListAdapter extends BaseAdapter {
    Context context;
    List<MedicineBean> mDates;

    public InfoListAdapter(Context context, List<MedicineBean> mDates) {
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
        ViewHolder holder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_infolist_lv,null );//将布局转换成view对象的方法
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        //加载控件显示的内容
        //获取集合指定位置的数据
        MedicineBean foodBean=mDates.get(position);
        holder.titleTv.setText(foodBean.getTitle());
        holder.notTv.setText("药物类别："+foodBean.getNotmatch());
        System.out.println("图片："+foodBean.getPicId());
        holder.iv.setImageResource(foodBean.getPicId());
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
