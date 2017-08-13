package com.chrissen.reading.picture.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.chrissen.reading.MyApplication;
import com.chrissen.reading.R;
import com.chrissen.reading.picture.adapter.GankAdapter;
import com.chrissen.reading.picture.adapter.UnsplashAdapter;
import com.chrissen.reading.picture.bean.Gank;
import com.chrissen.reading.picture.bean.Unsplash;
import com.chrissen.reading.picture.presenter.PictureImpl;
import com.chrissen.reading.picture.presenter.PicturePresenter;
import com.chrissen.reading.util.CustomRecyclerView;
import com.chrissen.reading.util.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public class PictureFragment extends Fragment implements PictureView {

    private static final String TAG = "PictureFragment";

    private int currentPage = 1;
    private int currentGankPage = 1;
    private int totalItemCount;
    private int previousTotal = 0;
    private int visibleItemCount;
    private int firstVisibleItem;
    private boolean loading = true;

    private PicturePresenter mPresenter;
    private UnsplashAdapter mUnsplashAdapter;
    private GankAdapter mGankAdapter;
    private LinearLayoutManager mLinearLm;
    private GridLayoutManager mGridLm;
    private List<Unsplash> listUnsplash;
    private List<Gank.MeiZhi> listMeizhi;
    private Context mContext;

    private CustomRecyclerView pictureRv;
    private CustomRecyclerView gankRv;
    private ProgressBar emptyView;
    private Spinner spinner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = MyApplication.getContext();
        mPresenter = new PictureImpl(this);
        listUnsplash = new ArrayList<>();
        listMeizhi = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture,container,false);
        pictureRv = (CustomRecyclerView) view.findViewById(R.id.picture_rv);
        gankRv = (CustomRecyclerView) view.findViewById(R.id.picture_ganl_rv);
        emptyView = (ProgressBar) view.findViewById(R.id.picture_empty_view);
        spinner = (Spinner) view.findViewById(R.id.picture_spinner);
        initLayout();
        return view;
    }

    private void initLayout() {
        mLinearLm = new LinearLayoutManager(mContext);
        mUnsplashAdapter = new UnsplashAdapter(PictureFragment.this,listUnsplash);
        pictureRv.setLayoutManager(mLinearLm);
        pictureRv.setAdapter(mUnsplashAdapter);
        pictureRv.setEmptyView(emptyView);
        mGridLm = new GridLayoutManager(mContext,2);
        mGankAdapter = new GankAdapter(PictureFragment.this,listMeizhi);
        gankRv.setLayoutManager(mGridLm);
        gankRv.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        gankRv.setAdapter(mGankAdapter);
        gankRv.setEmptyView(emptyView);
        spinner.setAdapter(ArrayAdapter.createFromResource(getActivity(),R.array.picture_category,android.R.layout.simple_spinner_dropdown_item));
        String source = PreferenceManager.getDefaultSharedPreferences(mContext).getString("default_picture","unsplash");
        switch (source){
            case "unsplash":
                gankRv.setVisibility(View.GONE);
                pictureRv.setVisibility(View.VISIBLE);
                spinner.setSelection(0,true);
                mPresenter.loadUnsplashImage(1);
                break;
            case "meizhi":
                gankRv.setVisibility(View.VISIBLE);
                pictureRv.setVisibility(View.GONE);
                spinner.setSelection(1,true);
                mPresenter.loadGankImage(1);
                break;
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        gankRv.setVisibility(View.INVISIBLE);
                        pictureRv.setVisibility(View.VISIBLE);

                        mPresenter.loadUnsplashImage(1);
                        break;
                    case 1:
                        pictureRv.setVisibility(View.INVISIBLE);
                        gankRv.setVisibility(View.VISIBLE);

                        mPresenter.loadGankImage(1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                            mPresenter.loadUnsplashMore(currentPage);
                        }
                    },800);
                    loading = true;
                }
            }
        });

        gankRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = gankRv.getChildCount();
                totalItemCount = mGridLm.getItemCount();
                firstVisibleItem = mGridLm.findFirstVisibleItemPosition();
                if (loading && totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal =totalItemCount;
                }
                if(!loading && totalItemCount - visibleItemCount <= firstVisibleItem){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentGankPage += 1;
                            mPresenter.loadGankImage(currentGankPage);
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
    public void showImage(List<Unsplash> unsplashList) {
        listUnsplash.addAll(unsplashList);
        mUnsplashAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreImage(List<Unsplash> unsplashList) {
        listUnsplash.addAll(unsplashList);
        mUnsplashAdapter.notifyItemInserted(unsplashList.size());
    }

    @Override
    public void showGankImage(List<Gank.MeiZhi> meiZhiList) {
        Log.i(TAG, "showGankImage: " + meiZhiList.size());
        listMeizhi.addAll(meiZhiList);
        mGankAdapter.notifyDataSetChanged();
    }


}
