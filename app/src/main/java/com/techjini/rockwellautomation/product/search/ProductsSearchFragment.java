package com.techjini.rockwellautomation.product.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.base.BaseFragment;
import com.techjini.rockwellautomation.product.browse.ProductGroup;
import com.techjini.rockwellautomation.product.browse.category.Category;
import com.techjini.rockwellautomation.product.browse.subcategory.SubCategory;
import com.techjini.rockwellautomation.product.search.childlist.ProductsSearchResultChildListItem;
import com.techjini.rockwellautomation.product.search.parentlist.ProductsSearchResultParentListItem;
import com.techjini.rockwellautomation.util.AppUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Debu
 */
public class ProductsSearchFragment extends BaseFragment {

  private LinearLayout layoutBrowse;
  private OnBrowseClickListener onBrowseClickListener;
  private EditText editTextSearchByKeyword;
  private EditText editTextSearchByCatalogNumber;
  private LinearLayout layoutNoSearchResult;
  private LinearLayout layoutSearchResult;
  private TextView textViewSearchResultCount;
  private RecyclerView recyclerViewProductsSearchResult;
  private List<ProductsSearchResultParentListItem> listOfProductsSearchResultParentListItems;
  private ProductsSearchResultExpandableAdapter productsSearchResultExpandableAdapter;
  private boolean productsSearchResultFound;

  private TextView.OnEditorActionListener onEditorActionListener =
      new TextView.OnEditorActionListener() {
        @Override public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
          boolean handled = false;

          if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (textView.getId() == R.id.editTextSearchByKeyword) {
              if (editTextSearchByKeyword.getText().toString().trim().length() >= 3) {
                AppUtil.hideKeyboard(baseActivity);
                searchProductsByKeyword(editTextSearchByKeyword.getText().toString());
                handled = true;
              } else {
                Toast.makeText(context, "Minimum 3 characters required to trigger search",
                    Toast.LENGTH_SHORT).show();
              }
            } else if (textView.getId() == R.id.editTextSearchByCatalogNumber) {
              if (editTextSearchByCatalogNumber.getText().toString().trim().length() >= 3) {
                AppUtil.hideKeyboard(baseActivity);
                searchProductsByCatalogNumber(editTextSearchByCatalogNumber.getText().toString());
                handled = true;
              } else {
                Toast.makeText(context, "Minimum 3 characters required to trigger search",
                    Toast.LENGTH_SHORT).show();
              }
            }
          }

