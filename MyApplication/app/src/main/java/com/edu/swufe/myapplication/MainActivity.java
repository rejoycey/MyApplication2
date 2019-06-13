package com.edu.swufe.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        //用于存放每一页的内容
        ViewPager viewPager =(ViewPager) findViewById( R.id.viewpager );
        MyPageAdapter pageAdapter =new MyPageAdapter( getSupportFragmentManager() );
        //联系两个方法
        viewPager.setAdapter( pageAdapter );
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
