package com.example.gilman.maybemajor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StringResult extends AppCompatActivity {
    TextView textView;
    Intent intent;
    QRDatabase qrDatabase=QRDatabase.getqrDatabase(this);
    QRDao qrDao=qrDatabase.getQrDao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_result);
        textView=findViewById(R.id.resultText);
        intent=getIntent();
        String text=intent.getStringExtra("data");
        textView.setText(text);
//        SavedData dbData1=new SavedData(qrDao.getmaxId()+1, text);
//        qrDao.addEntity(dbData1);
    }
}
