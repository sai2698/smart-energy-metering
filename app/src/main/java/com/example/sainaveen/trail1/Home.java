package com.example.sainaveen.trail1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by Sai Naveen on 3/12/2018.
 */

public class Home extends AppCompatActivity {
    BottomBar btbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tab_nearby);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                android.support.v4.app.FragmentManager fmange=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ftrans=fmange.beginTransaction();
                switch(tabId){
                    case R.id.tab_favorites:
                        ftrans.replace(R.id.frame,new first()).commit();
                        break;
                    case R.id.tab_friends:
                        ftrans.replace(R.id.frame,new third()).commit();
                        break;
                    case R.id.tab_nearby:
                        ftrans.replace(R.id.frame,new second()).commit();
                        break;

                }
            }
        });
    }
}
