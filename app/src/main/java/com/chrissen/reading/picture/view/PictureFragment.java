package com.chrissen.reading.picture.view;

import android.content.Context;
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

import com.chrissen.reading.MyApplication;
import com.chrissen.reading.R;
import com.chrissen.reading.picture.adapter.UnsplashAdapter;
import com.chrissen.reading.picture.bean.Unsplash;
import com.chrissen.reading.picture.presenter.PictureImpl;
import com.chrissen.reading.picture.presenter.PicturePresenter;
import com.chrissen.reading.util.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public class PictureFragment extends Fragment implements PictureView {

    private static final String TAG = "PictureFragment";

    private int currentPage = 1;
    private int totalItemCount;
    private int previousTotal = 0;
    private int visibleItemCount;
    private int firstVisibleItem;
    private boolean loading = true;

    private PicturePresenter mPresenter;
    private UnsplashAdapter mUnsplashAdapter;
    private LinearLayoutManager mLinearLm;
    private List<Unsplash> listUnsplash;
    private Context mContext;

    private CustomRecyclerView pictureRv;
    private ProgressBar emptyView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = MyApplication.getContext();
        mPresenter = new PictureImpl(this);
        listUnsplash = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture,container,false);
        mLinearLm = new LinearLayoutManager(mContext);
        pictureRv = (CustomRecyclerView) view.findViewById(R.id.picture_rv);
        emptyView = (ProgressBar) view.findViewById(R.id.picture_empty_view);
        mUnsplashAdapter = new UnsplashAdapter(this,listUnsplash);
        pictureRv.setLayoutManager(mLinearLm);
        pictureRv.setAdapter(mUnsplashAdapter);
        pictureRv.setEmptyView(emptyView);
        mPresenter.loadImage(1);
        initLayout();
        return view;
    }

    private void initLayout() {

        pictureRv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = pictureRv.getChildCount();
                totalItemCount = mLinearLm.getItemCount();
                firstVisibleItem = mLinearLm.findFirstVisibleItemPosition();
                if (loading && totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal =totalItemCount;
                }
                if(!loading && totalItemCount - visibleItemCount <= firstVisibleItem){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentPage += 1;
                            mPresenter.loadMore(currentPage);
                        }
                    },800);
                    loading = true;
                }
            }
        });

    }



    @Override
    public void showImage(List<Unsplash> unsplashList) {
        listUnsplash.addAll(unsplashList);
        mUnsplashAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreImage(List<Unsplash> unsplashList) {
        listUnsplash.addAll(unsplashList);
        mUnsplashAdapter.notifyItemInserted(unsplashList.size());
    }


}
