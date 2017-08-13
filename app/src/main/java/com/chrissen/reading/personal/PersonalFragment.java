package com.chrissen.reading.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chrissen.reading.R;

/**
 * Created by Administrator on 2017/8/11.
 */

public class PersonalFragment extends Fragment {

    private SettingFragment sf;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sf = new SettingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal,container,false);
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.main_container_fl,sf)
                .commit();
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getFragmentManager().beginTransaction()
                .remove(sf)
                .commit();
    }
}
