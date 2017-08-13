package com.chrissen.reading;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.chrissen.reading.news.view.NewsFragment;
import com.chrissen.reading.one.view.OneListFragment;
import com.chrissen.reading.picture.view.PictureFragment;
import com.chrissen.reading.rss.view.RssFragment;
import com.chrissen.reading.util.fragmentHelper.BackHandlerHelper;
import com.chrissen.reading.weibo.view.WeiBoFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainBnv;
    private DrawerLayout drawerLayout;
    private LinearLayout menuDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
    }

    private void initLayout() {
        mainBnv = (BottomNavigationView) findViewById(R.id.main_bnv);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        menuDrawer = (LinearLayout) findViewById(R.id.main_drawer_layout_ll);
        String types = PreferenceManager.getDefaultSharedPreferences(this).getString("default_screen","picture");
        switch (types){
            case "picture":
                mainBnv.setSelectedItemId(R.id.menu_bnv_picture);
                startPictureFragment();
                break;
            case "news":
                mainBnv.setSelectedItemId(R.id.menu_bnv_article);
                startNewsFragment();
                break;
            case "weibo":
                mainBnv.setSelectedItemId(R.id.menu_bnv_weibo);
                startWeiboFragment();
                break;
            case "rss":
                mainBnv.setSelectedItemId(R.id.menu_bnv_rss);
                startRssFragment();
                break;
            case "one":
                mainBnv.setSelectedItemId(R.id.menu_bnv_one);
                startOneFragment();
                break;
        }
        mainBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_bnv_picture:
                        startPictureFragment();
                        break;
                    case R.id.menu_bnv_article:
                        startNewsFragment();
                        break;
                    case R.id.menu_bnv_weibo:
                        startWeiboFragment();
                        break;
                    case R.id.menu_bnv_rss:
                        startRssFragment();
                        break;
                    case R.id.menu_bnv_one:
                        startOneFragment();
                        break;
                }
                return true;
            }
        });
    }

    private void startPictureFragment(){
        PictureFragment pf = new PictureFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container_fl,pf)
                .commit();
    }

    private void startNewsFragment(){
        NewsFragment af = new NewsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container_fl,af)
                .commit();
    }

    private void startWeiboFragment(){
        WeiBoFragment wbf = new WeiBoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container_fl,wbf)
                .commit();
    }

    private void startRssFragment(){
        RssFragment rf = new RssFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container_fl,rf)
                .commit();
    }

    private void startOneFragment(){
        OneListFragment of = new OneListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container_fl,of)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if(!BackHandlerHelper.handleBackPress(this)){
            super.onBackPressed();
        }
    }
}
