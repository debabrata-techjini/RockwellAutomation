package com.techjini.rockwellautomation.product.browse.rockwellautomation.parentlist;

import com.techjini.rockwellautomation.expandablerecyclerview.model.ParentListItem;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.RockwellAutomationChildListItem;
import java.util.List;

/**
 * Created by Debu
 */
public class RockwellAutomationParentListItem implements ParentListItem {

  private int productGroupId;
  private String productGroupImageUri;
  private String productGroupName;
  private String productGroupDetails;
  // As we have only one child item, the size of the list would be 1
  private List<RockwellAutomationChildListItem> listOfRockwellAutomationChildListItem;

  public RockwellAutomationParentListItem(int productGroupId, String productGroupImageUri,
      String productGroupName, String productGroupDetails,
      List<RockwellAutomationChildListItem> listOfRockwellAutomationChildListItem) {
    this.productGroupId = productGroupId;
    this.productGroupImageUri = productGroupImageUri;
    this.productGroupName = productGroupName;
    this.productGroupDetails = productGroupDetails;
    this.listOfRockwellAutomationChildListItem = listOfRockwellAutomationChildListItem;
  }

  public int getProductGroupId() {
    return productGroupId;
  }

  public void setProductGroupId(int productGroupId) {
    this.productGroupId = productGroupId;
  }

  public String getProductGroupImageUri() {
    return productGroupImageUri;
  }

  public void setProductGroupImageUri(String productGroupImageUri) {
    this.productGroupImageUri = productGroupImageUri;
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

  public List<RockwellAutomationChildListItem> getListOfRockwellAutomationChildListItem() {
    return listOfRockwellAutomationChildListItem;
  }

  public void setListOfRockwellAutomationChildListItem(
      List<RockwellAutomationChildListItem> listOfRockwellAutomationChildListItem) {
    this.listOfRockwellAutomationChildListItem = listOfRockwellAutomationChildListItem;
  }

  @Override public List<?> getChildItemList() {
    return listOfRockwellAutomationChildListItem;
  }

  @Override public boolean isInitiallyExpanded() {
    return false;
  }
}
