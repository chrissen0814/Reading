package com.chrissen.reading.util.fragmentHelper;

import android.support.v4.app.Fragment;
import android.view.View;

import com.chrissen.reading.R;

/**
 * Created by Administrator on 2017/8/3.
 */

public class FragmentTransitionHelper {


    public static void startFargment( Fragment currentFragment , Fragment targetFragment){
        if(!targetFragment.isAdded()){
            currentFragment.getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fragment_slide_right_in,R.anim.fragment_slide_left_out,R.anim.fragment_slide_left_in,R.anim.fragment_slide_right_out)
                    .hide(currentFragment)
                    .add(R.id.main_container_fl,targetFragment)
                    .addToBackStack(null)
                    .commit();
        }else {
            currentFragment.getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.fragment_slide_right_in,R.anim.fragment_slide_left_out,R.anim.fragment_slide_left_in,R.anim.fragment_slide_right_out)
                    .hide(currentFragment)
                    .show(targetFragment)
                    .addToBackStack(null)
                    .commit();
        }
        currentFragment.getActivity().findViewById(R.id.main_bnv).setVisibility(View.GONE);
    }

    public static void returnFragment(Fragment currentFragment , Fragment returnFragment){
        currentFragment.getFragmentManager().beginTransaction()
                .hide(currentFragment)
                .show(returnFragment)
                .commit();
    }

}
