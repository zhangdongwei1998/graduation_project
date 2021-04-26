package com.example.myapp.medicine_grid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.bean.SelfMedicalUtils;
import com.example.myapp.bean.Medicine;
import com.example.myapp.bean.MyOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MedicineGridActivity extends AppCompatActivity {

    List<Medicine> medicineList;
    MyOpenHelper mOpenHelper;
    SQLiteDatabase db;
    testAdapter1 adapter;
    List<Medicine> allMedicineList;

    GridView showGv;
    TextView showTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_grid);
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
        adapter = new testAdapter1(this,medicineList);
        // 向Gridview中添加Adapter
        showGv.setAdapter(adapter);
        setListener();
    }


    private void initView() {
        showGv=findViewById(R.id.food_grid_gv);
        showTv=findViewById(R.id.grid_title);
    }

    // 插入数据
    public void Insert() {
        String[] foods = SelfMedicalUtils.food;
        String[] des = SelfMedicalUtils.foodjianjie;
        int[] pic = SelfMedicalUtils.resId;
        String[] cate = SelfMedicalUtils.food1;
        int len = foods.length;
        for(int i=0;i<len;i++){
            ContentValues values = new ContentValues();
//            Log.i("I am in", foods[i]);
            values.put("name", foods[i]);
            values.put("category", cate[i]);
            values.put("description", des[i]);
            values.put("picId",pic[i]+"");
            long a = db.insert("person1", null, values);
//            Log.i("insert", "success"+a);
        }
    }

    // 查询数据
    public void Query() {
        Cursor cursor = db.query("person1", null, null, null, null, null, null);
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
        showGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medicine medicine=medicineList.get(position);
                Intent intent=new Intent(MedicineGridActivity.this, MyMedicineDescActivity.class);
                intent.putExtra("food", medicine);
                intent.putExtra("code","2");
                startActivity(intent);
            }
        });
    }

}