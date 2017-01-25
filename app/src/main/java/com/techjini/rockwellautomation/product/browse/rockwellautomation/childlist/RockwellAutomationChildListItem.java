package com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist;

import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.category.Category;
import java.util.List;

/**
 * Created by Debu
 */
public class RockwellAutomationChildListItem {

  private List<Category> listOfCategories;
  private String productGroupDescription;

  public RockwellAutomationChildListItem(List<Category> listOfCategories,
      String productGroupDescription) {
    this.listOfCategories = listOfCategories;
    this.productGroupDescription = productGroupDescription;
  }

  public List<Category> getListOfCategories() {
    return listOfCategories;
  }

  public void setListOfCategories(List<Category> listOfCategories) {
    this.listOfCategories = listOfCategories;
  }

  public String getProductGroupDescription() {
    return productGroupDescription;
  }

  public void setProductGroupDescription(String productGroupDescription) {
    this.productGroupDescription = productGroupDescription;
  }
}
