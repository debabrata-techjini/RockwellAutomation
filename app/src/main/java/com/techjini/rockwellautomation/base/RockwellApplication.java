package com.techjini.rockwellautomation.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Rupak, Debu
 */
public class RockwellApplication extends Application {

  private static Context context;
  private static RockwellApplication rockwellApplication;
  private boolean isTablet;

  public static Context getAppContext() {
    return context;
  }

  public static RockwellApplication getInstance() {
    return rockwellApplication;
  }

  @Override public void onCreate() {
    super.onCreate();

    context = getApplicationContext();
    rockwellApplication = this;
  }

  public void setIsTablet(boolean isTablet) {
    this.isTablet = isTablet;
  }

  public boolean isTablet() {
    return isTablet;
  }
}
