package com.techjini.rockwellautomation.product.browse.rockwellautomation;

import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.category.Category;
import java.util.List;

/**
 * Created by Debu
 */
public class ProductGroup {

  private int productGroupId;
  private String productGroupName;
  private String productGroupDetails;
  private String productGroupDescription;
  private String productGroupImageUri;
  private List<Category> listOfCategories;

  public ProductGroup(int productGroupId, String productGroupName, String productGroupDetails,
      String productGroupDescription, String productGroupImageUri,
      List<Category> listOfCategories) {
    this.productGroupId = productGroupId;
    this.productGroupName = productGroupName;
    this.productGroupDetails = productGroupDetails;
    this.productGroupDescription = productGroupDescription;
    this.productGroupImageUri = productGroupImageUri;
    this.listOfCategories = listOfCategories;
  }

  public int getProductGroupId() {
    return productGroupId;
  }

  public void setProductGroupId(int productGroupId) {
    this.productGroupId = productGroupId;
  }

  public String getProductGroupName() {
    return productGroupName;
  }

  public void setProductGroupName(String productGroupName) {
    this.productGroupName = productGroupName;
  }

  public String getProductGroupDetails() {
    return productGroupDetails;
  }

  public void setProductGroupDetails(String productGroupDetails) {
    this.productGroupDetails = productGroupDetails;
  }

  public String getProductGroupDescription() {
    return productGroupDescription;
  }

  public void setProductGroupDescription(String productGroupDescription) {
    this.productGroupDescription = productGroupDescription;
  }

  public String getProductGroupImageUri() {
    return productGroupImageUri;
  }

  public void setProductGroupImageUri(String productGroupImageUri) {
    this.productGroupImageUri = productGroupImageUri;
  }

  public List<Category> getListOfCategories() {
    return listOfCategories;
  }

  public void setListOfCategories(List<Category> listOfCategories) {
    this.listOfCategories = listOfCategories;
  }
}
