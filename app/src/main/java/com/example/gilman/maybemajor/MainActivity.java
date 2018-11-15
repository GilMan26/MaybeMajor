package com.example.gilman.maybemajor;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemLongClickListener {
    QRDao qrDao;
    List<SavedData> savedDataList;
    ListView listView;
    ArrayList<String> dataList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        QRDatabase database=QRDatabase.getqrDatabase(this);
        qrDao=database.getQrDao();
        savedDataList=qrDao.getData();
        dataList=getStringData(savedDataList);
        listView=findViewById(R.id.savedList);
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
//        adapter.addAll(dataList);

        listView.setOnItemLongClickListener(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.code) {
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.scan) {
            Intent intent=new Intent(this, ReadActivity.class);
            startActivity(intent);

        } else if (id == R.id.generate) {
            Intent intent=new Intent(this, GenerateActivity.class);
            startActivity(intent);

        } else if (id == R.id.about) {
            Intent aboutIntent=new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void refresh(){
//        List<SavedData> saved;
//        ArrayList<String> data;
//        saved=qrDao.getData();
//        data=getStringData(saved);
//        dataList=data;
//        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        //refresh();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
        //final String savedData = dataList.get(i);
        final int position = i;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete");
        builder.setCancelable(false);
        builder.setMessage("Do you really want to delete this entry ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(MainActivity.this,"Ok Presses",Toast.LENGTH_LONG).show();
                String data=dataList.get(position);
                long id=qrDao.getID(data);
                SavedData data1=new SavedData(id, data);
                qrDao.deleteEntity(data1);
                dataList.remove(position);
                adapter.notifyDataSetChanged();
                //refresh();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //TODO
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        return true;
    }

    public ArrayList<String> getStringData(List<SavedData> data){
        ArrayList<String> list=new ArrayList<>();

        for(int i=0;i<data.size();i++){
            list.add(data.get(i).getSavedData());
        }
        return list;
    }
}
