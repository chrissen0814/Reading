package com.chrissen.reading.one.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chrissen.reading.R;
import com.chrissen.reading.one.adapter.OneListAdapter;
import com.chrissen.reading.one.bean.OneList;
import com.chrissen.reading.one.bean.ReadingList;
import com.chrissen.reading.one.presenter.OneListPreImpl;
import com.chrissen.reading.one.presenter.OneListPresenter;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2017/8/12.
 */

public class OneListListFragment extends Fragment implements OneListView {

    private OneListPresenter presenter;

    private ImageView pictureIv;
    private TextView pictureDescTv;
    private RecyclerView onelistRv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new OneListPreImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onelist,container,false);
        pictureIv = (ImageView) view.findViewById(R.id.onelist_picture_iv);
        pictureDescTv = (TextView) view.findViewById(R.id.onelist_picture_foreward_tv);
        onelistRv = (RecyclerView) view.findViewById(R.id.onelist_readinglist_rv);
        pictureIv.setFocusable(true);
        pictureIv.requestFocus();
        pictureIv.isFocusableInTouchMode();
        presenter.getOneList();
        return view;
    }


    @Override
    public void showOneList(OneList oneList) {
        Glide.with(this).load(oneList.getDate().getContenList().get(0).getImageUrl())
                .into(pictureIv);
        pictureDescTv.setText(oneList.getDate().getContenList().get(0).getForward());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getReadingList();
            }
        },1000);
    }

    @Override
    public void showReadingList(ReadingList readingList) {
        OneListAdapter adapter = new OneListAdapter(this,readingList);
        onelistRv.setLayoutManager(new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        onelistRv.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("OneListFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("OneListFragment");
    }
}
