package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private Button btnWr;
private Button btnRe;
private Button btnUp;
private Button btnRem;
private EditText ed_name;
private EditText ed_email;
private EditText ed_phone;
MyHelper myhelper;
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
        myhelper=new MyHelper(this);
        btnWr.setOnClickListener(this);
        btnRe.setOnClickListener(this);
        btnUp.setOnClickListener(this);
        btnRem.setOnClickListener(this);

    }
    public void onClick(View v){



    }
}
