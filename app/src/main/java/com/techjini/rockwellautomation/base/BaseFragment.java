package com.techjini.rockwellautomation.base;

import android.app.Fragment;
import android.content.Context;

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
