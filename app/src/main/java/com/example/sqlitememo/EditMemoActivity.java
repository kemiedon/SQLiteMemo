package com.example.sqlitememo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditMemoActivity extends AppCompatActivity implements View.OnClickListener {
//新增備忘跟編輯備忘都在這個Activity中執行
    private EditText edtMemo;
    private Button btn_back, btn_ok;
    private Spinner sp_color;
    Intent intent;
    ArrayList<ColorData> color_list = null;
    SpinnerAdapter spinnerAdapter;
    String selected_color; //記錄目前所選的顏色
    private DbAdapter dbAdapter;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
    String new_memo, currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memo);
        initView();
        dbAdapter = new DbAdapter(this);
        Log.i("datetime=",new Date(System.currentTimeMillis()).toString());
    }
    private void initView(){
        edtMemo = findViewById(R.id.edtMemo);
        btn_back = findViewById(R.id.btn_back);
        btn_ok = findViewById(R.id.btn_ok);
        btn_back.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        sp_color = findViewById(R.id.sp_colors);
        color_list = new ArrayList<ColorData>();
        color_list.add(new ColorData("紅色","#e4222d"));
        color_list.add(new ColorData("Green","#00c7a4"));
        color_list.add(new ColorData("Blue","#4b7bd8"));
        color_list.add(new ColorData("Orange","#fc8200"));
        color_list.add(new ColorData("Cyan","#18ffff"));
        spinnerAdapter = new SpinnerAdapter(color_list, this);
        sp_color.setAdapter(spinnerAdapter);
        sp_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_color = color_list.get(position).getCode();
                Log.d("color=",color_list.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case  R.id.btn_back:
                //返回MainActivity
                intent = new Intent(EditMemoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_ok:
            //判斷要新增還是編輯備忘
                currentTime = df.format(new Date(System.currentTimeMillis())); //取得並格式化目前日期與時間
                new_memo = edtMemo.getText().toString();
                try{
                    dbAdapter.createMemo(currentTime, new_memo, null, selected_color);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    intent = new Intent(EditMemoActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                break;
        }
    }
}
