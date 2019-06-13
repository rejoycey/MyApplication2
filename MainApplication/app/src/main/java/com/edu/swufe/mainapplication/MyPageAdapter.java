package com.edu.swufe.mainapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {

    private String[] title = new String[]{"First","Second","Third"};

    public MyPageAdapter(FragmentManager manager) {
        super( manager);
    }

    @Override
    //确定使用哪个窗口
    public Fragment getItem(int position) {
        if (position==0){
            return new FirstFragment2();
        }else if (position==1){
            return new SecondFragment2();
        }else {
            return  new ThirdFragment2();
        }

    }
    @Override
    public CharSequence getPageTitle(int position) {

        return title[position];
        }

    @Override
    public int getCount() {
        return 3;
    }
}
