package com.chrissen.reading.one.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chrissen.reading.R;
import com.chrissen.reading.one.bean.OneList;
import com.chrissen.reading.one.presenter.OneListPreImpl;
import com.chrissen.reading.one.presenter.OneListPresenter;

/**
 * Created by Administrator on 2017/8/12.
 */

public class OneListFragment extends Fragment implements OneView {

    private OneListPresenter presenter;

    private ImageView pictureIv;
    private ImageView yuwenIv;
    private ImageView essayIv;
    private ImageView questionIv;
    private TextView pictureDescTv;
    private TextView yuwenTv;
    private TextView essayTv;
    private TextView questionTv;
    private CardView yuwenCv , essayCv , questionCv;

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
        yuwenIv = (ImageView) view.findViewById(R.id.onelist_yuwen_iv);
        essayIv = (ImageView) view.findViewById(R.id.onelist_essay_iv);
        questionIv = (ImageView) view.findViewById(R.id.onelist_question_iv);
        pictureDescTv = (TextView) view.findViewById(R.id.onelist_picture_foreward_tv);
        yuwenTv = (TextView) view.findViewById(R.id.onelist_yuwen_tv);
        essayTv = (TextView) view.findViewById(R.id.onelist_essay_tv);
        questionTv = (TextView) view.findViewById(R.id.onelist_question_tv);
        yuwenCv = (CardView) view.findViewById(R.id.onelist_yuwen_cv);
        essayCv = (CardView) view.findViewById(R.id.onelist_essay_cv);
        questionCv = (CardView) view.findViewById(R.id.onelist_question_cv);
        presenter.getOneList();
        return view;
    }


    @Override
    public void showOneList(OneList oneList) {
        final String yuwenImageUrl = oneList.getDate().getContenList().get(1).getImageUrl();
        final String essayImageUrl = oneList.getDate().getContenList().get(2).getImageUrl();
        final String questionImageUrl = oneList.getDate().getContenList().get(4).getImageUrl();
        final String yuwenId = oneList.getDate().getContenList().get(1).getItemId();
        final String essayId = oneList.getDate().getContenList().get(2).getItemId();
        final String questionId = oneList.getDate().getContenList().get(4).getItemId();
        Glide.with(this).load(oneList.getDate().getContenList().get(0).getImageUrl())
                .into(pictureIv);
        Glide.with(this).load(yuwenImageUrl)
                .into(yuwenIv);
        Glide.with(this).load(essayImageUrl)
                .into(essayIv);
        Glide.with(this).load(questionImageUrl)
                .into(questionIv);
        pictureDescTv.setText(oneList.getDate().getContenList().get(0).getForward());
        yuwenTv.setText(oneList.getDate().getContenList().get(1).getTitle());
        essayTv.setText(oneList.getDate().getContenList().get(2).getTitle());
        questionTv.setText(oneList.getDate().getContenList().get(4).getTitle());
        yuwenCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_container_fl,OneContentFragment.newInstance(yuwenImageUrl,yuwenId,"content"))
                        .addToBackStack(null)
                        .commit();
            }
        });
        essayCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_container_fl,OneContentFragment.newInstance(essayImageUrl,essayId,"content"))
                        .addToBackStack(null)
                        .commit();
            }
        });
        questionCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.main_container_fl,OneContentFragment.newInstance(questionImageUrl,questionId,"question"))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


}
