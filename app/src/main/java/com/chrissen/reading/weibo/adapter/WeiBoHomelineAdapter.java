package com.chrissen.reading.weibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chrissen.reading.R;
import com.chrissen.reading.weibo.bean.RetweetedStatus;
import com.chrissen.reading.weibo.bean.Status;
import com.chrissen.reading.weibo.util.PicHelper;
import com.chrissen.reading.weibo.util.WeiBoTextHelper;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/8/8.
 */

public class WeiBoHomelineAdapter extends RecyclerView.Adapter<WeiBoHomelineAdapter.HomelineViewHolder> {

    private Fragment fragment;
    private List<Status> statusList;

    public WeiBoHomelineAdapter(Fragment fragment ,List<Status> statusList){
        this.fragment = fragment;
        this.statusList = statusList;
    }

    @Override
    public HomelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weibo_homeline,parent,false);
        return new HomelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomelineViewHolder holder, int position) {
        Status status = statusList.get(position);
        String profileImageUrl = status.getUser().getProfileImageUrl();
        String accountText = status.getUser().getScreenName();
        String contentText = status.getContent();
        String retweetedText ;
        RetweetedStatus retweetedStatus = status.getRetweetedStatus();
        if(retweetedStatus != null){
            holder.lineIv.setVisibility(View.VISIBLE);
            retweetedText = retweetedStatus.getContent();
            holder.retweetedTextTv.setText(WeiBoTextHelper.getWeiBoContent(fragment.getContext(),retweetedText,holder.retweetedTextTv));
        }else {
            holder.retweetedTextTv.setText("");
            holder.lineIv.setVisibility(View.INVISIBLE);
        }
        if(status.getPicurlList() != null){
            List<String> middlePicUrlList = PicHelper.getBmiddlePicUrl(status.getPicurlList());
            if(middlePicUrlList.size() == 1){
                holder.imageViewList.get(0).getLayoutParams().width = 400;
                holder.imageViewList.get(0).getLayoutParams().height = 600;
                Glide.with(fragment).load(middlePicUrlList.get(0)).into(holder.imageViewList.get(0));
                for(int i = 1 ; i < holder.imageViewList.size();i++){
                    Glide.clear(holder.imageViewList.get(i));
                    holder.imageViewList.get(i).setImageDrawable(null);
                }
            }else if(middlePicUrlList.size() == 2) {
                for(int i =0 ;i < middlePicUrlList.size() ;i++){
                    holder.imageViewList.get(i).getLayoutParams().width = 400;
                    holder.imageViewList.get(i).getLayoutParams().height =400;
                    Glide.with(fragment).load(middlePicUrlList.get(i))
                            .into(holder.imageViewList.get(i));
                }
                for (int j = 2 ; j < holder.imageViewList.size();j++ ){
                    Glide.clear(holder.imageViewList.get(j));
                    holder.imageViewList.get(j).setImageDrawable(null);
                }
            }else if(middlePicUrlList.size() == 3) {
                for(int i =0 ;i < middlePicUrlList.size() ;i++){
                    holder.imageViewList.get(i).getLayoutParams().width = 300;
                    holder.imageViewList.get(i).getLayoutParams().height =300;
                    Glide.with(fragment).load(middlePicUrlList.get(i))
                            .into(holder.imageViewList.get(i));
                }
                for (int j = 3 ; j < holder.imageViewList.size();j++ ){
                    Glide.clear(holder.imageViewList.get(j));
                    holder.imageViewList.get(j).setImageDrawable(null);
                }
            }else if(middlePicUrlList.size() == 4) {
                for(int i =0 ;i < middlePicUrlList.size() ;i++){
                    holder.imageViewList.get(i).getLayoutParams().width = 300;
                    holder.imageViewList.get(i).getLayoutParams().height =300;
                    Glide.with(fragment).load(middlePicUrlList.get(i)).into(holder.imageViewList.get(0));
                    Glide.with(fragment).load(middlePicUrlList.get(i)).into(holder.imageViewList.get(1));
                    Glide.with(fragment).load(middlePicUrlList.get(i)).into(holder.imageViewList.get(3));
                    Glide.with(fragment).load(middlePicUrlList.get(i)).into(holder.imageViewList.get(4));
                }
                Glide.clear(holder.imageViewList.get(2));
                holder.imageViewList.get(2).setImageDrawable(null);
                Glide.clear(holder.imageViewList.get(5));
                holder.imageViewList.get(5).setImageDrawable(null);
                Glide.clear(holder.imageViewList.get(6));
                holder.imageViewList.get(6).setImageDrawable(null);
                Glide.clear(holder.imageViewList.get(7));
                holder.imageViewList.get(7).setImageDrawable(null);
                Glide.clear(holder.imageViewList.get(8));
                holder.imageViewList.get(8).setImageDrawable(null);
            }else if(middlePicUrlList.size() == 5){
                for(int i =0 ;i < middlePicUrlList.size() ;i++){
                    holder.imageViewList.get(i).getLayoutParams().width = 300;
                    holder.imageViewList.get(i).getLayoutParams().height =300;
                    Glide.with(fragment).load(middlePicUrlList.get(i))
                            .into(holder.imageViewList.get(i));
                }
                for (int j = 5 ; j < holder.imageViewList.size();j++ ){
                    Glide.clear(holder.imageViewList.get(j));
                    holder.imageViewList.get(j).setImageDrawable(null);
                }
            }else if(middlePicUrlList.size() == 6){
                for(int i =0 ;i < middlePicUrlList.size() ;i++){
                    holder.imageViewList.get(i).getLayoutParams().width = 300;
                    holder.imageViewList.get(i).getLayoutParams().height =300;
                    Glide.with(fragment).load(middlePicUrlList.get(i))
                            .into(holder.imageViewList.get(i));
                }
                for (int j = 6 ; j < holder.imageViewList.size();j++ ){
                    Glide.clear(holder.imageViewList.get(j));
                    holder.imageViewList.get(j).setImageDrawable(null);
                }
            }else if(middlePicUrlList.size() == 7){
                for(int i =0 ;i < middlePicUrlList.size() ;i++){
                    holder.imageViewList.get(i).getLayoutParams().width = 300;
                    holder.imageViewList.get(i).getLayoutParams().height =300;
                    Glide.with(fragment).load(middlePicUrlList.get(i))
                            .into(holder.imageViewList.get(i));
                }
                for (int j = 7 ; j < holder.imageViewList.size();j++ ){
                    Glide.clear(holder.imageViewList.get(j));
                    holder.imageViewList.get(j).setImageDrawable(null);
                }
            }else if(middlePicUrlList.size() == 8){
                for(int i =0 ;i < middlePicUrlList.size() ;i++){
                    holder.imageViewList.get(i).getLayoutParams().width = 300;
                    holder.imageViewList.get(i).getLayoutParams().height =300;
                    Glide.with(fragment).load(middlePicUrlList.get(i))
                            .into(holder.imageViewList.get(i));
                }
                for (int j = 8 ; j < holder.imageViewList.size();j++ ){
                    Glide.clear(holder.imageViewList.get(j));
                    holder.imageViewList.get(j).setImageDrawable(null);
                }
            }else if(middlePicUrlList.size() == 9){
                for(int i =0 ;i < middlePicUrlList.size() ;i++){
                    holder.imageViewList.get(i).getLayoutParams().width = 300;
                    holder.imageViewList.get(i).getLayoutParams().height =300;
                    Glide.with(fragment).load(middlePicUrlList.get(i))
                            .into(holder.imageViewList.get(i));
                }
            }

        }else {
            for(int i = 0 ; i < holder.imageViewList.size() ; i++){
                Glide.clear(holder.imageViewList.get(i));
                holder.imageViewList.get(i).setImageDrawable(null);
            }
        }
        holder.accountTv.setText(accountText);
        holder.textTv.setText(WeiBoTextHelper.getWeiBoContent(fragment.getContext(),contentText,holder.textTv));
        Glide.with(fragment).load(profileImageUrl)
                .centerCrop().bitmapTransform(new CropCircleTransformation(fragment.getContext()))
                .into(holder.accountIv);
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    class HomelineViewHolder extends RecyclerView.ViewHolder {

        private ImageView accountIv;
        private TextView textTv;
        private TextView accountTv;
        private TextView retweetedTextTv;
        private ImageView lineIv;
        private ImageView pic01Iv;
        private ImageView pic02Iv;
        private ImageView pic03Iv;
        private ImageView pic04Iv;
        private ImageView pic05Iv;
        private ImageView pic06Iv;
        private ImageView pic07Iv;
        private ImageView pic08Iv;
        private ImageView pic09Iv;

        private List<ImageView> imageViewList = new ArrayList<>();

        public HomelineViewHolder(View itemView) {
            super(itemView);
            accountIv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_account_iv);
            textTv = (TextView) itemView.findViewById(R.id.item_weibo_homeline_text_tv);
            accountTv = (TextView) itemView.findViewById(R.id.item_weibo_homeline_account_tv);
            retweetedTextTv = (TextView) itemView.findViewById(R.id.item_weibo_homeline_retweeted_text_tv);
            lineIv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_line_iv);
            pic01Iv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_pic_01_iv);
            pic02Iv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_pic_02_iv);
            pic03Iv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_pic_03_iv);
            pic04Iv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_pic_04_iv);
            pic05Iv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_pic_05_iv);
            pic06Iv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_pic_06_iv);
            pic07Iv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_pic_07_iv);
            pic08Iv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_pic_08_iv);
            pic09Iv = (ImageView) itemView.findViewById(R.id.item_weibo_homeline_pic_09_iv);
            imageViewList.add(pic01Iv);
            imageViewList.add(pic02Iv);
            imageViewList.add(pic03Iv);
            imageViewList.add(pic04Iv);
            imageViewList.add(pic05Iv);
            imageViewList.add(pic06Iv);
            imageViewList.add(pic07Iv);
            imageViewList.add(pic08Iv);
            imageViewList.add(pic09Iv);
        }
    }

}
