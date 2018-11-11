package com.example.gilman.maybemajor;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenerateFrag extends Fragment {
    ArrayList<String> listItems=new ArrayList<>();
    ArrayAdapter adapter;
    ListView listView;
    Button addButton, genButton;
    String result, finalResult;
    public GenerateFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_generate, container, false);
        adapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_2,listItems);
        listView=view.findViewById(R.id.list);
        listView.setAdapter(adapter);
        addButton=view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });
        genButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }



    public void addItem(View view){
        final EditText fieldEditText, valueEditText;
        TextView fieldTextView, valueTextView;
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
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
                result=field+"!"+value;
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
            Intent intent=new Intent(getContext(), Image.class);
            intent.putExtra("badaboom", finalResult);
            startActivity(intent);
        }
        else
            Toast.makeText(getContext(), "No fields and values", Toast.LENGTH_LONG).show();


    }
}
