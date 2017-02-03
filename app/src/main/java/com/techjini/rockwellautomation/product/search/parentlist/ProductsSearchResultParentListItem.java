package com.techjini.rockwellautomation.product.search.parentlist;

import com.techjini.rockwellautomation.expandablerecyclerview.model.ParentListItem;
import com.techjini.rockwellautomation.product.search.childlist.ProductsSearchResultChildListItem;
import java.util.List;

/**
 * Created by Debu
 */
public class ProductsSearchResultParentListItem
    implements ParentListItem, Comparable<ProductsSearchResultParentListItem> {

  private String productGroupName;
  private String category;
  private List<ProductsSearchResultChildListItem> listOfProductsSearchResultChildListItems;

  public ProductsSearchResultParentListItem(String productGroupName, String category,
      List<ProductsSearchResultChildListItem> listOfProductsSearchResultChildListItems) {
    this.productGroupName = productGroupName;
    this.category = category;
    this.listOfProductsSearchResultChildListItems = listOfProductsSearchResultChildListItems;
  }

  public String getProductGroupName() {
    return productGroupName;
  }

  public void setProductGroupName(String productGroupName) {
    this.productGroupName = productGroupName;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public List<ProductsSearchResultChildListItem> getListOfProductsSearchResultChildListItems() {
    return listOfProductsSearchResultChildListItems;
  }

  public void setListOfProductsSearchResultChildListItems(
      List<ProductsSearchResultChildListItem> listOfProductsSearchResultChildListItems) {
    this.listOfProductsSearchResultChildListItems = listOfProductsSearchResultChildListItems;
  }

  @Override public List<ProductsSearchResultChildListItem> getChildItemList() {
    return listOfProductsSearchResultChildListItems;
  }

  @Override public boolean isInitiallyExpanded() {
    return true;
  }

  @Override
  public int compareTo(ProductsSearchResultParentListItem productsSearchResultParentListItem) {
    return (productGroupName.compareTo(productsSearchResultParentListItem.getProductGroupName()));
  }
}
