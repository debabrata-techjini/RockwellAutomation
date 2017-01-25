package com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.subcategory;

/**
 * Created by Debu
 */
public class SubCategoryState {

  private boolean isSelected;
  private boolean isDividerWithNextImageVisible;
  private boolean isConfigureVisible;

  public SubCategoryState() {
    initialize();
  }

  public void initialize() {
    isSelected = false;
    isDividerWithNextImageVisible = false;
    isConfigureVisible = false;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;

    if (isSelected) {
      isDividerWithNextImageVisible = true;
      isConfigureVisible = true;
    } else {
      isDividerWithNextImageVisible = false;
      isConfigureVisible = false;
    }
  }

  public boolean isDividerWithNextImageVisible() {
    return isDividerWithNextImageVisible;
  }

  public boolean isConfigureVisible() {
    return isConfigureVisible;
  }
}
