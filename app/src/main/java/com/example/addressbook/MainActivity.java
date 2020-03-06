package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyHelper myhelper;
    private Button btnWr;
    private Button btnRe;
    private Button btnUp;
    private Button btnRem;
    private EditText ed_name;
    private EditText ed_email;
    private EditText ed_phone;
    private TextView Text_info;
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name;
            String email;
            String phone;
            ContentValues values;
            SQLiteDatabase db;
            switch (v.getId()){
                case R.id.btn_write:
                    name=ed_name.getText().toString();
                    email=ed_email.getText().toString();
                    phone=ed_phone.getText().toString();
                    db=myhelper.getWritableDatabase();

                    if(isNull()||isUnique(phone)){
                        values=new ContentValues();
                        values.put("name",name);
                        values.put("email",email);
                        values.put("phone",phone);
                        db.insert("info",null,values);
                        Toast.makeText(getApplicationContext(), "write successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "the phone number exists", Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                    break;
                case R.id.btn_read:
                    db=myhelper.getReadableDatabase();
                    if(isNull()){
                        Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_SHORT).show();
                    }else{
                        Cursor cursor=db.query("info",null,null,null,null,null,null,null);
                        cursor.moveToFirst();
                        Text_info.setText("");
                        Text_info.append("NAME:"+cursor.getString(2)+"\n"+
                                "EMAIL:"+cursor.getString(3)+"\n"+
                                "MOBILE NUMBER:"+cursor.getString(4)+"\n");
                        while(cursor.moveToNext()){
                            Text_info.append("NAME:"+cursor.getString(2)+"\n"+
                                    "EMAIL:"+cursor.getString(3)+"\n"+
                                    "MOBILE NUMBER:"+cursor.getString(4)+"\n");
                        }
                        cursor.close();
                    }
                    db.close();
                    break;
                case R.id.btn_update:
                    db=myhelper.getWritableDatabase();
                    if(isNull()||isUnique(ed_phone.getText().toString())){
                        values=new ContentValues();
                        values.put("name",ed_name.getText().toString());
                        values.put("email",ed_email.getText().toString());
                        values.put("name",ed_phone.getText().toString());
                        db.update("info",values,"name=?",new String[]{ed_name.getText().toString()});
                        Toast.makeText(getApplicationContext(), "update successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "the phone number exists", Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                    break;
                case R.id.btn_remove:
                    db=myhelper.getWritableDatabase();
                    db.delete("info",null,null);
                    Toast.makeText(getApplicationContext(), "delete successfully", Toast.LENGTH_SHORT).show();
                    db.close();
                    Text_info.setText("");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnWr=(Button)findViewById(R.id.btn_write);
        btnRe=(Button)findViewById(R.id.btn_read);
        btnUp=(Button)findViewById(R.id.btn_update);
        btnRem=(Button)findViewById(R.id.btn_remove);
        ed_name=(EditText)findViewById(R.id.edt_name);
        ed_email=(EditText)findViewById(R.id.edt_email);
        ed_phone=(EditText)findViewById(R.id.edt_phone);
        Text_info=(TextView)findViewById(R.id.text_show);
        btnWr.setOnClickListener(listener);
        btnRe.setOnClickListener(listener);
        btnUp.setOnClickListener(listener);
        btnRem.setOnClickListener(listener);
    }

    private boolean isUnique(String phone){
        SQLiteDatabase db=myhelper.getWritableDatabase();
        Cursor cursor2=db.rawQuery("select count(*)from table info where phone=="+phone,null);
        //query("info",null,"phone=="+ed_phone.getText().toString(),null,null,null,null,null);
        boolean result= cursor2.getCount()==0;
        cursor2.close();
        db.close();
        return  result;
    }
    private boolean isNull(){
        SQLiteDatabase db=myhelper.getWritableDatabase();
        Cursor cursor=db.query("info",null,null,null,null,null,null,null);
        boolean result=cursor.getCount()==0;
        cursor.close();
        db.close();
        return result;
    }
}
