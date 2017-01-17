package com.techjini.rockwellautomation.base;

import android.content.Context;
import android.support.v4.app.DialogFragment;

/**
 * Created by Debu
 */
public class BaseDialogFragment extends DialogFragment {

  protected Context context;
  protected BaseActivity baseActivity;

  public static BaseDialogFragment newInstance() {
    return new BaseDialogFragment();
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
