package com.chrissen.reading.weibo.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.chrissen.reading.R;
import com.chrissen.reading.util.CustomRecyclerView;
import com.chrissen.reading.weibo.adapter.WeiBoHomelineAdapter;
import com.chrissen.reading.weibo.bean.Status;
import com.chrissen.reading.weibo.presenter.WeiboPresenter;
import com.chrissen.reading.weibo.presenter.WeiboPresenterImpl;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */

public class WeiBoFragment extends Fragment implements WeiboView {
    private static final String TAG = "WeiBoFragment";
    private SsoHandler ssoHandler;
    private int currentPage = 1;
    private int totalItemCount;
    private int previousTotal = 0;
    private int visibleItemCount;
    private int firstVisibleItem;
    private boolean loading = true;

    private WeiboPresenter presenter;
    private LinearLayoutManager homelineLlm;
    private WeiBoHomelineAdapter adapter;
    private List<Status> statuses = new ArrayList<>();

    private Toolbar weiboHomelineToolbar;
    private CustomRecyclerView homelineRv;
    private ProgressBar emptyView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new WeiboPresenterImpl(this);
        ssoHandler = presenter.loadAccessToken(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weibo_main,container,false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        weiboHomelineToolbar = (Toolbar) view.findViewById(R.id.weibo_main_toolbar);
        emptyView = (ProgressBar) view.findViewById(R.id.weibo_main_empty_view);
        homelineRv = (CustomRecyclerView) view.findViewById(R.id.weibo_main_rv);
        homelineLlm = new LinearLayoutManager(getActivity());
        homelineRv.setLayoutManager(homelineLlm);
        adapter = new WeiBoHomelineAdapter(this, statuses);
        homelineRv.setAdapter(adapter);
        homelineRv.setEmptyView(emptyView);
        homelineRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = homelineRv.getChildCount();
                totalItemCount = homelineLlm.getItemCount();
                firstVisibleItem = homelineLlm.findFirstVisibleItemPosition();
                if (loading && totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal =totalItemCount;
                }
                if(!loading && totalItemCount - visibleItemCount <= firstVisibleItem){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentPage += 1;
                            presenter.loadMoreHomeLine(currentPage);
                        }
                    },800);
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
    public void onResume() {
        super.onResume();
        presenter.loadHomeLine();

    }

    @Override
    public void showHomeLine(List<Status> statusesList) {
        statuses.clear();
        statuses.addAll(statusesList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreHomeLine(List<Status> statusList) {
        statuses.addAll(statusList);
        adapter.notifyItemInserted(statuses.size());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode,resultCode,data);
        }
    }
}
