package com.example.gilman.maybemajor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GenerateActivity extends AppCompatActivity {
    ArrayList<String> listItems=new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;
    String result, finalResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        listView=findViewById(R.id.list);
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItems);
        Intent intent=getIntent();
        listView.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                getImage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.plusField) {
            addItem();
        }

        return super.onOptionsItemSelected(item);
    }


    public void addItem(){
        final EditText fieldEditText, valueEditText;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.alert_dialog,null);
        builder.setView(dialogView);
        fieldEditText=dialogView.findViewById(R.id.fieldED);
        valueEditText=dialogView.findViewById(R.id.valueED);

        builder.setTitle("Add Custom Field");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String field, value, result;
                //Log.d("field", fieldEditText.getText().toString().trim());
                field=fieldEditText.getText().toString();
                value=valueEditText.getText().toString();
                result=field+":"+value;
                adapter.add(result);
                finalResult=finalResult+";"+result;
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    public void getImage(){
        if(finalResult!=null){
            finalResult=finalResult+";$";

            finalResult=finalResult.substring(5);
            Intent intent=new Intent(this, Image.class);
            intent.putExtra("badaboom", finalResult);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "No fields and values", Toast.LENGTH_LONG).show();


    }



}
