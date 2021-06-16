package com.example.covid19;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context mContext;
    SQLiteDatabase  myDataBase;
    String dbPath;
    String dbName;
    public DatabaseHelper(Context context,String name, int version) {
        super(context, name,null, version);
        this.dbName=name;
        this.mContext=context;
        this.dbPath="/data/data/"+"com.example.covid19"+"/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void  CheckDb()
    {
        SQLiteDatabase checkDb=null;
        String filePath=dbPath+dbName;
        try{
            checkDb=SQLiteDatabase.openDatabase(filePath,null,0);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(checkDb!=null)
        {
            Toast.makeText(mContext,"",Toast.LENGTH_SHORT).show();
        }
        else
        {
            CopyDatabase();
        }
    }
    public void CopyDatabase()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        try
        {
            InputStream ios=mContext.getAssets().open(dbName);
            OutputStream os=new FileOutputStream(dbPath+dbName);

            byte[] buffer=new byte[1024];

            int len;
            while((len = ios.read(buffer))>0){
                os.write(buffer,0,len);
            }
            os.flush();
            ios.close();
            os.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.d("CopyDb","Database Copied");
    }
    public void OpenDatabase() {
        String filePath= dbPath+dbName;
        SQLiteDatabase.openDatabase(filePath,null,0);
    }
    public Boolean checkpass(String contact,String sirf)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor =db.rawQuery("select * from tub2 where contact=? and sirf=?",new String[] {contact,sirf});

        if(cursor.getCount()>0)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public Cursor viewData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from tub2",null);
        return cursor;
    }
}

