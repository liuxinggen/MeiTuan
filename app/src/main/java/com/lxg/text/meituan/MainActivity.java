package com.lxg.text.meituan;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout1, layout2, layout3, llChose;
    private List<String> listLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();

    }

    private void initView() {
        layout1 = findViewById(R.id.ll_1);
        layout2 = findViewById(R.id.ll_2);
        layout3 = findViewById(R.id.ll_3);
        llChose = findViewById(R.id.ll_chose);


        listLeft = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            listLeft.add("左边" + i);
        }


    }

    private void initEvent() {
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPopupWindow myPopupWindow = new MyPopupWindow(MainActivity.this);
                myPopupWindow.setData(listLeft);
                myPopupWindow.showAsDropDown(llChose, 0, 2);
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyExpandPopupWindow myExpandPopupWindow = new MyExpandPopupWindow(MainActivity.this);
                myExpandPopupWindow.setData(listLeft);
                myExpandPopupWindow.showAsDropDown(llChose, 0, 2);
            }
        });


    }
}
