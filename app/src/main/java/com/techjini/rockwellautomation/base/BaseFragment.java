package com.techjini.rockwellautomation.base;

import android.app.Fragment;
import android.content.Context;

/**
 * Created by Debu
 */
public class BaseFragment extends Fragment {

  protected Context context;
  protected BaseActivity baseActivity;

  public static BaseFragment newInstance() {
    return new BaseFragment();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);

    this.context = context;
    baseActivity = (BaseActivity) context;
  }

  public BaseActivity getBaseActivity() {
    return baseActivity;
  }
}
