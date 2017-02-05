package com.techjini.rockwellautomation.product.browse.encompasspartners;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.techjini.rockwellautomation.R;
import com.techjini.rockwellautomation.expandablerecyclerview.adapter.ExpandableRecyclerAdapter;
import com.techjini.rockwellautomation.expandablerecyclerview.model.ParentListItem;
import com.techjini.rockwellautomation.expandablerecyclerview.viewHolder.ChildViewHolder;
import com.techjini.rockwellautomation.expandablerecyclerview.viewHolder.ParentViewHolder;
import com.techjini.rockwellautomation.product.browse.category.Category;
import com.techjini.rockwellautomation.product.browse.category.CategoryAdapter;
import com.techjini.rockwellautomation.product.browse.category.CategoryState;
import com.techjini.rockwellautomation.product.browse.encompasspartners.childlist.EncompassPartnersChildListItem;
import com.techjini.rockwellautomation.product.browse.encompasspartners.parentlist.EncompassPartnersParentListItem;
import com.techjini.rockwellautomation.product.browse.subcategory.SubCategory;
import com.techjini.rockwellautomation.product.browse.subcategory.SubCategoryAdapter;
import com.techjini.rockwellautomation.product.browse.subcategory.SubCategoryState;
import com.techjini.rockwellautomation.util.RecyclerViewItemTouchListener;
import java.util.ArrayList;
import java.util.List;

import static com.techjini.rockwellautomation.util.Constants.DEFAULT_IMAGE_RESOURCE;

/**
 * Created by Debu
 */
