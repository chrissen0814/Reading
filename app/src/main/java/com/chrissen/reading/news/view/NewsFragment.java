package com.chrissen.reading.news.view;

import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.chrissen.reading.R;
import com.chrissen.reading.news.adapter.NewsAdapter;
import com.chrissen.reading.news.bean.News;
import com.chrissen.reading.news.presenter.NewsImpl;
import com.chrissen.reading.news.presenter.NewsPresenter;
import com.chrissen.reading.util.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class NewsFragment extends Fragment implements NewsView {

    private int count;
    private int totalItemCount;
    private int previousTotal = 0;
    private int visibleItemCount;
    private int firstVisibleItem;
    private boolean loading = true;
    private String[] category;
    private NewsPresenter mPresenter;
    private NewsAdapter mNewsAdapter;
    private List<News.Result.Info> mInfoList;
    private CustomRecyclerView mNewsRv;
    private LinearLayoutManager mLayoutManager;
    private ProgressBar emptyView;
    private Spinner mSpinner;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfoList = new ArrayList<>();
        category = getResources().getStringArray(R.array.news_category);
        mPresenter = new NewsImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        mNewsRv = (CustomRecyclerView) view.findViewById(R.id.article_rv);
        mSpinner = (Spinner) view.findViewById(R.id.news_spinner);
        emptyView = (ProgressBar) view.findViewById(R.id.article_empty_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mNewsRv.setLayoutManager(mLayoutManager);
        mNewsAdapter = new NewsAdapter(this,mInfoList);
        mNewsRv.setAdapter(mNewsAdapter);
        mNewsRv.setEmptyView(emptyView);
        initLayout();
        return view;
    }

    private void initLayout() {
        mSpinner.setAdapter(ArrayAdapter.createFromResource(getActivity(),R.array.news_category,android.R.layout.simple_spinner_dropdown_item));
        String type = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("default_news","top");
        switch (type){
            case "top":
                mPresenter.loadNews(category[0],0);
                mSpinner.setSelection(0,true);
                break;
            case "news":
                mPresenter.loadNews(category[1],0);
                mSpinner.setSelection(1,true);
                break;
            case "finance":
                mPresenter.loadNews(category[2],0);
                mSpinner.setSelection(2,true);
                break;
            case "sport" :
                mPresenter.loadNews(category[3],0);
                mSpinner.setSelection(3,true);
                break;
            case "entertainment":
                mPresenter.loadNews(category[4],0);
                mSpinner.setSelection(4,true);
                break;
            case "military":
                mPresenter.loadNews(category[5],0);
                mSpinner.setSelection(5,true);
                break;
            case "technology":
                mPresenter.loadNews(category[6],0);
                mSpinner.setSelection(6,true);
                break;
            case "nba":
                mPresenter.loadNews(category[7],0);
                mSpinner.setSelection(7,true);
                break;
        }
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.loadNews(category[position],0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mNewsRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = mNewsRv.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                if (loading && totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal =totalItemCount;
                }
                if(!loading && totalItemCount - visibleItemCount <= firstVisibleItem){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            count = count + 20;
                            mPresenter.loadMoreNews(mSpinner.getSelectedItem().toString(),count);
                        }
                    },600);
                    loading = true;
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }


    @Override
    public void showNews(List<News.Result.Info> infoList) {
        mInfoList.clear();
        mInfoList.addAll(infoList);
        mNewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreNews(List<News.Result.Info> infoList) {
        mInfoList.addAll(infoList);
        mNewsAdapter.notifyDataSetChanged();
    }

}
