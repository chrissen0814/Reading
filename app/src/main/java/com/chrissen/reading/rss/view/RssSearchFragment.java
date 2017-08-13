package com.chrissen.reading.rss.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chrissen.reading.R;
import com.chrissen.reading.rss.adapter.RssSearchAdapter;
import com.chrissen.reading.rss.bean.Feed;
import com.chrissen.reading.rss.presenter.RssSearchPresenter;
import com.chrissen.reading.rss.presenter.RssSearchPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class RssSearchFragment extends Fragment implements RssSearchView {
    private static final String TAG = "RssSearchFragment";

    private RssSearchPresenter presenter;

    private SearchView rssSv;
    private RecyclerView searchedRssRv;
    private RssSearchAdapter adapter;

    private List<Feed> feeds = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RssSearchPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss_search,container,false);
        rssSv = (SearchView) view.findViewById(R.id.rss_search_search_sv);
        rssSv.setIconifiedByDefault(true);
        rssSv.setFocusable(true);
        rssSv.setIconified(false);
        rssSv.requestFocusFromTouch();
        rssSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.lodSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                feeds.clear();
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        rssSv.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                feeds.clear();
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        searchedRssRv = (RecyclerView) view.findViewById(R.id.rss_search_rv);
        adapter = new RssSearchAdapter(getActivity(),feeds);
        searchedRssRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchedRssRv.setAdapter(adapter);
        return view;
    }

    @Override
    public void showSearchedRsses(List<Feed> feedList) {
        feeds.clear();
        feeds.addAll(feedList);
        adapter.notifyDataSetChanged();
    }
}
