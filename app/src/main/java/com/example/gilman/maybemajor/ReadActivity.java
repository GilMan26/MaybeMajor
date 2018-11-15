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

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {
    Button scan_btn;
    String[] Label = new String[10];
    String[] Data = new String[10];
    Intent otherIntent, customIntent;
    QRDatabase qrDatabase=QRDatabase.getqrDatabase(this);
    QRDao qrDao=qrDatabase.getQrDao();
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
        ArrayList<String> resultAL=new ArrayList<>();
        customIntent=new Intent(this, ResultActivity.class);
        otherIntent=new Intent(this, StringResult.class);
        String res;
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                //Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
                res = result.getContents();
                if(res.charAt(res.length()-1)=='$'){
                    int colon = 0, semicolon, start = 0, indexlabel = 0, indexdata = 0;
                    for (int i = 0; i < res.length(); i++) {
                        String indField=null;
                        char ch = res.charAt(i);
                        if (ch == ':') {
                            colon = i;
                            Label[indexlabel] = res.substring(start, colon );
                            indField=res.substring(start, colon - 1)+" : ";
                            indexlabel++;
                        }
                        if (ch == ';') {
                            semicolon = i;
                            Data[indexdata] = res.substring(colon + 1, semicolon);
                            indField=indField+res.substring(colon + 1, semicolon);
                            indexdata++;
                            start = semicolon + 1;
                        }
                        if (ch == '$') {
                            break;
                        }
                        resultAL.add(indField);
                    }
                    SavedData dbData=new SavedData(qrDao.getmaxId()+1, resultAL.get(0));
                    qrDao.addEntity(dbData);
                    customIntent.putExtra("boom", Label);
                    customIntent.putExtra("bing", Data);
                    customIntent.putExtra("finalArray", resultAL);
                    startActivity(customIntent);
                }
                else{
                    SavedData dbData1=new SavedData(qrDao.getmaxId()+1, res);
                    qrDao.addEntity(dbData1);
                    otherIntent.putExtra("data", res);
                    startActivity(otherIntent);

                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
//        if(result != null){
//            if(result.getContents()==null){
//                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
//            }
//            else {
//                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
//            }
//        }
//        else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//        assert result != null;
//        res = result.getContents();
//        if(res.charAt(res.length()-1)=='$'){
//        int colon = 0, semicolon, start = 0, indexlabel = 0, indexdata = 0;
//                for (int i = 0; i < res.length(); i++) {
//                String indField=null;
//                char ch = res.charAt(i);
//                if (ch == ':') {
//                    colon = i;
//                    Label[indexlabel] = res.substring(start, colon - 1);
//                    indField=res.substring(start, colon - 1)+" : ";
//                    indexlabel++;
//                }
//                if (ch == ';') {
//                    semicolon = i;
//                    Data[indexdata] = res.substring(colon + 1, semicolon);
//                    indField=indField+res.substring(colon + 1, semicolon);
//                    indexdata++;
//                    start = semicolon + 1;
//                }
//                if (ch == '$') {
//                    break;
//                }
//                resultAL.add(indField);
//            }
//            customIntent.putExtra("boom", Label);
//            customIntent.putExtra("bing", Data);
//        intent.putExtra("finalArray", resultAL);
//            startActivity(customIntent);
//        }
//        else{
//            otherIntent.putExtra("data", res);
//            startActivity(otherIntent);
//        }


    }
}
