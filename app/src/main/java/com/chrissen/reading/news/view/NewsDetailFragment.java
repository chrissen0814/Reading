package com.chrissen.reading.news.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chrissen.reading.MyApplication;
import com.chrissen.reading.R;
import com.chrissen.reading.news.bean.News;
import com.chrissen.reading.util.fragmentHelper.BackHandlerHelper;
import com.chrissen.reading.util.fragmentHelper.FragmentBackHandler;
import com.umeng.analytics.MobclickAgent;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnImageClickListener;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class NewsDetailFragment extends Fragment implements FragmentBackHandler {

    private static final String Info = "info";

    private News.Result.Info info;

    private TextView titleTv;
    private TextView contentTv;
    private ImageView contentImageIv;
    private Button linkBt;
    private CollapsingToolbarLayout ctl;

    public static NewsDetailFragment newInstance(News.Result.Info info){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Info,info);
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        info = (News.Result.Info) getArguments().getSerializable(Info);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail,container,false);
        ctl = (CollapsingToolbarLayout) view.findViewById(R.id.news_detail_ctl);
        titleTv = (TextView) view.findViewById(R.id.article_detail_title_tv);
        contentTv = (TextView) view.findViewById(R.id.article_detail_content_tv);
        contentImageIv = (ImageView) view.findViewById(R.id.news_detail_image_iv);
        linkBt = (Button) view.findViewById(R.id.news_detail_link_bt);
        initLayout();
        return view;
    }

    private void initLayout() {
        ctl.setTitle(info.getSrc());
        if(info.getPic().equals("")){
            Glide.with(this).load(R.drawable.pic_news).into(contentImageIv);
        }else{
            Glide.with(this).load(info.getPic()).into(contentImageIv);
        }
        titleTv.setText(info.getTitle());
        linkBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkIntent = new Intent(Intent.ACTION_VIEW);
                String url = info.getUrl();
                Uri linkUri = Uri.parse(url);
                linkIntent.setData(linkUri);
                startActivity(linkIntent);
            }
        });
        RichText.from(info.getContent())
                .autoPlay(true)
                .borderRadius(10)
                .scaleType(ImageHolder.ScaleType.FIT_CENTER)
                .imageClick(new OnImageClickListener() {
            @Override
            public void imageClicked(List<String> imageUrls, int position) {

                Toast.makeText(getActivity(), "Clicked" + imageUrls.get(position), Toast.LENGTH_SHORT).show();
            }
                }).urlClick(new OnUrlClickListener() {
            @Override
            public boolean urlClicked(String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(url);
                intent.setData(uri);
                startActivity(intent);
                return true;
            }
        }).into(contentTv);

        boolean doubleClick = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).getBoolean("double_to_close",false);
        if (doubleClick) {
            doubleToClose();
        }
    }

    private void doubleToClose() {
        final GestureDetector gestureDetector = new GestureDetector(getActivity(),new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                getActivity().onBackPressed();
                return super.onDoubleTap(e);
            }
        });
        contentTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event) ;
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        BottomNavigationView bnv = (BottomNavigationView) getActivity().findViewById(R.id.main_bnv);
        bnv.setVisibility(View.VISIBLE);
        return BackHandlerHelper.handleBackPress(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("NewsDetailFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("NewsDetailFragment");
    }

}
