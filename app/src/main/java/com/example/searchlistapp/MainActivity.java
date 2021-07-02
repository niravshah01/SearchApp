package com.example.searchlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SearchView sv;
    ListView lv;
    List<Bean> beanList;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sv = findViewById(R.id.sv);
        lv = findViewById(R.id.lv);

        String [] Decimal = getResources().getStringArray(R.array.decimal);
        String [] Binary = getResources().getStringArray(R.array.binary);
        String [] Hexa = getResources().getStringArray(R.array.hexa);

        beanList = new ArrayList<Bean>();

        for(int i=0;i<Decimal.length;i++){
            Bean bean = new Bean(Decimal[i],Binary[i],Hexa[i]);
            beanList.add(bean);
        }

        for(int i=0;i<Decimal.length;i++){
            Bean bean = beanList.get(i);
            String s = bean.getDecimal();
            Log.e("Name",s);
        }

        adapter = new MyAdapter(getApplicationContext(),beanList);
        lv.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }
}