package com.chrissen.reading.rss.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chrissen.reading.R;
import com.chrissen.reading.rss.bean.Feed;
import com.chrissen.reading.rss.view.StreamFragment;

import org.litepal.crud.DataSupport;

import java.lang.reflect.Field;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/8/11.
 */

public class RssAdapter extends RecyclerView.Adapter<RssAdapter.RssViewHolder> {

    private Fragment fragment;
    private List<Feed> savedFeeds;

    public RssAdapter(Fragment fragment){
        this.fragment = fragment;
        savedFeeds = DataSupport.findAll(Feed.class);
    }

    @Override
    public RssViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rss,parent,false);
        return new RssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RssViewHolder holder, final int position) {
        final Feed feed = savedFeeds.get(position);
        if(feed.getVisualUrl() != null){
            Glide.with(fragment).load(feed.getVisualUrl())
                    .bitmapTransform(new CropCircleTransformation(fragment.getContext()))
                    .into(holder.iconIv);
        }else {
            Glide.with(fragment).load(R.drawable.icon_rss).into(holder.iconIv);
        }
        holder.titleTv.setText(feed.getTitle());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.getFragmentManager().beginTransaction()
                        .replace(R.id.main_container_fl,StreamFragment.newInstance(feed.getTitle(),feed.getFeedId()))
                        .addToBackStack(null)
                        .commit();
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopupMenu(v,holder.getAdapterPosition());
                return true;
            }
        });
    }

    private void showPopupMenu(View view , final int position){
        PopupMenu popupMenu = new PopupMenu(fragment.getActivity(),view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper helper = (MenuPopupHelper) field.get(popupMenu);
            helper.setForceShowIcon(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.popup_menu_delete:
                        savedFeeds.get(position).delete();
                        savedFeeds.remove(position);
                        RssAdapter.this.notifyItemRemoved(position);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return savedFeeds.size();
    }

    class RssViewHolder extends RecyclerView.ViewHolder{

        private View view;
        private ImageView iconIv;
        private TextView titleTv;

        public RssViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            iconIv = (ImageView) itemView.findViewById(R.id.item_rss_icon_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item_rss_title_tv);
        }
    }

}
