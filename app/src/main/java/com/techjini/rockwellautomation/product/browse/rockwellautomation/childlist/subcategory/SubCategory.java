package com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.subcategory;

/**
 * Created by Debu
 */
public class SubCategory {

  private int subCategoryId;
  private String subCategory;
  private String productImageUri;
  private SubCategoryState subCategoryState;

  public SubCategory(int subCategoryId, String subCategory, String productImageUri) {
    this.subCategoryId = subCategoryId;
    this.subCategory = subCategory;
    this.productImageUri = productImageUri;
    subCategoryState = new SubCategoryState();
  }

  public int getSubCategoryId() {
    return subCategoryId;
  }

  public void setSubCategoryId(int subCategoryId) {
    this.subCategoryId = subCategoryId;
  }

  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }

  public String getProductImageUri() {
    return productImageUri;
  }

  public void setProductImageUri(String productImageUri) {
    this.productImageUri = productImageUri;
  }

  public SubCategoryState getSubCategoryState() {
    return subCategoryState;
  }
}
