package com.techjini.rockwellautomation.product.browse.rockwellautomation;

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
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.RockwellAutomationChildListItem;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.parentlist.RockwellAutomationParentListItem;
import com.techjini.rockwellautomation.util.AppUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Debu
 */
public class RockwellAutomationFragment extends BaseFragment
    implements ExpandableRecyclerAdapter.ExpandCollapseListener {

  private static final String TAG = RockwellAutomationFragment.class.getSimpleName();
  private TextView textViewExpandAll;
  private RecyclerView recyclerViewProductGroupList;
  private List<RockwellAutomationParentListItem> listOfRockwellAutomationParentListItems;
  private RockwellAutomationExpandableAdapter rockwellAutomationExpandableAdapter;
  private boolean isAllListItemsExpanded;
  private LinearLayoutManager linearLayoutManager;

  public RockwellAutomationFragment() {
    // Required empty public constructor
  }

  public static RockwellAutomationFragment newInstance() {
    return new RockwellAutomationFragment();
  }

  @Override public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = layoutInflater.inflate(R.layout.fragment_rockwell_automation, container, false);

    textViewExpandAll = (TextView) view.findViewById(R.id.textViewExpandAll);
    recyclerViewProductGroupList =
        (RecyclerView) view.findViewById(R.id.recyclerViewProductGroupList);

    listOfRockwellAutomationParentListItems = getListOfRockwellAutomationParentListItems();
    rockwellAutomationExpandableAdapter = new RockwellAutomationExpandableAdapter(context, this,
        listOfRockwellAutomationParentListItems);
    rockwellAutomationExpandableAdapter.setExpandCollapseListener(this);
    linearLayoutManager = new LinearLayoutManager(context);
    recyclerViewProductGroupList.setLayoutManager(linearLayoutManager);
    recyclerViewProductGroupList.setAdapter(rockwellAutomationExpandableAdapter);
    recyclerViewProductGroupList.getRecycledViewPool()
        .setMaxRecycledViews(RockwellAutomationExpandableAdapter.TYPE_CHILD, 0);
    recyclerViewProductGroupList.getRecycledViewPool()
        .setMaxRecycledViews(RockwellAutomationExpandableAdapter.TYPE_PARENT, 0);
    recyclerViewProductGroupList.setHasFixedSize(true);

    textViewExpandAll.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (isAllListItemsExpanded) {
          rockwellAutomationExpandableAdapter.collapseAllParents();
          isAllListItemsExpanded = false;
          textViewExpandAll.setText(context.getString(R.string.expand_all));
        } else {
          rockwellAutomationExpandableAdapter.expandAllParents();
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
    rockwellAutomationExpandableAdapter.onSaveInstanceState(outState);
  }

  private List<RockwellAutomationParentListItem> getListOfRockwellAutomationParentListItems() {
    List<RockwellAutomationParentListItem> listOfRockwellAutomationParentListItems =
        new ArrayList<>();
    List<ProductGroup> listOfProductGroups = AppUtil.getListOfProductGroups();

    for (int i = 0; i < listOfProductGroups.size(); i++) {
      ProductGroup productGroup = listOfProductGroups.get(i);
      List<RockwellAutomationChildListItem> listOfRockwellAutomationChildListItems =
          new ArrayList<>();
      RockwellAutomationChildListItem rockwellAutomationChildListItem =
          new RockwellAutomationChildListItem(productGroup.getListOfCategories(),
              productGroup.getProductGroupDescription());
      listOfRockwellAutomationChildListItems.add(rockwellAutomationChildListItem);

      RockwellAutomationParentListItem rockwellAutomationParentListItem =
          new RockwellAutomationParentListItem(productGroup.getProductGroupId(),
              productGroup.getProductGroupImageUri(), productGroup.getProductGroupName(),
              productGroup.getProductGroupDetails(), listOfRockwellAutomationChildListItems);

      listOfRockwellAutomationParentListItems.add(rockwellAutomationParentListItem);
    }

    Collections.sort(listOfRockwellAutomationParentListItems);
    return listOfRockwellAutomationParentListItems;
  }

  private Map<String, Integer> getMapOfAlphabeticalIndex() {
    Map<String, Integer> mapOfAlphabeticalIndex = new LinkedHashMap<>();
    Log.d(TAG, "rockwellAutomationExpandableAdapter.getItemList().size(): "
        + rockwellAutomationExpandableAdapter.getItemList().size());
    List<Object> listOfItems = rockwellAutomationExpandableAdapter.getItemList();

    for (int i = 0; i < listOfItems.size(); i++) {
      if (listOfItems.get(i) instanceof ParentWrapper) {
        ParentListItem parentListItem = ((ParentWrapper) listOfItems.get(i)).getParentListItem();
        String productGroupName =
            ((RockwellAutomationParentListItem) parentListItem).getProductGroupName();
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
