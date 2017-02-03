package com.techjini.rockwellautomation.product.browse.rockwellautomation.parentlist;

import com.techjini.rockwellautomation.expandablerecyclerview.model.ParentListItem;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.RockwellAutomationChildListItem;
import java.util.List;

/**
 * Created by Debu
 */
public class RockwellAutomationParentListItem
    implements ParentListItem, Comparable<RockwellAutomationParentListItem> {

  private int productGroupId;
  private String productGroupImageUri;
  private String productGroupName;
  private String productGroupDetails;
  // As we have only one child item, the size of the list would be 1
  private List<RockwellAutomationChildListItem> listOfRockwellAutomationChildListItems;

  public RockwellAutomationParentListItem(int productGroupId, String productGroupImageUri,
      String productGroupName, String productGroupDetails,
      List<RockwellAutomationChildListItem> listOfRockwellAutomationChildListItems) {
    this.productGroupId = productGroupId;
    this.productGroupImageUri = productGroupImageUri;
    this.productGroupName = productGroupName;
    this.productGroupDetails = productGroupDetails;
    this.listOfRockwellAutomationChildListItems = listOfRockwellAutomationChildListItems;
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

  public List<RockwellAutomationChildListItem> getListOfRockwellAutomationChildListItems() {
    return listOfRockwellAutomationChildListItems;
  }

  public void setListOfRockwellAutomationChildListItems(
      List<RockwellAutomationChildListItem> listOfRockwellAutomationChildListItems) {
    this.listOfRockwellAutomationChildListItems = listOfRockwellAutomationChildListItems;
  }

  @Override public List<RockwellAutomationChildListItem> getChildItemList() {
    return listOfRockwellAutomationChildListItems;
  }

  @Override public boolean isInitiallyExpanded() {
    return false;
  }

  @Override
  public int compareTo(RockwellAutomationParentListItem rockwellAutomationParentListItem) {
    return (productGroupName.compareTo(rockwellAutomationParentListItem.getProductGroupName()));
  }
}
