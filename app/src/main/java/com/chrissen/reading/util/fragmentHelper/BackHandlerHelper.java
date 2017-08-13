package com.chrissen.reading.util.fragmentHelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public class BackHandlerHelper {

    public static boolean handleBackPress(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();

        if (fragments == null) return false;

        for (int i = fragments.size() - 1; i >= 0; i--) {
            Fragment child = fragments.get(i);

            if (isFragmentBackHandled(child)) {
                return true;
            }
        }

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            return true;
        }
        return false;
    }

    public static boolean handleBackPress(Fragment fragment) {
        return handleBackPress(fragment.getChildFragmentManager());
    }

    public static boolean handleBackPress(FragmentActivity fragmentActivity) {
        return handleBackPress(fragmentActivity.getSupportFragmentManager());
    }


    public static boolean isFragmentBackHandled(Fragment fragment) {
        return fragment != null
                && fragment.isVisible()
                && fragment.getUserVisibleHint() //for ViewPager
                && fragment instanceof FragmentBackHandler
                && ((FragmentBackHandler) fragment).onBackPressed();
    }

}
