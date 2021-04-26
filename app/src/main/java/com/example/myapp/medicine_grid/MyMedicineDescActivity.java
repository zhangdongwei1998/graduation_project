package com.example.myapp.medicine_grid;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.bean.Medicine;
import com.example.myapp.bean.MyOpenHelper;
import com.example.myapp.medicine_list.testAdapter;

public class MyMedicineDescActivity extends AppCompatActivity implements View.OnClickListener {
    TextView titleTv1,titleTv2,descTv,notTv,textView1;
    ImageView backIv,bigPicIv,code1,code2;
    Button add_button;
    SQLiteDatabase db;

    MyOpenHelper mOpenHelper;
    testAdapter adapter;


    String addname,category,picid,description;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_desc_my);
        initView();
        // 创建MyOpenHelper实例
        mOpenHelper = new MyOpenHelper(this);
        // 得到数据库
        db = mOpenHelper.getWritableDatabase();
        //接收上一级页面传来的数据
        Intent intent=getIntent();
//        String yanzheng=intent.getStringExtra("code");
//        if(yanzheng.equals("2")){
//            code1.setVisibility(View.INVISIBLE);
//            code2.setVisibility(View.INVISIBLE);
//            textView1.setVisibility(View.INVISIBLE);
//            add_button.setVisibility(View.INVISIBLE);
//        }
        Medicine medicine=(Medicine) intent.getSerializableExtra("food");
        //设置显示控件
        addname=medicine.getName();
        category=medicine.getCategory();
        picid=medicine.getPicId();
        description=medicine.getDescription();
        id=Integer.parseInt(medicine.get_id());

        titleTv1.setText(medicine.getName());
        titleTv2.setText(medicine.getName());
        descTv.setText(medicine.getDescription());
        notTv.setText(medicine.getCategory());
        bigPicIv.setImageResource(Integer.parseInt(medicine.getPicId()));
        add_button=(Button)findViewById(R.id.food_grid_add);
        backIv=(ImageView)findViewById(R.id.fooddesc_iv_back);
        add_button.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    private void initView() {
        add_button=(Button)findViewById(R.id.food_grid_add);
        titleTv1=findViewById(R.id.fooddesc_tv_title1);
        titleTv2=findViewById(R.id.fooddesc_tv_title2);
        descTv=findViewById(R.id.fooddesc_tv_desc);
        notTv=findViewById(R.id.fooddesc_tv_not);
        backIv=findViewById(R.id.fooddesc_iv_back);
        bigPicIv=findViewById(R.id.fooddesc_iv_bigpic);
        code1=(ImageView) findViewById(R.id.code1);
        code2=(ImageView)findViewById(R.id.code2);
        textView1=(TextView)findViewById(R.id.textView1);
    }

    public void iv_1(View view){
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        int num1 = Integer.parseInt(textView1.getText().toString());
        if(num1>1){
            num1-=1;
        }

        textView1.setText(Integer.toString(num1));

    }
    public void iv_2(View view){
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        int num1 = Integer.parseInt(textView1.getText().toString());
        if(num1<999){
            num1+=1;
        }

        textView1.setText(Integer.toString(num1));

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.food_grid_add:
                Toast.makeText(MyMedicineDescActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
//                System.out.println("nameclick:"+addname+"categoryclick:"+category+"picidclick:"+picid+"descriptionclick:"+description);
                Delete(id,addname,category,description,picid);
                break;
            case R.id.fooddesc_iv_back:
                finish();
                break;

        }
    }

    public void Delete(int id,String addname,String category,String description,String picid) {
        String[] id1={String.valueOf(id)};
        db.delete("person1","_id=?",id1);
        }
}