          return handled;
        }
      };

  public ProductsSearchFragment() {
    // Required empty public constructor
  }

  public static ProductsSearchFragment newInstance() {
    return new ProductsSearchFragment();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);

    onBrowseClickListener = (OnBrowseClickListener) baseActivity;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_products_search, container, false);

    layoutBrowse = (LinearLayout) view.findViewById(R.id.layoutBrowse);
    editTextSearchByKeyword = (EditText) view.findViewById(R.id.editTextSearchByKeyword);
    editTextSearchByCatalogNumber =
        (EditText) view.findViewById(R.id.editTextSearchByCatalogNumber);
    layoutNoSearchResult = (LinearLayout) view.findViewById(R.id.layoutNoSearchResult);
    layoutSearchResult = (LinearLayout) view.findViewById(R.id.layoutSearchResult);
    textViewSearchResultCount = (TextView) view.findViewById(R.id.textViewSearchResultCount);
    recyclerViewProductsSearchResult =
        (RecyclerView) view.findViewById(R.id.recyclerViewProductsSearchResult);

    editTextSearchByKeyword.setOnEditorActionListener(onEditorActionListener);
    editTextSearchByCatalogNumber.setOnEditorActionListener(onEditorActionListener);

    listOfProductsSearchResultParentListItems = new ArrayList<>();
    recyclerViewProductsSearchResult.setLayoutManager(new LinearLayoutManager(context));
    recyclerViewProductsSearchResult.setHasFixedSize(true);

    toggleProductsSearchResultVisibility();

    layoutBrowse.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        onBrowseClickListener.onBrowseClick();
      }
    });

    return view;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    productsSearchResultExpandableAdapter.onSaveInstanceState(outState);
  }

  private void toggleProductsSearchResultVisibility() {
    if (productsSearchResultFound) {
      layoutSearchResult.setVisibility(View.VISIBLE);
      layoutNoSearchResult.setVisibility(View.GONE);
      String numberOfResultsFound = context.getString(R.string.number_of_results_found,
          String.valueOf(listOfProductsSearchResultParentListItems.size()),
          ((listOfProductsSearchResultParentListItems.size() > 1) ? "s" : ""));
      textViewSearchResultCount.setText(numberOfResultsFound);
    } else {
      layoutNoSearchResult.setVisibility(View.VISIBLE);
      layoutSearchResult.setVisibility(View.GONE);
      textViewSearchResultCount.setText("");
    }
  }

  private void searchProductsByKeyword(String keyword) {
    // TODO: Need to implement; currently it returns a dummy list of products search result list items
    if (productsSearchResultExpandableAdapter != null) {
      productsSearchResultExpandableAdapter.notifyParentItemRangeRemoved(0,
          listOfProductsSearchResultParentListItems.size());
    }

    listOfProductsSearchResultParentListItems.clear();
    List<ProductGroup> listOfProductGroups = AppUtil.getListOfProductGroups();

    for (ProductGroup productGroup : listOfProductGroups) {
      String productGroupName = productGroup.getProductGroupName();

      for (Category category : productGroup.getListOfCategories()) {
        String productCategory = category.getCategory();
        List<ProductsSearchResultChildListItem> listOfProductsSearchResultChildListItems =
            new ArrayList<>();

        for (SubCategory subCategory : category.getListOfSubCategories()) {
          String productSubCategory = subCategory.getSubCategory().toLowerCase();

          if (productSubCategory.contains(keyword.toLowerCase())) {
            ProductsSearchResultChildListItem productsSearchResultChildListItem =
                new ProductsSearchResultChildListItem(subCategory.getSubCategoryId(),
                    subCategory.getSubCategory());
            listOfProductsSearchResultChildListItems.add(productsSearchResultChildListItem);
          } // End of if
        } // End of sub category for loop

        if (listOfProductsSearchResultChildListItems.size() > 0) {
          listOfProductsSearchResultChildListItems.get(
              listOfProductsSearchResultChildListItems.size() - 1)
              .setVerticalDividerDisplayed(true);

          ProductsSearchResultParentListItem productsSearchResultParentListItem =
              new ProductsSearchResultParentListItem(productGroupName, productCategory,
                  listOfProductsSearchResultChildListItems);
          listOfProductsSearchResultParentListItems.add(productsSearchResultParentListItem);
        } // End of if
      } // End of category for loop
    } // End of product group for loop

    if (listOfProductsSearchResultParentListItems.size() > 0) {
      productsSearchResultFound = true;
    } else {
      productsSearchResultFound = false;
    }

    toggleProductsSearchResultVisibility();

    if (productsSearchResultFound) {
      if (productsSearchResultExpandableAdapter == null) {
        productsSearchResultExpandableAdapter = new ProductsSearchResultExpandableAdapter(context,
            listOfProductsSearchResultParentListItems);
        recyclerViewProductsSearchResult.setAdapter(productsSearchResultExpandableAdapter);
      } else {
        productsSearchResultExpandableAdapter.notifyParentItemRangeInserted(0,
            listOfProductsSearchResultParentListItems.size());
      }
    }
  }

  private void searchProductsByCatalogNumber(String catalogNumber) {
    // TODO: Need to implement
    Toast.makeText(context, "Need to implement", Toast.LENGTH_SHORT).show();
  }

  public interface OnBrowseClickListener {
    void onBrowseClick();
  }
}
