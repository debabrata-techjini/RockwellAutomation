package com.techjini.rockwellautomation.product.browse.encompasspartners.parentlist;

import com.techjini.rockwellautomation.expandablerecyclerview.model.ParentListItem;
import com.techjini.rockwellautomation.product.browse.encompasspartners.childlist.EncompassPartnersChildListItem;
import java.util.List;

/**
 * Created by Debu
 */
public class EncompassPartnersParentListItem
    implements ParentListItem, Comparable<EncompassPartnersParentListItem> {

  private int productGroupId;
  private String productGroupImageUri;
  private String productGroupName;
  private String productGroupDetails;
  // As we have only one child item, the size of the list would be 1
  private List<EncompassPartnersChildListItem> listOfEncompassPartnersChildListItem;

  public EncompassPartnersParentListItem(int productGroupId, String productGroupImageUri,
      String productGroupName, String productGroupDetails,
      List<EncompassPartnersChildListItem> listOfEncompassPartnersChildListItem) {
    this.productGroupId = productGroupId;
    this.productGroupImageUri = productGroupImageUri;
    this.productGroupName = productGroupName;
    this.productGroupDetails = productGroupDetails;
    this.listOfEncompassPartnersChildListItem = listOfEncompassPartnersChildListItem;
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

  public List<EncompassPartnersChildListItem> getListOfEncompassPartnersChildListItem() {
    return listOfEncompassPartnersChildListItem;
  }

  public void setListOfEncompassPartnersChildListItem(
      List<EncompassPartnersChildListItem> listOfEncompassPartnersChildListItem) {
    this.listOfEncompassPartnersChildListItem = listOfEncompassPartnersChildListItem;
  }

  @Override public List<?> getChildItemList() {
    return listOfEncompassPartnersChildListItem;
  }

  @Override public boolean isInitiallyExpanded() {
    return false;
  }

  @Override public int compareTo(EncompassPartnersParentListItem EncompassPartnersParentListItem) {
    return (getProductGroupName().compareTo(EncompassPartnersParentListItem.getProductGroupName()));
  }
}
