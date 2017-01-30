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
import com.techjini.rockwellautomation.product.browse.category.Category;
import com.techjini.rockwellautomation.product.browse.encompasspartners.childlist.EncompassPartnersChildListItem;
import com.techjini.rockwellautomation.product.browse.encompasspartners.parentlist.EncompassPartnersParentListItem;
import com.techjini.rockwellautomation.product.browse.subcategory.SubCategory;
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
    recyclerViewProductGroupList.setLayoutManager(new LinearLayoutManager(context));
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
    List<ProductGroup> listOfProductGroups = getListOfProductGroups();

    for (int i = 0; i < listOfProductGroups.size(); i++) {
      ProductGroup productGroup = listOfProductGroups.get(i);
      List<EncompassPartnersChildListItem> listOfEncompassPartnersChildListItem = new ArrayList<>();
      EncompassPartnersChildListItem encompassPartnersChildListItem =
          new EncompassPartnersChildListItem(productGroup.getListOfCategories(),
              productGroup.getProductGroupDescription());
      listOfEncompassPartnersChildListItem.add(encompassPartnersChildListItem);

      EncompassPartnersParentListItem encompassPartnersParentListItem =
          new EncompassPartnersParentListItem(productGroup.getProductGroupId(),
              productGroup.getProductGroupImageUri(), productGroup.getProductGroupName(),
              productGroup.getProductGroupDetails(), listOfEncompassPartnersChildListItem);

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
          recyclerViewProductGroupList.scrollToPosition(
              mapOfAlphabeticalIndex.get(textViewAlphabeticalIndex.getText().toString()));
        }
      });

      layoutAlphabeticalIndex.addView(textViewAlphabeticalIndex);
    }
  }

  // TODO: Need to implement; currently, it returns a dummy list of product groups
  private List<ProductGroup> getListOfProductGroups() {
    List<ProductGroup> listOfProductGroups = new ArrayList<>();

    ProductGroup productGroup1 =
        new ProductGroup(1, "A product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child1"));
    ProductGroup productGroup2 =
        new ProductGroup(2, "B product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child2"));
    ProductGroup productGroup3 =
        new ProductGroup(3, "C product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child3"));
    ProductGroup productGroup4 =
        new ProductGroup(4, "D product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child4"));
    ProductGroup productGroup5 =
        new ProductGroup(5, "E product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child5"));
    ProductGroup productGroup6 =
        new ProductGroup(6, "F product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child6"));
    ProductGroup productGroup7 =
        new ProductGroup(7, "G product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child7"));
    ProductGroup productGroup8 =
        new ProductGroup(8, "H product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child8"));
    ProductGroup productGroup9 =
        new ProductGroup(9, "I product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child9"));
    ProductGroup productGroup10 =
        new ProductGroup(10, "J product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child10"));
    ProductGroup productGroup11 =
        new ProductGroup(11, "K product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child11"));
    ProductGroup productGroup12 =
        new ProductGroup(12, "L product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child12"));
    ProductGroup productGroup13 =
        new ProductGroup(13, "M product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child13"));
    ProductGroup productGroup14 =
        new ProductGroup(14, "N product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child14"));
    ProductGroup productGroup15 =
        new ProductGroup(15, "O product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child15"));
    ProductGroup productGroup16 =
        new ProductGroup(16, "P product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child16"));
    ProductGroup productGroup17 =
        new ProductGroup(17, "Q product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child17"));
    ProductGroup productGroup18 =
        new ProductGroup(18, "R product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child18"));
    ProductGroup productGroup19 =
        new ProductGroup(19, "S product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child19"));
    ProductGroup productGroup20 =
        new ProductGroup(20, "T product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child20"));
    ProductGroup productGroup21 =
        new ProductGroup(21, "U product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child21"));
    ProductGroup productGroup22 =
        new ProductGroup(22, "V product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child22"));
    ProductGroup productGroup23 =
        new ProductGroup(23, "W product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child23"));
    ProductGroup productGroup24 =
        new ProductGroup(24, "X product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child24"));
    ProductGroup productGroup25 =
        new ProductGroup(25, "Y product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child25"));
    ProductGroup productGroup26 =
        new ProductGroup(26, "Z product group name", "Product group details goes here.",
            "Description about the product group goes here.", null, getListOfCategories("Child26"));

    listOfProductGroups.add(productGroup1);
    listOfProductGroups.add(productGroup2);
    listOfProductGroups.add(productGroup3);
    listOfProductGroups.add(productGroup4);
    listOfProductGroups.add(productGroup5);
    listOfProductGroups.add(productGroup6);
    listOfProductGroups.add(productGroup7);
    listOfProductGroups.add(productGroup8);
    listOfProductGroups.add(productGroup9);
    listOfProductGroups.add(productGroup10);
    listOfProductGroups.add(productGroup11);
    listOfProductGroups.add(productGroup12);
    listOfProductGroups.add(productGroup13);
    listOfProductGroups.add(productGroup14);
    listOfProductGroups.add(productGroup15);
    listOfProductGroups.add(productGroup16);
    listOfProductGroups.add(productGroup17);
    listOfProductGroups.add(productGroup18);
    listOfProductGroups.add(productGroup19);
    listOfProductGroups.add(productGroup20);
    listOfProductGroups.add(productGroup21);
    listOfProductGroups.add(productGroup22);
    listOfProductGroups.add(productGroup23);
    listOfProductGroups.add(productGroup24);
    listOfProductGroups.add(productGroup25);
    listOfProductGroups.add(productGroup26);

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
