package com.example.gilman.maybemajor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ReadActivity extends AppCompatActivity {

    Button scan_btn;
    String[] Label = new String[10];
    String[] Data = new String[10];
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Intent intent=getIntent();
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        intent=new Intent(this, ResultActivity.class);
        String res;
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        assert result != null;
        res = result.getContents();
        int colon = 0, semicolon, start = 0, indexlabel = 0, indexdata = 0;

        for (int i = 0; i < res.length(); i++) {
            char ch = res.charAt(i);
            if (ch == ':') {
                colon = i;
                Label[indexlabel] = res.substring(start, colon - 1);
                indexlabel++;
            }
            if (ch == ';') {
                semicolon = i;
                Data[indexdata] = res.substring(colon + 1, semicolon);
                indexdata++;
                start = semicolon + 1;
            }
            if (ch == '$') {
                break;
            }
        }
        intent.putExtra("boom", Label);
        intent.putExtra("bing", Data);
        startActivity(intent);
    }
}
