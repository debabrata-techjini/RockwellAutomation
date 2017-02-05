package com.techjini.rockwellautomation.product.browse.category;

/**
 * Created by Debu
 */
public class CategoryState {

  private boolean isSelected;
  private boolean isNextImageVisible;
  private boolean isProductGroupDescriptionVisible;
  private boolean isSubCategoryVisible;
  private boolean isDividerWithNextImageVisible;
  private boolean isConfigureVisible;

  public CategoryState() {
    initialize();
  }

  private void initialize() {
    isSelected = false;
    isNextImageVisible = false;
    isProductGroupDescriptionVisible = true;
    isSubCategoryVisible = false;
    isDividerWithNextImageVisible = false;
    isConfigureVisible = false;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;

    if (isSelected) {
      isNextImageVisible = true;
      isProductGroupDescriptionVisible = false;
      isSubCategoryVisible = true;
      isDividerWithNextImageVisible = false;
      isConfigureVisible = false;
    } else {
      isNextImageVisible = false;
      isProductGroupDescriptionVisible = true;
      isSubCategoryVisible = false;
      isDividerWithNextImageVisible = false;
      isConfigureVisible = false;
    }
  }

  public boolean isNextImageVisible() {
    return isNextImageVisible;
  }

  public boolean isProductGroupDescriptionVisible() {
    return isProductGroupDescriptionVisible;
  }

  public boolean isSubCategoryVisible() {
    return isSubCategoryVisible;
  }

  public boolean isDividerWithNextImageVisible() {
    return isDividerWithNextImageVisible;
  }

  public boolean isConfigureVisible() {
    return isConfigureVisible;
  }
}
