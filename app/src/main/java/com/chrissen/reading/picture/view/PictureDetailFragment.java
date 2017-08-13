package com.chrissen.reading.picture.view;


import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chrissen.reading.MyApplication;
import com.chrissen.reading.R;
import com.chrissen.reading.picture.bean.Unsplash;
import com.chrissen.reading.util.ImageHelper;
import com.chrissen.reading.util.fragmentHelper.BackHandlerHelper;
import com.chrissen.reading.util.fragmentHelper.FragmentBackHandler;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/2.
 */

public class PictureDetailFragment extends Fragment implements View.OnClickListener , FragmentBackHandler {

    private static final String UTM_SOURCE = "chrissen0814";
    private static final String UTM = "?utm_source=" + UTM_SOURCE + "&utm_medium=referral&utm_campaign=api-credit";

    private static final String UNSPLASH = "unsplash";
    private static final String TRANSITION_NAME = "transition_name";
    private static final int REQUEST_CODE = 1;
    private Unsplash unsplash;
    private String transitionName;
    private LinearLayout layout;
    private CardView imageCv;
    private ImageView fullIv;
    private TextView resourceTv;
    private ProgressBar progressBar;
    private LinearLayout setWallpaperLl , saveLl , shareLl , linkLl , authorLl;


    public static PictureDetailFragment newInstance(Unsplash unsplash, String transitionName){
        Bundle bundle = new Bundle();
        bundle.putSerializable(UNSPLASH,unsplash);
        bundle.putString(TRANSITION_NAME,transitionName);
        PictureDetailFragment fragment = new PictureDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext())
                .inflateTransition(android.R.transition.move));
        }
        unsplash = (Unsplash) getArguments().getSerializable(UNSPLASH);
        transitionName = getArguments().getString(TRANSITION_NAME);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_detail,container,false);
        layout = (LinearLayout) view.findViewById(R.id.picture_detail_layout_ll);
        fullIv = (ImageView) view.findViewById(R.id.picture_detail_full_iv);
        resourceTv = (TextView) view.findViewById(R.id.picture_detail_resource_tv);
        setWallpaperLl = (LinearLayout) view.findViewById(R.id.picture_detail_set_wallpaper_ll);
        setWallpaperLl.setOnClickListener(this);
        saveLl = (LinearLayout) view.findViewById(R.id.picture_detail_save_ll);
        saveLl.setOnClickListener(this);
        shareLl = (LinearLayout) view.findViewById(R.id.picture_detail_share_ll);
        shareLl.setOnClickListener(this);
        linkLl = (LinearLayout) view.findViewById(R.id.picture_detail_link_ll);
        linkLl.setOnClickListener(this);
        authorLl = (LinearLayout) view.findViewById(R.id.picture_detail_author_ll);
        authorLl.setOnClickListener(this);
        imageCv = (CardView) view.findViewById(R.id.picture_detail_image_cv);
        progressBar = (ProgressBar) view.findViewById(R.id.picture_detail_pb);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageCv.setTransitionName(transitionName);
        }
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Glide.with(this).load(unsplash.getUrls().getRegular()).centerCrop().into(fullIv);
        resourceTv.setText("By" + " " + unsplash.getUser().getName() + " " + "On Unsplash");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.picture_detail_set_wallpaper_ll:
                progressBar.setVisibility(View.VISIBLE);
               setWallpaper();
                break;
            case R.id.picture_detail_save_ll:
                    if(ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                            PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
                    }else {
                       saveImage();
                    }
                break;
            case R.id.picture_detail_share_ll:

                break;
            case R.id.picture_detail_link_ll:
                String linkUrl = unsplash.getLinks().getHtml() + UTM ;
                Uri linkUri = Uri.parse(linkUrl);
                Intent linkI = new Intent(Intent.ACTION_VIEW);
                linkI.setData(linkUri);
                startActivity(linkI);
                break;
            case R.id.picture_detail_author_ll:
                String authorUrl = unsplash.getUser().getUserLink().getHtml() + UTM;
                Uri authorUri = Uri.parse(authorUrl);
                Intent authorI = new Intent(Intent.ACTION_VIEW);
                authorI.setData(authorUri);
                startActivity(authorI);
                break;
        }
    }

    private void setWallpaper(){
        Observable.just(unsplash)
                .map(new Function<Unsplash, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull Unsplash unsplash) throws Exception {
                        Bitmap fullBitmap = Glide.with(getActivity()).load(unsplash.getUrls().getRegular())
                                .asBitmap()
                                .into(unsplash.getWidth()/3,unsplash.getHeight()/3)
                                .get();
                        return fullBitmap ;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        WallpaperManager wm = WallpaperManager.getInstance(getActivity());
                        try {
                            wm.setBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                        Snackbar.make(layout,"设置成功",Snackbar.LENGTH_LONG)
                                .setAction("去看看!", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                                        homeIntent.addCategory("android.intent.category.HOME");
                                        startActivity(homeIntent);
                                    }
                                }).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void saveImage(){
        progressBar.setVisibility(View.VISIBLE);
        Observable.just(unsplash)
                .map(new Function<Unsplash, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull Unsplash unsplash) throws Exception {
                        Bitmap fullBitmap = Glide.with(getActivity()).load(unsplash.getLinks().getDownload())
                                .asBitmap()
                                .into(unsplash.getWidth(),unsplash.getHeight())
                                .get();
                        return fullBitmap ;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        ImageHelper.saveImageToGallery(MyApplication.getContext(),bitmap);
                        progressBar.setVisibility(View.INVISIBLE);
                        Snackbar.make(layout,"已保存",Snackbar.LENGTH_LONG)
                                .show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    saveImage();
                }else {
                    Toast.makeText(getActivity(), "无法保存图片", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public boolean onBackPressed() {
        BottomNavigationView bnv = (BottomNavigationView) getActivity().findViewById(R.id.main_bnv);
        bnv.setVisibility(View.VISIBLE);
        return BackHandlerHelper.handleBackPress(this);
    }
}
