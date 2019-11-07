package com.example.sqlitememo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    private DbAdapter dbAdapter;
    ListView memo_list;
    ArrayList<Memo> memos = new ArrayList<>();
    Cursor cursor;
    ListAdapter dataSimpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memo_list = findViewById(R.id.memoList);
        dbAdapter = new DbAdapter(this);
        displayList();
    }

    private void displayList() {
        //列出所有memo資料
        cursor = dbAdapter.listMemos();
        if(cursor.moveToFirst()) {
              do{
                memos.add(new Memo(
                        cursor.getInt(cursor.getColumnIndexOrThrow("_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("memo")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("remind")),
                        cursor.getString(cursor.getColumnIndexOrThrow("bgcolor"))));
              }while(cursor.moveToNext());
        }
        cursor.moveToFirst();
        dataSimpleAdapter = new ListAdapter(this,memos);
        memo_list.setAdapter(dataSimpleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_add:
                intent = new Intent(MainActivity.this, EditMemoActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
