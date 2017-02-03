package com.techjini.rockwellautomation.product.search.childlist;

/**
 * Created by Debu
 */
public class ProductsSearchResultChildListItem {

  private int subCategoryId;
  private String subCategory;
  private boolean isVerticalDividerDisplayed;

  public ProductsSearchResultChildListItem(int subCategoryId, String subCategory) {
    this.subCategoryId = subCategoryId;
    this.subCategory = subCategory;
  }

  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }

  public int getSubCategoryId() {
    return subCategoryId;
  }

  public void setSubCategoryId(int subCategoryId) {
    this.subCategoryId = subCategoryId;
  }

  public boolean isVerticalDividerDisplayed() {
    return isVerticalDividerDisplayed;
  }

  public void setVerticalDividerDisplayed(boolean verticalDividerDisplayed) {
    isVerticalDividerDisplayed = verticalDividerDisplayed;
  }
}
