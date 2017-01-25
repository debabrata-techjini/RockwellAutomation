package com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.category;

import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.subcategory.SubCategory;
import java.util.List;

/**
 * Created by Debu
 */
public class Category {

  private int categoryId;
  private String category;
  private List<SubCategory> listOfSubCategories;
  private CategoryState categoryState;

  public Category(int categoryId, String category, List<SubCategory> listOfSubCategories) {
    this.categoryId = categoryId;
    this.category = category;
    this.listOfSubCategories = listOfSubCategories;
    categoryState = new CategoryState();
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public List<SubCategory> getListOfSubCategories() {
    return listOfSubCategories;
  }

  public void setListOfSubCategories(List<SubCategory> listOfSubCategories) {
    this.listOfSubCategories = listOfSubCategories;
  }

  public CategoryState getCategoryState() {
    return categoryState;
  }
}
