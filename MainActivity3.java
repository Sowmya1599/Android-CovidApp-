package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    Button btt21;
    EditText et1,et2,et3;
    DatabaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btt21=(Button)findViewById(R.id.bt221);
        et1=(EditText)findViewById(R.id.et211);
        et2=(EditText)findViewById(R.id.et222);
        et3=(EditText)findViewById(R.id.et233);
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
        btt21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ename=et1.getText().toString();
                String  emno=et2.getText().toString();
                String esirf=et3.getText().toString();
                Boolean checkpass = dataBaseHelper.checkpass(emno,esirf);
                if((TextUtils.isEmpty(ename))||(TextUtils.isEmpty(emno))||(TextUtils.isEmpty(esirf)))
                {
                    Toast.makeText(MainActivity3.this,"Fields should not be empty",Toast.LENGTH_LONG).show();
                }
                else if (checkpass==true) {
                    Toast.makeText(MainActivity3.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),HandWashActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity3.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}