package com.chrissen.reading.rss.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.chrissen.reading.R;
import com.chrissen.reading.rss.adapter.RssAdapter;

/**
 * Created by Administrator on 2017/8/9.
 */

public class RssFragment extends Fragment{
    private static final String TAG = "RssFragment";

    private ImageButton searchIb;
    private RecyclerView rssRv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rss,container,false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        rssRv = (RecyclerView) view.findViewById(R.id.rss_rv);
        RssAdapter adapter = new RssAdapter(this);
        rssRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rssRv.setAdapter(adapter);
        searchIb = (ImageButton) view.findViewById(R.id.rss_search_ib);
        searchIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + " search");
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_container_fl,new RssSearchFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


}
