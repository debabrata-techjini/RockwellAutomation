package com.techjini.rockwellautomation.product.browse.encompasspartners;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseFragment;

/**
 * Created by Debu
 */
public class EncompassPartnersFragment extends BaseFragment {

  public EncompassPartnersFragment() {
    // Required empty public constructor
  }

  public static EncompassPartnersFragment newInstance() {
    return new EncompassPartnersFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_encompass_partners, container, false);
  }
}
