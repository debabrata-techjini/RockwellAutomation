package com.techjini.rockwellautomation.setting;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseDialogFragment;
import com.techjini.rockwellautomation.util.AppUtil;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Debu
 */
public class SettingsFragment extends BaseDialogFragment
    implements AdapterView.OnItemSelectedListener {

  private static final String TAG = SettingsFragment.class.getSimpleName();
  private Spinner spinnerCountry;
  private Spinner spinnerRole;
  private Button buttonGo;
  private List listOfCountries;
  private List listOfRoles;
  private String selectedCountry;
  private String selectedRole;
  private boolean isSettingsSaved;
  private OnSettingsPopupClosedListener onSettingsPopupClosedListener;

  public interface OnSettingsPopupClosedListener {
    void onSettingsPopupClosed();
  }

  public SettingsFragment() {

  }

  public static SettingsFragment newInstance() {
    return new SettingsFragment();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);

    onSettingsPopupClosedListener = (OnSettingsPopupClosedListener) getActivity();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_settings, container, false);

    spinnerCountry = (Spinner) view.findViewById(R.id.spinnerCountry);
    spinnerRole = (Spinner) view.findViewById(R.id.spinnerRole);
    buttonGo = (Button) view.findViewById(R.id.buttonGo);

    listOfCountries = getListOfCounties();
    listOfRoles = getListOfRoles();

    ArrayAdapter<String> countryAdapter =
        new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listOfCountries);
    countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerCountry.setAdapter(countryAdapter);
    spinnerCountry.setOnItemSelectedListener(this);

    ArrayAdapter<String> roleAdapter =
        new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listOfRoles);
    roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerRole.setAdapter(roleAdapter);
    spinnerRole.setOnItemSelectedListener(this);

    buttonGo.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if ((!TextUtils.isEmpty(selectedCountry) && (!TextUtils.isEmpty(selectedRole)))) {
          // TODO: Need to save selectedCountry & selectedRole
          isSettingsSaved = true;
          Log.d(TAG, "Saving isSettingsSaved to pref as true");
          AppUtil.setIsSettingsSavedToPref(baseActivity, isSettingsSaved);
          dismiss();
          onSettingsPopupClosedListener.onSettingsPopupClosed();
        }
      }
    });

    getDialog().setCanceledOnTouchOutside(false);
    return view;
  }

  @Override public void onResume() {
    super.onResume();

    int width = getResources().getDimensionPixelSize(R.dimen.card_view_width);
    int height = getResources().getDimensionPixelSize(R.dimen.card_view_height);
    getDialog().getWindow().setLayout(width, height);
  }

  private List<String> getListOfCounties() {
    return Arrays.asList("India", "US");
  }

  private List<String> getListOfRoles() {
    return Arrays.asList("Partner", "Admin");
  }

  @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    final int viewId = parent.getId();

    switch (viewId) {
      case R.id.spinnerCountry:
        selectedCountry = parent.getItemAtPosition(position).toString();
        Log.d(TAG, "Selected country: " + selectedCountry);
        break;
      case R.id.spinnerRole:
        selectedRole = parent.getItemAtPosition(position).toString();
        Log.d(TAG, "Selected role: " + selectedRole);
        break;
    }
  }

  @Override public void onNothingSelected(AdapterView<?> adapterView) {

  }
}
