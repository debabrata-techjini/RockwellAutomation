package com.techjini.rockwellautomation.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseActivity;
import com.techjini.rockwellautomation.home.HomeScreenActivity;
import com.techjini.rockwellautomation.setting.SettingsFragment;
import com.techjini.rockwellautomation.util.AppUtil;
import com.techjini.rockwellautomation.util.Constants;

/**
 * Created by Rupak, Debu
 */
public class SplashScreenActivity extends BaseActivity
    implements SettingsFragment.OnSettingsPopupCloseListener {

  private static final String SETTINGS_POPUP_TAG = "settingsPopup";
  private SettingsFragment settingsFragment;
  private boolean isSettingsSaved;
  private Handler handlerToStartHomeScreenAfterDelay;
  private Runnable runnableToStartHomeScreen;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    isSettingsSaved = AppUtil.getIsSettingsSavedFromPref(this);
    handlerToStartHomeScreenAfterDelay = new Handler(Looper.getMainLooper());

    runnableToStartHomeScreen = new Runnable() {
      @Override public void run() {
        Intent intent = new Intent(SplashScreenActivity.this, HomeScreenActivity.class);
        startActivity(intent);
        finish();
      }
    };

    if (!isSettingsSaved) {
      showSettingsPopup();
    } else if (savedInstanceState == null) {
      startHomeScreenActivityAfterDelay();
    }
  }

  @Override protected void onPause() {
    super.onPause();

    handlerToStartHomeScreenAfterDelay.removeCallbacks(runnableToStartHomeScreen);
  }

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_splash_screen;
  }

  private void showSettingsPopup() {
    settingsFragment = SettingsFragment.newInstance();
    settingsFragment.show(getSupportFragmentManager(), SETTINGS_POPUP_TAG);
  }

  private void startHomeScreenActivityAfterDelay() {
    handlerToStartHomeScreenAfterDelay.postDelayed(runnableToStartHomeScreen,
        Constants.SPLASH_SCREEN_TIME_OUT_IN_MILLISEC);
  }

  @Override public void onSettingsPopupClose() {
    startHomeScreenActivityAfterDelay();
  }
}
