package com.chrissen.reading.util.fragmentHelper;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.View;

import com.chrissen.reading.R;

/**
 * Created by Administrator on 2017/8/3.
 */

public class FragmentTransitionHelper {


    public static void startFargment(Context context , Fragment currentFragment , Fragment targetFragment , View transitionView , String transitionName){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            currentFragment.setSharedElementReturnTransition(TransitionInflater.from(context).inflateTransition(R.transition.default_transition));
            currentFragment.setExitTransition(new Fade());
            targetFragment.setSharedElementEnterTransition(TransitionInflater.from(context).inflateTransition(R.transition.default_transition));
            targetFragment.setEnterTransition(new Fade());
        }
        if(!targetFragment.isAdded()){
            currentFragment.getFragmentManager().beginTransaction()
                    .hide(currentFragment)
                    .add(R.id.main_container_fl,targetFragment)
                    .addToBackStack(null)
                    .commit();
        }else {
            currentFragment.getFragmentManager().beginTransaction()
                    .hide(currentFragment)
                    .show(targetFragment)
                    .addToBackStack(null)
                    .commit();
        }
        /*currentFragment.getFragmentManager().beginTransaction()
                .replace(R.id.main_container_fl,targetFragment)
                .addSharedElement(transitionView,transitionName)
                .addToBackStack(null)
                .commit();*/
        currentFragment.getActivity().findViewById(R.id.main_bnv).setVisibility(View.GONE);
    }

}
