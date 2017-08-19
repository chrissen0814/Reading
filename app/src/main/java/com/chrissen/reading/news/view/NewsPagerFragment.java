package com.chrissen.reading.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chrissen.reading.R;
import com.chrissen.reading.news.adapter.NewsPagerAdapter;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class NewsPagerFragment extends Fragment{

    private List<String> titleList;
    private List<Fragment> fragmentList;
    private NewsPagerAdapter newsPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleList = Arrays.asList(getResources().getStringArray(R.array.news_category));
        fragmentList = new ArrayList<>();
        for(int i = 0 ; i < titleList.size() ; i++){
            fragmentList.add(NewsFragment.newInstance(titleList.get(i)));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_pager,container,false);
        tabLayout = (TabLayout) view.findViewById(R.id.news_tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.news_viewpager);
        return view;
    }

    private void initLayout() {
        newsPagerAdapter = new NewsPagerAdapter(getFragmentManager(),titleList,fragmentList);
        viewPager.setAdapter(newsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager,true);
    }


    @Override
    public void onResume() {
        super.onResume();
        initLayout();
        MobclickAgent.onPageStart("NewsPagerFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("NewsPagerFragment");
    }
}
