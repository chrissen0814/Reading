package com.chrissen.reading.personal;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.chrissen.reading.R;

/**
 * Created by Administrator on 2017/8/11.
 */

public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.preference);
    }
}
