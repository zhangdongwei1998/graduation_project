package com.example.myapp.medicine_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.bean.ShopMedicalUtils;
import com.example.myapp.bean.Medicine;
import com.example.myapp.bean.MyOpenHelper;
import com.example.myapp.medicine_grid.ShopMedicineDescActivity;

import java.util.ArrayList;
import java.util.List;

public class InfoListActivity extends AppCompatActivity implements View.OnClickListener  {

    List<Medicine> medicineList;
    MyOpenHelper mOpenHelper;
    SQLiteDatabase db;
    testAdapter adapter;

    List<Medicine> allMedicineList;

    EditText searchEt;
    ImageView searchIv,flushIv;
    ListView showLv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        medicineList = new ArrayList<Medicine>();
        // 创建MyOpenHelper实例
        mOpenHelper = new MyOpenHelper(this);
        // 得到数据库
        db = mOpenHelper.getWritableDatabase();
        // 插入数据
//        Insert();
        initView();
        // 查询数据
        Query();
        // 创建MyAdapter实例
        allMedicineList = new ArrayList<Medicine>();
        allMedicineList.addAll(medicineList);
        adapter = new testAdapter(this,medicineList);
        // 向listview中添加Adapter
        showLv.setAdapter(adapter);
        setListener();
    }


    private void initView() {
        searchEt=findViewById(R.id.info_et_search);
        searchIv=findViewById(R.id.info_iv_search);
        flushIv=findViewById(R.id.info_iv_flush);
        showLv=findViewById(R.id.infolist_lv);
        searchIv.setOnClickListener(this);//添加点击事件的监听器
        flushIv.setOnClickListener(this);
    }

    // 插入数据
    public void Insert() {
        String[] foods = ShopMedicalUtils.food;
        String[] des = ShopMedicalUtils.foodjianjie;
        int[] pic = ShopMedicalUtils.resId;
        String[] cate = ShopMedicalUtils.food1;
        int len = foods.length;
        for(int i=0;i<len;i++){
            ContentValues values = new ContentValues();
//            Log.i("I am in", foods[i]);
            values.put("name", foods[i]);
            values.put("category", cate[i]);
            values.put("description", des[i]);
            values.put("picId",pic[i]+"");
            long a = db.insert("person", null, values);
//            Log.i("insert", "success"+a);
        }
    }

    // 查询数据
    public void Query() {
        Cursor cursor = db.query("person", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String _id = cursor.getString(0);
            String name = cursor.getString(1);
            String category = cursor.getString(2);
            String description = cursor.getString(3);
            String picId=cursor.getString(4);
            Medicine medicine = new Medicine(_id, name, category, description,picId);
            medicineList.add(medicine);
        }
    }



    private void setListener() {
        showLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medicine medicine=medicineList.get(position);
                Intent intent=new Intent(InfoListActivity.this, ShopMedicineDescActivity.class);
                intent.putExtra("food", medicine);
                intent.putExtra("code","1");
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.info_iv_flush://刷新点击
                searchEt.setText("");
                medicineList.clear();
                medicineList.addAll(allMedicineList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.info_iv_search://搜索点击
                //1、获取输入内容，判断不为空
                String msg=searchEt.getText().toString().trim();//获取输入信息
                if(TextUtils.isEmpty(msg)){
                    Toast.makeText(this,"输入内容不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断所有食物列表的标题是否包含输入内容，如果包含，就添加到小集合中
                List<Medicine> list=new ArrayList<>();
                for(int i=0;i<medicineList.size();i++){
                    String title=medicineList.get(i).getName();
                    if(title.contains(msg)){
                        list.add(medicineList.get(i));
                    }
                }
                medicineList.clear();//清空listview的适配器数据源内容
                medicineList.addAll(list);//添加新的数据到数据源中
                adapter.notifyDataSetChanged();//提示适配器更新
                break;
        }
    }
}