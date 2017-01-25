package com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.subcategory;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.techjini.rockwellautomation.R;
import java.util.List;

/**
 * Created by Debu
 */
public class SubCategoryAdapter
    extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {

  private static final String TAG = SubCategoryAdapter.class.getSimpleName();
  private Context context;
  private List<SubCategory> listOfSubCategories;

  public SubCategoryAdapter(Context context, List<SubCategory> listOfSubCategories) {
    this.context = context;
    this.listOfSubCategories = listOfSubCategories;
  }

  @Override public SubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.sub_category_list_item, parent, false);
    return new SubCategoryAdapter.SubCategoryViewHolder(view);
  }

  @Override
  public void onBindViewHolder(SubCategoryAdapter.SubCategoryViewHolder subCategoryViewHolder,
      int position) {
    bindSubCategoryViewHolder(subCategoryViewHolder, position);
  }

  private void bindSubCategoryViewHolder(final SubCategoryViewHolder subCategoryViewHolder,
      int position) {
    if (listOfSubCategories.get(position).getSubCategoryState().isSelected()) {
      subCategoryViewHolder.layoutSubCategoryListItem.setBackgroundResource(
          R.drawable.inner_list_item_selected_shape);
    } else {
      subCategoryViewHolder.layoutSubCategoryListItem.setBackgroundColor(
          ContextCompat.getColor(context, R.color.innerListItemBackgroundColorNormal));
    }

    subCategoryViewHolder.textViewSubCategory.setText(
        listOfSubCategories.get(position).getSubCategory());
  }

  @Override public int getItemCount() {
    return listOfSubCategories.size();
  }

  public void updateListOfSubCategories(List<SubCategory> listOfSubCategories) {
    this.listOfSubCategories.clear();
    this.listOfSubCategories.addAll(listOfSubCategories);
  }

  public List<SubCategory> getListOfSubCategories() {
    return listOfSubCategories;
  }

  public SubCategory getSubCategory(int position) {
    return listOfSubCategories.get(position);
  }

  class SubCategoryViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout layoutSubCategoryListItem;
    private TextView textViewSubCategory;

    public SubCategoryViewHolder(View itemView) {
      super(itemView);

      layoutSubCategoryListItem =
          (LinearLayout) itemView.findViewById(R.id.layoutSubCategoryListItem);
      textViewSubCategory = (TextView) itemView.findViewById(R.id.textViewSubCategory);
    }
  }
}
