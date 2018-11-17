package com.example.gilman.maybemajor;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StringResult extends AppCompatActivity {
    String text;
    TextView textView;
    Button copyButton;
    Intent intent;
    QRDatabase qrDatabase=QRDatabase.getqrDatabase(this);
    QRDao qrDao=qrDatabase.getQrDao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_result);
        textView=findViewById(R.id.resultText);
        copyButton=findViewById(R.id.copyButton);
        intent=getIntent();
        text=intent.getStringExtra("data");
        textView.setText(text);
//        SavedData dbData1=new SavedData(qrDao.getmaxId()+1, text);
//        qrDao.addEntity(dbData1);
    }

    public void copyContent(View view){

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label",text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_LONG).show();
    }
}
