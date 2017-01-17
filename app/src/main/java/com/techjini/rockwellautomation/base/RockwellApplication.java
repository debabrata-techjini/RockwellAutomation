package com.techjini.rockwellautomation.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Debu
 */
public class RockwellApplication extends Application {

  private static RockwellApplication rockwellApplication;
  private boolean isTablet;

  @Override public void onCreate() {
    super.onCreate();

    rockwellApplication = this;
  }

  public static Context getContext() {
    return rockwellApplication;
  }

  public static synchronized RockwellApplication getInstance() {
    return rockwellApplication;
  }

  public void setIsTablet(boolean isTablet) {
    this.isTablet = isTablet;
  }

  public boolean isTablet() {
    return isTablet;
  }
}
