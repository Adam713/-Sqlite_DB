package com.qays3dd.sqlite_db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DB_Sqlit db = new DB_Sqlit(this);
    EditText name, naumber, ID;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextTName);
        naumber = (EditText) findViewById(R.id.editNumber);
        ID = (EditText) findViewById(R.id.editText_id);
        lst = (ListView) findViewById(R.id.listView_Data);
        ShowData();

    }

    public void btn_add_data(View view) {
        String Name = name.getText().toString();
        String Number = naumber.getText().toString();

        Boolean result = db.insertData( Name, Number);
        if (result==true) {
            Toast.makeText(MainActivity.this,"Ok", Toast.LENGTH_SHORT).show();
            name.setText("");
            naumber.setText("");
            ShowData();
        }else {
            Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
        }

    }
    public void  ShowData(){
        ArrayList<String> listData = db.getAllrecord();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listData);
        lst.setAdapter(arrayAdapter);
    }

    public void btn_Update(View view) {
        String Name = name.getText().toString();
        String Number = naumber.getText().toString();
        String id = ID.getText().toString();

        Boolean result = db.updateData( id, Name, Number);
        if (result==true) {
            Toast.makeText(MainActivity.this,"Ok", Toast.LENGTH_SHORT).show();
            name.setText("");
            naumber.setText("");
            ID.setText("");
            ShowData();
        }else {
            Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
        }

    }

    public void btn_Delete(View view) {
        String id = ID.getText().toString();
        Integer result = db.Delete(id);
        if (result > 0){
            Toast.makeText(MainActivity.this,"Ok", Toast.LENGTH_SHORT).show();
            ShowData();
        }else {
            Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
        }

    }
}