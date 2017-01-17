package com.techjini.rockwellautomation.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.techjini.rockwellautomation.util.AppUtil;

/**
 * Created by Debu
 */
public abstract class BaseActivity extends AppCompatActivity {

  private static final String TAG = BaseActivity.class.getSimpleName();
  protected View rootView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (AppUtil.isTablet(this)) {
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
      RockwellApplication.getInstance().setIsTablet(true);
    } else {
      Log.d(TAG, "Device is not a tablet");
      // TODO: Take appropriate action
    }

    rootView = getLayoutInflater().inflate(getLayoutResourceId(), null);
    setContentView(rootView);
  }

  protected abstract int getLayoutResourceId();
}
