package com.techjini.rockwellautomation.product.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseFragment;

/**
 * Created by Debu
 */
public class ProductsSearchFragment extends BaseFragment {

  public ProductsSearchFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_products_search, container, false);
  }
}
