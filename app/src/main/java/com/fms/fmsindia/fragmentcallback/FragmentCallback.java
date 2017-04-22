package com.fms.fmsindia.fragmentcallback;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by krishna on 17/8/15.
 */
public class FragmentCallback extends Fragment {

    public FragmentCallBackListener fragmentCallBackListener;
    public interface FragmentCallBackListener{
        public void navigateFragment(Fragment fragment, String title);

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            fragmentCallBackListener = (FragmentCallBackListener) activity;
        } catch (ClassCastException castException) {
            /** The activity does not implement the listener. */
        }
    }


}
