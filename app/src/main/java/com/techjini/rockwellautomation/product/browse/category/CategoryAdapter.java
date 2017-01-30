package com.techjini.rockwellautomation.product.browse.category;

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
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

  private static final String TAG = CategoryAdapter.class.getSimpleName();
  private Context context;
  private List<Category> listOfCategories;

  public CategoryAdapter(Context context, List<Category> listOfCategories) {
    this.context = context;
    this.listOfCategories = listOfCategories;
  }

  @Override public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.category_list_item, parent, false);
    return new CategoryViewHolder(view);
  }

  @Override public void onBindViewHolder(CategoryViewHolder categoryViewHolder, int position) {
    bindCategoryViewHolder(categoryViewHolder, position);
  }

  private void bindCategoryViewHolder(final CategoryViewHolder categoryViewHolder, int position) {
    if (listOfCategories.get(position).getCategoryState().isSelected()) {
      categoryViewHolder.layoutCategoryListItem.setBackgroundResource(
          R.drawable.inner_list_item_selected_shape);
    } else {
      categoryViewHolder.layoutCategoryListItem.setBackgroundColor(
          ContextCompat.getColor(context, R.color.innerListItemBackgroundColorNormal));
    }

    categoryViewHolder.textViewCategory.setText(listOfCategories.get(position).getCategory());
  }

  @Override public int getItemCount() {
    return listOfCategories.size();
  }

  public void updateListOfCategories(List<Category> listOfCategories) {
    this.listOfCategories.clear();
    this.listOfCategories.addAll(listOfCategories);
  }

  public List<Category> getListOfCategories() {
    return listOfCategories;
  }

  public Category getCategory(int position) {
    return listOfCategories.get(position);
  }

  class CategoryViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout layoutCategoryListItem;
    private TextView textViewCategory;

    public CategoryViewHolder(View itemView) {
      super(itemView);

      layoutCategoryListItem = (LinearLayout) itemView.findViewById(R.id.layoutCategoryListItem);
      textViewCategory = (TextView) itemView.findViewById(R.id.textViewCategory);
    }
  }
}
