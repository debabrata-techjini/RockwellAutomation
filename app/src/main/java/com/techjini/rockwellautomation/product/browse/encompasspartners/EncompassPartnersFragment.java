package com.techjini.rockwellautomation.product.browse.encompasspartners;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseFragment;
import com.techjini.rockwellautomation.expandablerecyclerview.adapter.ExpandableRecyclerAdapter;
import com.techjini.rockwellautomation.expandablerecyclerview.model.ParentListItem;
import com.techjini.rockwellautomation.expandablerecyclerview.model.ParentWrapper;
import com.techjini.rockwellautomation.product.browse.ProductGroup;
import com.techjini.rockwellautomation.product.browse.encompasspartners.childlist.EncompassPartnersChildListItem;
import com.techjini.rockwellautomation.product.browse.encompasspartners.parentlist.EncompassPartnersParentListItem;
import com.techjini.rockwellautomation.util.AppUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Debu
 */
public class EncompassPartnersFragment extends BaseFragment
    implements ExpandableRecyclerAdapter.ExpandCollapseListener {

  private static final String TAG = EncompassPartnersFragment.class.getSimpleName();
  private TextView textViewExpandAll;
  private RecyclerView recyclerViewProductGroupList;
  private List<EncompassPartnersParentListItem> listOfEncompassPartnersParentListItems;
  private EncompassPartnersExpandableAdapter encompassPartnersExpandableAdapter;
  private boolean isAllListItemsExpanded;
  private LinearLayoutManager linearLayoutManager;

  public EncompassPartnersFragment() {
    // Required empty public constructor
  }

  public static EncompassPartnersFragment newInstance() {
    return new EncompassPartnersFragment();
  }

  @Override public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = layoutInflater.inflate(R.layout.fragment_encompass_partners, container, false);

    textViewExpandAll = (TextView) view.findViewById(R.id.textViewExpandAll);
    recyclerViewProductGroupList =
        (RecyclerView) view.findViewById(R.id.recyclerViewProductGroupList);

    listOfEncompassPartnersParentListItems = getListOfEncompassPartnersParentListItems();
    encompassPartnersExpandableAdapter = new EncompassPartnersExpandableAdapter(context, this,
        listOfEncompassPartnersParentListItems);
    encompassPartnersExpandableAdapter.setExpandCollapseListener(this);
    linearLayoutManager = new LinearLayoutManager(context);
    recyclerViewProductGroupList.setLayoutManager(linearLayoutManager);
    recyclerViewProductGroupList.setAdapter(encompassPartnersExpandableAdapter);
    recyclerViewProductGroupList.getRecycledViewPool()
        .setMaxRecycledViews(EncompassPartnersExpandableAdapter.TYPE_CHILD, 0);
    recyclerViewProductGroupList.getRecycledViewPool()
        .setMaxRecycledViews(EncompassPartnersExpandableAdapter.TYPE_PARENT, 0);
    recyclerViewProductGroupList.setHasFixedSize(true);

    textViewExpandAll.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (isAllListItemsExpanded) {
          encompassPartnersExpandableAdapter.collapseAllParents();
          isAllListItemsExpanded = false;
          textViewExpandAll.setText(context.getString(R.string.expand_all));
        } else {
          encompassPartnersExpandableAdapter.expandAllParents();
          isAllListItemsExpanded = true;
          textViewExpandAll.setText(context.getString(R.string.collapse_all));
        }
      }
    });

    displayAlphabeticalIndex(layoutInflater, view);

    return view;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    encompassPartnersExpandableAdapter.onSaveInstanceState(outState);
  }

  private List<EncompassPartnersParentListItem> getListOfEncompassPartnersParentListItems() {
    List<EncompassPartnersParentListItem> listOfEncompassPartnersParentListItems =
        new ArrayList<>();
    List<ProductGroup> listOfProductGroups = AppUtil.getListOfProductGroups();

    for (int i = 0; i < listOfProductGroups.size(); i++) {
      ProductGroup productGroup = listOfProductGroups.get(i);
      List<EncompassPartnersChildListItem> listOfEncompassPartnersChildListItems =
          new ArrayList<>();
      EncompassPartnersChildListItem encompassPartnersChildListItem =
          new EncompassPartnersChildListItem(productGroup.getListOfCategories(),
              productGroup.getProductGroupDescription());
      listOfEncompassPartnersChildListItems.add(encompassPartnersChildListItem);

      EncompassPartnersParentListItem encompassPartnersParentListItem =
          new EncompassPartnersParentListItem(productGroup.getProductGroupId(),
              productGroup.getProductGroupImageUri(), productGroup.getProductGroupName(),
              productGroup.getProductGroupDetails(), listOfEncompassPartnersChildListItems);

      listOfEncompassPartnersParentListItems.add(encompassPartnersParentListItem);
    }

    Collections.sort(listOfEncompassPartnersParentListItems);
    return listOfEncompassPartnersParentListItems;
  }

  private Map<String, Integer> getMapOfAlphabeticalIndex() {
    Map<String, Integer> mapOfAlphabeticalIndex = new LinkedHashMap<>();
    Log.d(TAG, "encompassPartnersExpandableAdapter.getItemList().size(): "
        + encompassPartnersExpandableAdapter.getItemList().size());
    List<Object> listOfItems = encompassPartnersExpandableAdapter.getItemList();

    for (int i = 0; i < listOfItems.size(); i++) {
      if (listOfItems.get(i) instanceof ParentWrapper) {
        ParentListItem parentListItem = ((ParentWrapper) listOfItems.get(i)).getParentListItem();
        String productGroupName =
            ((EncompassPartnersParentListItem) parentListItem).getProductGroupName();
        String alphabeticalIndex = productGroupName.substring(0, 1);

        if (mapOfAlphabeticalIndex.get(alphabeticalIndex) == null) {
          mapOfAlphabeticalIndex.put(alphabeticalIndex, i);
        }
      }
    }

    return mapOfAlphabeticalIndex;
  }

  private void displayAlphabeticalIndex(LayoutInflater layoutInflater, View view) {
    Map<String, Integer> mapOfAlphabeticalIndex = getMapOfAlphabeticalIndex();
    LinearLayout layoutAlphabeticalIndex =
        (LinearLayout) view.findViewById(R.id.layoutAlphabeticalIndex);
    TextView textViewAlphabeticalIndex;
    List<String> alphabeticalIndexList = new ArrayList<>(mapOfAlphabeticalIndex.keySet());

    for (String alphabeticalIndex : alphabeticalIndexList) {
      textViewAlphabeticalIndex =
          (TextView) layoutInflater.inflate(R.layout.alphabetical_index_item, null);
      textViewAlphabeticalIndex.setText(alphabeticalIndex);

      textViewAlphabeticalIndex.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          // When the alphabetical index is clicked, we need to fetch map
          // of alphabetical index again, as list might be expanded / collapsed.
          Map<String, Integer> mapOfAlphabeticalIndex = getMapOfAlphabeticalIndex();
          TextView textViewAlphabeticalIndex = (TextView) view;
          int alphabeticalIndex =
              mapOfAlphabeticalIndex.get(textViewAlphabeticalIndex.getText().toString());
          linearLayoutManager.scrollToPositionWithOffset(alphabeticalIndex, 0);
        }
      });

      layoutAlphabeticalIndex.addView(textViewAlphabeticalIndex);
    }
  }

  @Override public void onListItemExpanded(int position) {
    //Log.d(TAG, "onListItemExpanded() -> position: " + position);
  }

  @Override public void onListItemCollapsed(int position) {
    //Log.d(TAG, "onListItemCollapsed() -> position: " + position);
  }

  public void disallowProductGroupListToInterceptTouchEvent() {
    //Log.d(TAG, "disallowProductGroupListToInterceptTouchEvent()");
    recyclerViewProductGroupList.requestDisallowInterceptTouchEvent(true);
  }
}
