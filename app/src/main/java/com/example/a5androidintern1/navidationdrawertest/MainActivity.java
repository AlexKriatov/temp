package com.example.a5androidintern1.navidationdrawertest;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainActivity extends Activity {

    final String[] data ={"Resume","Restart","Settings","Main menu"};
    GameFragment fragment;
    public static String packageName;
    DrawerLayout drawer;
    ListView navList;
    // String name = getApplicationContext().getPackageName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        packageName = "com.example.a5androidintern1.navidationdrawertest";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new GameFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.main, fragment);
        transaction.commit();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        navList = (ListView) findViewById(R.id.drawer);
        navList.setAdapter(adapter);

        navList.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                fragment.timersStop();
            }
        });
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos, long id){
                drawer.setDrawerListener( new DrawerLayout.SimpleDrawerListener(){
                    @Override
                    public void onDrawerClosed(View drawerView){
                        super.onDrawerClosed(drawerView);
                        fragment.timersStart();

                    }
                });
                drawer.closeDrawer(navList);
            }
        });

    }


    public void openDrawer(){

       drawer.openDrawer(navList);
    }
}