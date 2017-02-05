package com.techjini.rockwellautomation.product.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.expandablerecyclerview.adapter.ExpandableRecyclerAdapter;
import com.techjini.rockwellautomation.expandablerecyclerview.model.ParentListItem;
import com.techjini.rockwellautomation.expandablerecyclerview.viewHolder.ChildViewHolder;
import com.techjini.rockwellautomation.expandablerecyclerview.viewHolder.ParentViewHolder;
import com.techjini.rockwellautomation.product.search.childlist.ProductsSearchResultChildListItem;
import com.techjini.rockwellautomation.product.search.parentlist.ProductsSearchResultParentListItem;
import java.util.List;

/**
 * Created by Debu
 */
public class ProductsSearchResultExpandableAdapter extends
    ExpandableRecyclerAdapter<ProductsSearchResultExpandableAdapter.ProductsSearchResultParentViewHolder, ProductsSearchResultExpandableAdapter.ProductsSearchResultChildViewHolder> {

  private Context context;
  private List<ProductsSearchResultParentListItem> listOfProductsSearchResultParentListItems;
  private LayoutInflater layoutInflater;

  public ProductsSearchResultExpandableAdapter(Context context,
      @NonNull List<ProductsSearchResultParentListItem> listOfProductsSearchResultParentListItems) {
    super(listOfProductsSearchResultParentListItems);

    this.context = context;
    this.listOfProductsSearchResultParentListItems = listOfProductsSearchResultParentListItems;
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public ProductsSearchResultParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
    View view =
        layoutInflater.inflate(R.layout.products_search_result_parent_list_item, parentViewGroup,
            false);
    return new ProductsSearchResultParentViewHolder(view);
  }

  @Override
  public ProductsSearchResultChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
    View view =
        layoutInflater.inflate(R.layout.products_search_result_child_list_item, childViewGroup,
            false);
    return new ProductsSearchResultChildViewHolder(view);
  }

  @Override public void onBindParentViewHolder(
      ProductsSearchResultParentViewHolder productsSearchResultParentViewHolder, int position,
      ParentListItem parentListItem) {
    ProductsSearchResultParentListItem productsSearchResultParentListItem =
        (ProductsSearchResultParentListItem) parentListItem;

    bindProductsSearchResultParentViewHolder(productsSearchResultParentViewHolder,
        productsSearchResultParentListItem);
  }

  @Override public void onBindChildViewHolder(
      ProductsSearchResultChildViewHolder productsSearchResultChildViewHolder, int position,
      Object childListItem) {
    ProductsSearchResultChildListItem productsSearchResultChildListItem =
        (ProductsSearchResultChildListItem) childListItem;

    bindProductsSearchResultChildViewHolder(productsSearchResultChildViewHolder,
        productsSearchResultChildListItem);
  }

  private void bindProductsSearchResultParentViewHolder(
      final ProductsSearchResultParentViewHolder productsSearchResultParentViewHolder,
      ProductsSearchResultParentListItem productsSearchResultParentListItem) {
    String searchedProductGroupAndCategory =
        productsSearchResultParentListItem.getProductGroupName()
            + " > "
            + productsSearchResultParentListItem.getCategory();
    productsSearchResultParentViewHolder.textViewProductGroupAndCategory.setText(
        searchedProductGroupAndCategory);
  }

  private void bindProductsSearchResultChildViewHolder(
      final ProductsSearchResultChildViewHolder productsSearchResultChildViewHolder,
      ProductsSearchResultChildListItem productsSearchResultChildListItem) {
    productsSearchResultChildViewHolder.textViewSubCategory.setText(
        productsSearchResultChildListItem.getSubCategory());

    if (productsSearchResultChildListItem.isVerticalDividerDisplayed()) {
      productsSearchResultChildViewHolder.layoutVerticalDivider.setVisibility(View.VISIBLE);
    } else {
      productsSearchResultChildViewHolder.layoutVerticalDivider.setVisibility(View.GONE);
    }
  }

  class ProductsSearchResultParentViewHolder extends ParentViewHolder {

    private TextView textViewProductGroupAndCategory;

    ProductsSearchResultParentViewHolder(View itemView) {
      super(itemView);

      textViewProductGroupAndCategory =
          (TextView) itemView.findViewById(R.id.textViewProductGroupAndCategory);
    }

    @Override public boolean shouldItemViewClickToggleExpansion() {
      return false;
    }
  }

  class ProductsSearchResultChildViewHolder extends ChildViewHolder {

    private TextView textViewSubCategory;
    private LinearLayout layoutVerticalDivider;

    ProductsSearchResultChildViewHolder(View itemView) {
      super(itemView);

      textViewSubCategory = (TextView) itemView.findViewById(R.id.textViewSubCategory);
      layoutVerticalDivider = (LinearLayout) itemView.findViewById(R.id.layoutVerticalDivider);
    }
  }
}
