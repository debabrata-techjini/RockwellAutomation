package com.techjini.rockwellautomation.product.browse.rockwellautomation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseFragment;
import com.techjini.rockwellautomation.expandablerecyclerview.adapter.ExpandableRecyclerAdapter;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.RockwellAutomationChildListItem;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.category.Category;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.subcategory.SubCategory;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.parentlist.RockwellAutomationParentListItem;
import java.util.ArrayList;
import java.util.List;

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

  public RockwellAutomationFragment() {
    // Required empty public constructor
  }

  public static RockwellAutomationFragment newInstance() {
    return new RockwellAutomationFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_rockwell_automation, container, false);

    textViewExpandAll = (TextView) view.findViewById(R.id.textViewExpandAll);
    recyclerViewProductGroupList =
        (RecyclerView) view.findViewById(R.id.recyclerViewProductGroupList);

    listOfRockwellAutomationParentListItems = getListOfRockwellAutomationParentListItems();
    rockwellAutomationExpandableAdapter = new RockwellAutomationExpandableAdapter(context, this,
        listOfRockwellAutomationParentListItems);
    rockwellAutomationExpandableAdapter.setExpandCollapseListener(this);
    recyclerViewProductGroupList.setLayoutManager(new LinearLayoutManager(context));
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

    return view;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    rockwellAutomationExpandableAdapter.onSaveInstanceState(outState);
  }

  private List<RockwellAutomationParentListItem> getListOfRockwellAutomationParentListItems() {
    List<RockwellAutomationParentListItem> listOfRockwellAutomationParentListItems =
        new ArrayList<>();
    List<ProductGroup> listOfProductGroups = getListOfProductGroups();

    for (int i = 0; i < listOfProductGroups.size(); i++) {
      ProductGroup productGroup = listOfProductGroups.get(i);
      List<RockwellAutomationChildListItem> listOfRockwellAutomationChildListItem =
          new ArrayList<>();
      RockwellAutomationChildListItem rockwellAutomationChildListItem =
          new RockwellAutomationChildListItem(productGroup.getListOfCategories(),
              productGroup.getProductGroupDescription());
      listOfRockwellAutomationChildListItem.add(rockwellAutomationChildListItem);

      RockwellAutomationParentListItem rockwellAutomationParentListItem =
          new RockwellAutomationParentListItem(productGroup.getProductGroupId(),
              productGroup.getProductGroupImageUri(), productGroup.getProductGroupName(),
              productGroup.getProductGroupDetails(), listOfRockwellAutomationChildListItem);

      listOfRockwellAutomationParentListItems.add(rockwellAutomationParentListItem);
    }

    return listOfRockwellAutomationParentListItems;
  }

  // TODO: Need to implement; currently, it returns a dummy list of product groups
  private List<ProductGroup> getListOfProductGroups() {
    List<ProductGroup> listOfProductGroups = new ArrayList<>();

    ProductGroup productGroup1 =
        new ProductGroup(1, "A product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child1"));
    ProductGroup productGroup2 =
        new ProductGroup(2, "N product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child2"));
    ProductGroup productGroup3 =
        new ProductGroup(3, "D product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child3"));
    ProductGroup productGroup4 =
        new ProductGroup(4, "X product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child4"));
    ProductGroup productGroup5 =
        new ProductGroup(5, "K product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child5"));
    ProductGroup productGroup6 =
        new ProductGroup(6, "S product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child6"));

    listOfProductGroups.add(productGroup1);
    listOfProductGroups.add(productGroup2);
    listOfProductGroups.add(productGroup3);
    listOfProductGroups.add(productGroup4);
    listOfProductGroups.add(productGroup5);
    listOfProductGroups.add(productGroup6);

    return listOfProductGroups;
  }

  // TODO: Need to implement; currently, it returns a dummy list of categories
  private List<Category> getListOfCategories(String child) {
    List<Category> listOfCategories = new ArrayList<>();

    Category category1 =
        new Category(1, child + "-" + "Category 1", getListOfSubCategories(child, "Category 1"));
    Category category2 =
        new Category(2, child + "-" + "Category 2", getListOfSubCategories(child, "Category 2"));
    Category category3 =
        new Category(3, child + "-" + "Category 3", getListOfSubCategories(child, "Category 3"));
    Category category4 =
        new Category(4, child + "-" + "Category 4", getListOfSubCategories(child, "Category 4"));
    Category category5 =
        new Category(5, child + "-" + "Category 5", getListOfSubCategories(child, "Category 5"));
    Category category6 =
        new Category(6, child + "-" + "Category 6", getListOfSubCategories(child, "Category 6"));

    listOfCategories.add(category1);
    listOfCategories.add(category2);
    listOfCategories.add(category3);
    listOfCategories.add(category4);
    listOfCategories.add(category5);
    listOfCategories.add(category6);

    return listOfCategories;
  }

  // TODO: Need to implement; currently, it returns a dummy list of sub categories
  private List<SubCategory> getListOfSubCategories(String child, String category) {
    List<SubCategory> listOfSubCategories = new ArrayList<>();

    SubCategory subCategory1 =
        new SubCategory(1, child + "-" + category + "-" + "Sub category 1", null);
    SubCategory subCategory2 =
        new SubCategory(2, child + "-" + category + "-" + "Sub category 2", null);
    SubCategory subCategory3 =
        new SubCategory(3, child + "-" + category + "-" + "Sub category 3", null);
    SubCategory subCategory4 =
        new SubCategory(4, child + "-" + category + "-" + "Sub category 4", null);
    SubCategory subCategory5 =
        new SubCategory(5, child + "-" + category + "-" + "Sub category 5", null);
    SubCategory subCategory6 =
        new SubCategory(6, child + "-" + category + "-" + "Sub category 6", null);

    listOfSubCategories.add(subCategory1);
    listOfSubCategories.add(subCategory2);
    listOfSubCategories.add(subCategory3);
    listOfSubCategories.add(subCategory4);
    listOfSubCategories.add(subCategory5);
    listOfSubCategories.add(subCategory6);

    return listOfSubCategories;
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
