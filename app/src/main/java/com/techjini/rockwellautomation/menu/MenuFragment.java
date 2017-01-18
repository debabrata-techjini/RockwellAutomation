package com.techjini.rockwellautomation.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseFragment;

/**
 * Created by Debu
 */
public class MenuFragment extends BaseFragment {

  public MenuFragment() {
    // Required empty public constructor
  }

  public static MenuFragment newInstance() {
    return new MenuFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_menu, container, false);
  }
}
