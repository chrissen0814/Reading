package com.chrissen.reading.news.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.chrissen.reading.R;
import com.chrissen.reading.news.adapter.NewsAdapter;
import com.chrissen.reading.news.bean.News;
import com.chrissen.reading.news.presenter.NewsImpl;
import com.chrissen.reading.news.presenter.NewsPresenter;
import com.chrissen.reading.util.CustomRecyclerView;
import com.chrissen.reading.util.fragmentHelper.BackHandlerHelper;
import com.chrissen.reading.util.fragmentHelper.FragmentBackHandler;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class NewsFragment extends Fragment implements NewsView , FragmentBackHandler {
    private static final String CATEGORY = "category";
    private String category;
    private NewsAdapter adapter;
    private LinearLayoutManager linerLayoutManager;
    private List<News.Result.Info> infoList;
    private NewsPresenter presenter;
    private ProgressBar emptyView;
    private CustomRecyclerView customRecyclerView;

    private int start = 0;
    private int totalItemCount;
    private int previousTotal = 0;
    private int visibleItemCount;
    private int firstVisibleItem;
    private boolean loading = true;

    public static NewsFragment newInstance(String category){
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY,category);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getArguments().getString(CATEGORY);
        presenter = new NewsImpl(this);
        infoList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        emptyView = (ProgressBar) view.findViewById(R.id.news_empty_view);
        customRecyclerView = (CustomRecyclerView) view.findViewById(R.id.news_content_crv);
        linerLayoutManager = new LinearLayoutManager(getActivity());
        customRecyclerView.setLayoutManager(linerLayoutManager);
        adapter = new NewsAdapter(this,infoList);
        customRecyclerView.setAdapter(adapter);
        customRecyclerView.setEmptyView(emptyView);
        customRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = customRecyclerView.getChildCount();
                totalItemCount = linerLayoutManager.getItemCount();
                firstVisibleItem = linerLayoutManager.findFirstVisibleItemPosition();
                if (loading && totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal =totalItemCount;
                }
                if(!loading && totalItemCount - visibleItemCount <= firstVisibleItem){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            start = start + 20;
                            presenter.loadMoreNews(category,start);
                        }
                    },800);
                    loading = true;
                }
            }
        });
    }


    @Override
    public void showNews(List<News.Result.Info> infoList) {
        this.infoList.clear();
        this.infoList.addAll(infoList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreNews(List<News.Result.Info> infoList) {
        this.infoList.addAll(infoList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("NewsFragment");
        if(!category.isEmpty()){
            presenter.loadNews(category,0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("NewsFragment");
    }


    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }
}
