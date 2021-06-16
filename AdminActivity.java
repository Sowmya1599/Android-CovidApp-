package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    ListView listView;
    DatabaseHelper dataBaseHelper;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        dataBaseHelper=new DatabaseHelper(this,"co.db",1);
        try{
            dataBaseHelper.CheckDb();
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            dataBaseHelper.OpenDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }
        listView = (ListView) findViewById(R.id.list30);
        listItem = new ArrayList<>();
        dataBaseHelper = new DatabaseHelper(this, "co.db", 1);
        viewData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String txt=listView.getItemAtPosition(position).toString();
                Toast.makeText(AdminActivity.this,""+txt,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void viewData(){
        Cursor cursor=dataBaseHelper.viewData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data to show",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext()){
                listItem.add("Name:\t\t"+cursor.getString(0));
                listItem.add("Age:\t\t " + cursor.getString(1));
                listItem.add("Gender:\t\t" + cursor.getString(2));
                listItem.add("Address:\t\t" + cursor.getString(3));
                listItem.add("Contact Number:\t\t " + cursor.getString(4));
                listItem.add("Date of Sample Collected\t\t" + cursor.getString(5));
                listItem.add("Date of Report:\t\t" + cursor.getString(6));
                listItem.add("Lab Report:\t\t " + cursor.getString(7));
                listItem.add("District:\t\t" + cursor.getString(8));
                listItem.add("SRF Number:\t\t" + cursor.getString(9));
                listItem.add("Status:\t\t " + cursor.getString(10));
                listItem.add("Swab:\t\t" + cursor.getString(11));
            }
            adapter=new ArrayAdapter(AdminActivity.this, android.R.layout.simple_list_item_1,listItem);
            listView.setAdapter(adapter);
    }
    }
}