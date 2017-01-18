package com.techjini.rockwellautomation.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Rupak, Debu
 */
public class BaseFragment extends Fragment {

  protected Context context;
  protected BaseActivity baseActivity;

  @Override public void onAttach(Context context) {
    super.onAttach(context);

    this.context = context;
    baseActivity = (BaseActivity) context;
  }
}
