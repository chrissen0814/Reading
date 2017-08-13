package com.chrissen.reading.rss.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chrissen.reading.R;
import com.chrissen.reading.rss.adapter.StreamAdapter;
import com.chrissen.reading.rss.presenter.StreamPresenter;
import com.chrissen.reading.rss.presenter.StreamPresenterImpl;
import com.chrissen.reading.util.CustomRecyclerView;
import com.chrissen.reading.util.fragmentHelper.BackHandlerHelper;
import com.chrissen.reading.util.fragmentHelper.FragmentBackHandler;

/**
 * Created by Administrator on 2017/8/11.
 */

public class StreamFragment extends Fragment implements StreamView , FragmentBackHandler {
    private static final String FEED_TITLE = "feed";
    private static final String FEED_ID = "id";
    private String feedTitle;
    private String feedId;
    private CustomRecyclerView streamRv;
    private TextView titleTv;
    private ProgressBar emptyView;
    private StreamPresenter presenter;

    public static StreamFragment newInstance(String feedTitle , String feedId){
        Bundle bundle = new Bundle();
        bundle.putString(FEED_TITLE,feedTitle);
        bundle.putString(FEED_ID,feedId);
        StreamFragment fragment = new StreamFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedTitle =getArguments().getString(FEED_TITLE);
        feedId = getArguments().getString(FEED_ID);
        presenter = new StreamPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stream,container,false);
        titleTv = (TextView) view.findViewById(R.id.stream_title);
        titleTv.setText(feedTitle);
        streamRv = (CustomRecyclerView) view.findViewById(R.id.stream_article_rv);
        emptyView = (ProgressBar) view.findViewById(R.id.stream_empty_view);
        presenter.loadStream(feedId);
        return view;
    }

    @Override
    public void showArticles() {
        StreamAdapter adapter = new StreamAdapter(this,feedTitle);
        streamRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        streamRv.setAdapter(adapter);
        streamRv.setEmptyView(emptyView);
    }

    @Override
    public boolean onBackPressed() {
        BottomNavigationView bnv = (BottomNavigationView) getActivity().findViewById(R.id.main_bnv);
        bnv.setVisibility(View.VISIBLE);
        return BackHandlerHelper.handleBackPress(this);
    }

}