public class EncompassPartnersExpandableAdapter extends
    ExpandableRecyclerAdapter<EncompassPartnersExpandableAdapter.EncompassPartnersParentViewHolder, EncompassPartnersExpandableAdapter.EncompassPartnersChildViewHolder> {

  private static final String TAG = EncompassPartnersExpandableAdapter.class.getSimpleName();
  private Context context;
  private EncompassPartnersFragment encompassPartnersFragment;
  private List<EncompassPartnersParentListItem> listOfEncompassPartnersParentListItems;
  private LayoutInflater layoutInflater;

  public EncompassPartnersExpandableAdapter(Context context,
      EncompassPartnersFragment encompassPartnersFragment,
      @NonNull List<EncompassPartnersParentListItem> listOfEncompassPartnersParentListItems) {
    super(listOfEncompassPartnersParentListItems);

    this.context = context;
    this.encompassPartnersFragment = encompassPartnersFragment;
    this.listOfEncompassPartnersParentListItems = listOfEncompassPartnersParentListItems;
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public EncompassPartnersParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
    View view =
        layoutInflater.inflate(R.layout.encompass_partners_parent_list_item, parentViewGroup,
            false);
    return new EncompassPartnersParentViewHolder(view);
  }

  @Override
  public EncompassPartnersChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
    View view =
        layoutInflater.inflate(R.layout.encompass_partners_child_list_item, childViewGroup, false);
    return new EncompassPartnersChildViewHolder(view);
  }

  @Override public void onBindParentViewHolder(
      final EncompassPartnersParentViewHolder encompassPartnersParentViewHolder, final int position,
      ParentListItem parentListItem) {
    EncompassPartnersParentListItem encompassPartnersParentListItem =
        (EncompassPartnersParentListItem) parentListItem;

    bindEncompassPartnersParentViewHolder(encompassPartnersParentViewHolder,
        encompassPartnersParentListItem);
  }

  @Override public void onBindChildViewHolder(
      EncompassPartnersChildViewHolder encompassPartnersChildViewHolder, int position,
      Object childListItem) {
    EncompassPartnersChildListItem encompassPartnersChildListItem =
        (EncompassPartnersChildListItem) childListItem;

    bindEncompassPartnersChildViewHolder(encompassPartnersChildViewHolder,
        encompassPartnersChildListItem);
  }

  @Override public void expandAllParents() {
    notifyParentItemRangeChanged(0, listOfEncompassPartnersParentListItems.size());
    super.expandAllParents();
  }

  @Override public void collapseAllParents() {
    notifyParentItemRangeChanged(0, listOfEncompassPartnersParentListItems.size());
    super.collapseAllParents();
  }

  private void bindEncompassPartnersParentViewHolder(
      final EncompassPartnersParentViewHolder encompassPartnersParentViewHolder,
      EncompassPartnersParentListItem encompassPartnersParentListItem) {
    if (encompassPartnersParentListItem.getProductGroupImageUri() != null) {
      // TODO: Need to use Picasso lib to load image Uri to the image view
    } else {
      encompassPartnersParentViewHolder.imageViewProductGroup.setImageResource(
          DEFAULT_IMAGE_RESOURCE);
    }

    encompassPartnersParentViewHolder.textViewProductGroupName.setText(
        encompassPartnersParentListItem.getProductGroupName());
    encompassPartnersParentViewHolder.textViewProductGroupDetails.setText(
        encompassPartnersParentListItem.getProductGroupDetails());
  }

  private void bindEncompassPartnersChildViewHolder(
      final EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      final EncompassPartnersChildListItem encompassPartnersChildListItem) {
    List<Category> listOfCategories = encompassPartnersChildListItem.getListOfCategories();
    List<SubCategory> listOfSubCategories = new ArrayList<>();
    final CategoryAdapter categoryAdapter = new CategoryAdapter(context, listOfCategories);
    final SubCategoryAdapter subCategoryAdapter =
        new SubCategoryAdapter(context, listOfSubCategories);
    int selectedCategoryPosition = -1;
    int selectedSubCategoryPosition = -1;
    final LinearLayoutManager linearLayoutManagerOfCategory;
    final LinearLayoutManager linearLayoutManagerOfSubCategory;

    for (int i = 0; i < listOfCategories.size(); i++) {
      Category category = listOfCategories.get(i);

      if (category.getCategoryState().isSelected()) {
        selectedCategoryPosition = i;
        handleViewVisibilityForSelectedCategory(encompassPartnersChildViewHolder,
            category.getCategoryState());
        // When a selected category is found, we need to show list of sub categories, corresponding
        // to the category
        listOfSubCategories = category.getListOfSubCategories();
        subCategoryAdapter.updateListOfSubCategories(listOfSubCategories);
        subCategoryAdapter.notifyItemRangeChanged(0, listOfSubCategories.size());
        // Only one of the categories can be selected; so once a selected category is found, break
        // the "for" loop.
        break;
      }
    }

    for (int i = 0; i < listOfSubCategories.size(); i++) {
      SubCategory subCategory = listOfSubCategories.get(i);

      if (subCategory.getSubCategoryState().isSelected()) {
        selectedSubCategoryPosition = i;
        getProductImage(encompassPartnersChildViewHolder, subCategory.getProductImageUri());
        handleViewVisibilityForSelectedSubCategory(encompassPartnersChildViewHolder,
            subCategory.getSubCategoryState());
        // Only one of the Categories can be selected; so once a selected category is found, break
        // the "for" loop.
        break;
      }
    }

    encompassPartnersChildViewHolder.recyclerViewCategory.setAdapter(categoryAdapter);
    linearLayoutManagerOfCategory = new LinearLayoutManager(context);
    encompassPartnersChildViewHolder.recyclerViewCategory.setLayoutManager(
        linearLayoutManagerOfCategory);
    encompassPartnersChildViewHolder.recyclerViewCategory.setHasFixedSize(true);
    encompassPartnersChildViewHolder.textViewProductGroupDescription.setText(
        encompassPartnersChildListItem.getProductGroupDescription());

    if (selectedCategoryPosition != -1) {
      linearLayoutManagerOfCategory.scrollToPositionWithOffset(selectedCategoryPosition, 0);
    }

    encompassPartnersChildViewHolder.recyclerViewSubCategory.setAdapter(subCategoryAdapter);
    linearLayoutManagerOfSubCategory = new LinearLayoutManager(context);
    encompassPartnersChildViewHolder.recyclerViewSubCategory.setLayoutManager(
        linearLayoutManagerOfSubCategory);
    encompassPartnersChildViewHolder.recyclerViewSubCategory.setHasFixedSize(true);

    if (selectedSubCategoryPosition != -1) {
      linearLayoutManagerOfSubCategory.scrollToPositionWithOffset(selectedSubCategoryPosition, 0);
    }

    encompassPartnersChildViewHolder.recyclerViewCategory.addOnItemTouchListener(
        new RecyclerViewItemTouchListener(context,
            encompassPartnersChildViewHolder.recyclerViewCategory,
            new RecyclerViewItemTouchListener.OnRecyclerViewItemClickListener() {
              @Override public void onClick(View view, int selectedCategoryPosition) {
                Log.d(TAG, "onClick() -> selected category position: " + selectedCategoryPosition);
                handleCategorySelection(encompassPartnersChildViewHolder, categoryAdapter,
                    subCategoryAdapter, selectedCategoryPosition);
                linearLayoutManagerOfCategory.scrollToPositionWithOffset(selectedCategoryPosition,
                    0);
              }
            }) {
          @Override
          public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            //Log.d(TAG, "onInterceptTouchEvent()");
            super.onInterceptTouchEvent(recyclerView, motionEvent);
            disallowProductGroupListToInterceptTouchEvent();
            return false;
          }
        });

    encompassPartnersChildViewHolder.recyclerViewSubCategory.addOnItemTouchListener(
        new RecyclerViewItemTouchListener(context,
            encompassPartnersChildViewHolder.recyclerViewSubCategory,
            new RecyclerViewItemTouchListener.OnRecyclerViewItemClickListener() {
              @Override public void onClick(View view, int selectedSubCategoryPosition) {
                Log.d(TAG,
                    "onClick() -> selected sub category position: " + selectedSubCategoryPosition);
                handleSubCategorySelection(encompassPartnersChildViewHolder, subCategoryAdapter,
                    selectedSubCategoryPosition);
                linearLayoutManagerOfSubCategory.scrollToPositionWithOffset(
                    selectedSubCategoryPosition, 0);
              }
            }) {
          @Override
          public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            //Log.d(TAG, "onInterceptTouchEvent()");
            super.onInterceptTouchEvent(recyclerView, motionEvent);
            disallowProductGroupListToInterceptTouchEvent();
            return false;
          }
        });
  }

  private void handleCategorySelection(
      EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      CategoryAdapter categoryAdapter, SubCategoryAdapter subCategoryAdapter,
      int selectedCategoryPosition) {
    List<Category> listOfCategories = categoryAdapter.getListOfCategories();
    setAllCategoryState(listOfCategories, selectedCategoryPosition);
    Category selectedCategory = categoryAdapter.getCategory(selectedCategoryPosition);
    CategoryState selectedCategoryState = selectedCategory.getCategoryState();
    List<SubCategory> listOfSubCategories = selectedCategory.getListOfSubCategories();
    initializeSubCategoryState(listOfSubCategories);
    subCategoryAdapter.updateListOfSubCategories(listOfSubCategories);

    handleViewVisibilityForSelectedCategory(encompassPartnersChildViewHolder,
        selectedCategoryState);

    categoryAdapter.notifyItemRangeChanged(0, listOfCategories.size());
    subCategoryAdapter.notifyItemRangeChanged(0, listOfSubCategories.size());
  }

  private void handleSubCategorySelection(
      EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      SubCategoryAdapter subCategoryAdapter, int selectedSubCategoryPosition) {
    List<SubCategory> listOfSubCategories = subCategoryAdapter.getListOfSubCategories();

    setAllSubCategoryState(listOfSubCategories, selectedSubCategoryPosition);
    SubCategory selectedSubCategory =
        subCategoryAdapter.getSubCategory(selectedSubCategoryPosition);
    SubCategoryState selectedSubCategoryState = selectedSubCategory.getSubCategoryState();
    String productImageUri = selectedSubCategory.getProductImageUri();
    getProductImage(encompassPartnersChildViewHolder, productImageUri);

    handleViewVisibilityForSelectedSubCategory(encompassPartnersChildViewHolder,
        selectedSubCategoryState);

    subCategoryAdapter.notifyItemRangeChanged(0, listOfSubCategories.size());
  }

  private void handleViewVisibilityForSelectedCategory(
      EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      CategoryState selectedCategoryState) {
    handleNextImageVisibility(encompassPartnersChildViewHolder,
        selectedCategoryState.isNextImageVisible());
    handleProductGroupDescriptionLayoutVisibility(encompassPartnersChildViewHolder,
        selectedCategoryState.isProductGroupDescriptionVisible());
    handleSubCategoryLayoutVisibility(encompassPartnersChildViewHolder,
        selectedCategoryState.isSubCategoryVisible());
    handleDividerWithNextImageLayoutVisibility(encompassPartnersChildViewHolder,
        selectedCategoryState.isDividerWithNextImageVisible());
    handleConfigureLayoutVisibility(encompassPartnersChildViewHolder,
        selectedCategoryState.isConfigureVisible());
  }

  private void handleViewVisibilityForSelectedSubCategory(
      EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      SubCategoryState selectedSubCategoryState) {
    handleDividerWithNextImageLayoutVisibility(encompassPartnersChildViewHolder,
        selectedSubCategoryState.isDividerWithNextImageVisible());
    handleConfigureLayoutVisibility(encompassPartnersChildViewHolder,
        selectedSubCategoryState.isConfigureVisible());
  }

  private void getProductImage(EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      String productImageUri) {
    if (productImageUri != null) {
      // TODO: Need to use Picasso lib to load image Uri to the image view
    } else {
      encompassPartnersChildViewHolder.imageViewProduct.setImageResource(DEFAULT_IMAGE_RESOURCE);
    }
  }

  private void setAllCategoryState(List<Category> listOfCategories, int selectedCategoryPosition) {
    for (int i = 0; i < listOfCategories.size(); i++) {
      if (i == selectedCategoryPosition) {
        Category selectedCategory = listOfCategories.get(i);
        selectedCategory.getCategoryState().setSelected(true);
      } else {
        Category unselectedCategory = listOfCategories.get(i);
        unselectedCategory.getCategoryState().setSelected(false);
      }
    }
  }

  private void setAllSubCategoryState(List<SubCategory> listOfSubCategories,
      int selectedSubCategoryPosition) {
    for (int i = 0; i < listOfSubCategories.size(); i++) {
      if (i == selectedSubCategoryPosition) {
        SubCategory selectedSubCategory = listOfSubCategories.get(i);
        selectedSubCategory.getSubCategoryState().setSelected(true);
      } else {
        SubCategory unselectedSubCategory = listOfSubCategories.get(i);
        unselectedSubCategory.getSubCategoryState().setSelected(false);
      }
    }
  }

  private void initializeSubCategoryState(List<SubCategory> listOfSubCategories) {
    for (SubCategory subCategory : listOfSubCategories) {
      subCategory.getSubCategoryState().initialize();
    }
  }

  private void handleNextImageVisibility(
      EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      boolean shouldShowNextImage) {
    if (shouldShowNextImage) {
      encompassPartnersChildViewHolder.imageViewNext1.setVisibility(View.VISIBLE);
    } else {
      encompassPartnersChildViewHolder.imageViewNext1.setVisibility(View.GONE);
    }
  }

  private void handleProductGroupDescriptionLayoutVisibility(
      EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      boolean shouldShowProductGroupDescription) {
    if (shouldShowProductGroupDescription) {
      encompassPartnersChildViewHolder.layoutProductGroupDescription.setVisibility(View.VISIBLE);
    } else {
      encompassPartnersChildViewHolder.layoutProductGroupDescription.setVisibility(View.GONE);
    }
  }

  private void handleSubCategoryLayoutVisibility(
      EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      boolean shouldShowSubCategory) {
    if (shouldShowSubCategory) {
      encompassPartnersChildViewHolder.layoutSubCategory.setVisibility(View.VISIBLE);
    } else {
      encompassPartnersChildViewHolder.layoutSubCategory.setVisibility(View.GONE);
    }
  }

  private void handleDividerWithNextImageLayoutVisibility(
      EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      boolean shouldShowDividerWithNextImage) {
    if (shouldShowDividerWithNextImage) {
      encompassPartnersChildViewHolder.layoutDividerWithNextImage2.setVisibility(View.VISIBLE);
    } else {
      encompassPartnersChildViewHolder.layoutDividerWithNextImage2.setVisibility(View.GONE);
    }
  }

  private void handleConfigureLayoutVisibility(
      EncompassPartnersChildViewHolder encompassPartnersChildViewHolder,
      boolean shouldShowConfigure) {
    if (shouldShowConfigure) {
      encompassPartnersChildViewHolder.layoutConfigure.setVisibility(View.VISIBLE);
    } else {
      encompassPartnersChildViewHolder.layoutConfigure.setVisibility(View.GONE);
    }
  }

  private void disallowProductGroupListToInterceptTouchEvent() {
    //Log.d(TAG, "disallowProductGroupListToInterceptTouchEvent()");
    encompassPartnersFragment.disallowProductGroupListToInterceptTouchEvent();
  }

  public List<Object> getItemList() {
    return mItemList;
  }

  class EncompassPartnersParentViewHolder extends ParentViewHolder {

    //private static final float INITIAL_POSITION = 0.0f;
    //private static final float ROTATED_POSITION = 180f;
    private ImageView imageViewProductGroup;
    private TextView textViewProductGroupName;
    private TextView textViewProductGroupDetails;
    private ImageView imageViewExpand;

    EncompassPartnersParentViewHolder(View itemView) {
      super(itemView);

      imageViewProductGroup = (ImageView) itemView.findViewById(R.id.imageViewProductGroup);
      textViewProductGroupName = (TextView) itemView.findViewById(R.id.textViewProductGroupName);
      textViewProductGroupDetails =
          (TextView) itemView.findViewById(R.id.textViewProductGroupDetails);
      imageViewExpand = (ImageView) itemView.findViewById(R.id.imageViewExpand);
    }

    @Override public void onExpansionToggled(boolean isExpanded) {
      super.onExpansionToggled(isExpanded);

      if (isExpanded) {
        imageViewExpand.setImageResource(R.drawable.ic_plus);
      } else {
        imageViewExpand.setImageResource(R.drawable.ic_minus);
      }

      //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      //  RotateAnimation rotateAnimation;
      //  if (isExpanded) { // rotate clockwise
      //    rotateAnimation = new RotateAnimation(ROTATED_POSITION, INITIAL_POSITION,
      //        RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
      //  } else { // rotate counterclockwise
      //    rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION, INITIAL_POSITION,
      //        RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
      //  }
      //
      //  rotateAnimation.setDuration(200);
      //  rotateAnimation.setFillAfter(true);
      //  imageViewExpand.startAnimation(rotateAnimation);
      //}
    }

    @Override public void setExpanded(boolean isExpanded) {
      super.setExpanded(isExpanded);

      if (isExpanded) {
        imageViewExpand.setImageResource(R.drawable.ic_minus);
      } else {
        imageViewExpand.setImageResource(R.drawable.ic_plus);
      }

      //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      //  if (isExpanded) {
      //    imageViewExpand.setRotation(ROTATED_POSITION);
      //  } else {
      //    imageViewExpand.setRotation(INITIAL_POSITION);
      //  }
      //}
    }
  }

  class EncompassPartnersChildViewHolder extends ChildViewHolder {

    private RecyclerView recyclerViewCategory;
    private ImageView imageViewNext1;
    private LinearLayout layoutProductGroupDescription;
    private TextView textViewProductGroupDescription;
    private LinearLayout layoutSubCategory;
    private RecyclerView recyclerViewSubCategory;
    private LinearLayout layoutDividerWithNextImage2;
    private LinearLayout layoutConfigure;
    private Button buttonConfigure;
    private ImageView imageViewProduct;

    EncompassPartnersChildViewHolder(View itemView) {
      super(itemView);

      recyclerViewCategory = (RecyclerView) itemView.findViewById(R.id.recyclerViewCategory);
      imageViewNext1 = (ImageView) itemView.findViewById(R.id.imageViewNext1);
      layoutProductGroupDescription =
          (LinearLayout) itemView.findViewById(R.id.layoutProductGroupDescription);
      textViewProductGroupDescription =
          (TextView) itemView.findViewById(R.id.textViewProductGroupDescription);
      layoutSubCategory = (LinearLayout) itemView.findViewById(R.id.layoutSubCategory);
      recyclerViewSubCategory = (RecyclerView) itemView.findViewById(R.id.recyclerViewSubCategory);
      layoutDividerWithNextImage2 =
          (LinearLayout) itemView.findViewById(R.id.layoutDividerWithNextImage2);
      layoutConfigure = (LinearLayout) itemView.findViewById(R.id.layoutConfigure);
      buttonConfigure = (Button) itemView.findViewById(R.id.buttonConfigure);
      imageViewProduct = (ImageView) itemView.findViewById(R.id.imageViewProduct);
    }
  }
}
