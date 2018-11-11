package com.example.gilman.maybemajor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView labeltext, datatext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
        String[] Label, Data;
        Label=intent.getStringArrayExtra("boom");
        Data=intent.getStringArrayExtra("bing");
        String Labelfull = null,Datafull = null;
        //Data= intent.getStringArrayExtra("Data");
        labeltext = (TextView) findViewById(R.id.label1);
        datatext= (TextView) findViewById(R.id.data1);
        for (int i=0;i<Label.length;i++)
        {
            Labelfull+=Label[i]+"\n";
        }
        for (int i=0;i<Data.length;i++)
        {
            Datafull+=Data[i]+"\n";
        }
        labeltext.append(Labelfull);
        datatext.append(Datafull);

    }
}
