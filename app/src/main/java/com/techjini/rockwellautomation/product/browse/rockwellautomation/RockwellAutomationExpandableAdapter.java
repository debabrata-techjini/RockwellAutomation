package com.techjini.rockwellautomation.product.browse.rockwellautomation;

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
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.RockwellAutomationChildListItem;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.category.Category;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.category.CategoryAdapter;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.category.CategoryState;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.subcategory.SubCategory;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.subcategory.SubCategoryAdapter;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.childlist.subcategory.SubCategoryState;
import com.techjini.rockwellautomation.product.browse.rockwellautomation.parentlist.RockwellAutomationParentListItem;
import com.techjini.rockwellautomation.util.RecyclerViewItemTouchListener;
import java.util.ArrayList;
import java.util.List;

import static com.techjini.rockwellautomation.util.Constants.DEFAULT_IMAGE_RESOURCE;

/**
 * Created by Debu
 */
public class RockwellAutomationExpandableAdapter extends
    ExpandableRecyclerAdapter<RockwellAutomationExpandableAdapter.RockwellAutomationParentViewHolder, RockwellAutomationExpandableAdapter.RockwellAutomationChildViewHolder> {

  private static final String TAG = RockwellAutomationExpandableAdapter.class.getSimpleName();
  private Context context;
  private RockwellAutomationFragment rockwellAutomationFragment;
  private List<RockwellAutomationParentListItem> listOfRockwellAutomationParentListItems;
  private LayoutInflater layoutInflater;

  public RockwellAutomationExpandableAdapter(Context context,
      RockwellAutomationFragment rockwellAutomationFragment,
      @NonNull List<RockwellAutomationParentListItem> listOfRockwellAutomationParentListItems) {
    super(listOfRockwellAutomationParentListItems);

    this.context = context;
    this.rockwellAutomationFragment = rockwellAutomationFragment;
    this.listOfRockwellAutomationParentListItems = listOfRockwellAutomationParentListItems;
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public RockwellAutomationParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
    View view =
        layoutInflater.inflate(R.layout.rockwell_automation_parent_list_item, parentViewGroup,
            false);
    return new RockwellAutomationParentViewHolder(view);
  }

  @Override
  public RockwellAutomationChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
    View view =
        layoutInflater.inflate(R.layout.rockwell_automation_child_list_item, childViewGroup, false);
    return new RockwellAutomationChildViewHolder(view);
  }

  @Override public void onBindParentViewHolder(
      final RockwellAutomationParentViewHolder rockwellAutomationParentViewHolder,
      final int position, ParentListItem parentListItem) {
    RockwellAutomationParentListItem rockwellAutomationParentListItem =
        (RockwellAutomationParentListItem) parentListItem;

    bindRockwellAutomationParentViewHolder(rockwellAutomationParentViewHolder,
        rockwellAutomationParentListItem);
  }

  @Override public void onBindChildViewHolder(
      RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder, int position,
      Object childListItem) {
    RockwellAutomationChildListItem rockwellAutomationChildListItem =
        (RockwellAutomationChildListItem) childListItem;

    bindRockwellAutomationChildViewHolder(rockwellAutomationChildViewHolder,
        rockwellAutomationChildListItem);
  }

  @Override public void expandAllParents() {
    notifyParentItemRangeChanged(0, listOfRockwellAutomationParentListItems.size());
    super.expandAllParents();
  }

  @Override public void collapseAllParents() {
    notifyParentItemRangeChanged(0, listOfRockwellAutomationParentListItems.size());
    super.collapseAllParents();
  }

  private void bindRockwellAutomationParentViewHolder(
      final RockwellAutomationParentViewHolder rockwellAutomationParentViewHolder,
      RockwellAutomationParentListItem rockwellAutomationParentListItem) {
    if (rockwellAutomationParentListItem.getProductGroupImageUri() != null) {
      // TODO: Need to use Picasso lib to load image Uri to the image view
    } else {
      rockwellAutomationParentViewHolder.imageViewProductGroup.setImageResource(
          DEFAULT_IMAGE_RESOURCE);
    }

    rockwellAutomationParentViewHolder.textViewProductGroupName.setText(
        rockwellAutomationParentListItem.getProductGroupName());
    rockwellAutomationParentViewHolder.textViewProductGroupDetails.setText(
        rockwellAutomationParentListItem.getProductGroupDetails());
  }

  private void bindRockwellAutomationChildViewHolder(
      final RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      final RockwellAutomationChildListItem rockwellAutomationChildListItem) {
    List<Category> listOfCategories = rockwellAutomationChildListItem.getListOfCategories();
    List<SubCategory> listOfSubCategories = new ArrayList<>();
    final CategoryAdapter categoryAdapter = new CategoryAdapter(context, listOfCategories);
    final SubCategoryAdapter subCategoryAdapter =
        new SubCategoryAdapter(context, listOfSubCategories);

    forLoop:
    for (Category category : listOfCategories) {
      if (category.getCategoryState().isSelected()) {
        handleViewVisibilityForSelectedCategory(rockwellAutomationChildViewHolder,
            category.getCategoryState());
        // When a selected category is found, we need to show list of sub categories, corresponding
        // to the category
        listOfSubCategories = category.getListOfSubCategories();
        subCategoryAdapter.updateListOfSubCategories(listOfSubCategories);
        subCategoryAdapter.notifyItemRangeChanged(0, listOfSubCategories.size());
        // Only one of the categories can be selected; so once a selected category is found; break
        // from the outer "for" loop.
        break forLoop;
      }
    }

    forLoop:
    for (SubCategory subCategory : listOfSubCategories) {
      if (subCategory.getSubCategoryState().isSelected()) {
        getProductImage(rockwellAutomationChildViewHolder, subCategory.getProductImageUri());
        handleViewVisibilityForSelectedSubCategory(rockwellAutomationChildViewHolder,
            subCategory.getSubCategoryState());
        // Only one of the Categories can be selected; so once a selected category is found; break
        // from the outer "for" loop.
        break forLoop;
      }
    }

    rockwellAutomationChildViewHolder.recyclerViewCategory.setAdapter(categoryAdapter);
    rockwellAutomationChildViewHolder.recyclerViewCategory.setLayoutManager(
        new LinearLayoutManager(context));
    rockwellAutomationChildViewHolder.recyclerViewCategory.setHasFixedSize(true);
    rockwellAutomationChildViewHolder.textViewProductGroupDescription.setText(
        rockwellAutomationChildListItem.getProductGroupDescription());

    rockwellAutomationChildViewHolder.recyclerViewSubCategory.setAdapter(subCategoryAdapter);
    rockwellAutomationChildViewHolder.recyclerViewSubCategory.setLayoutManager(
        new LinearLayoutManager(context));
    rockwellAutomationChildViewHolder.recyclerViewSubCategory.setHasFixedSize(true);

    rockwellAutomationChildViewHolder.recyclerViewCategory.addOnItemTouchListener(
        new RecyclerViewItemTouchListener(context,
            rockwellAutomationChildViewHolder.recyclerViewCategory,
            new RecyclerViewItemTouchListener.OnRecyclerViewItemClickListener() {
              @Override public void onClick(View view, int position) {
                Log.d(TAG, "onClick() -> selected category position: " + position);
                handleCategorySelection(rockwellAutomationChildViewHolder, categoryAdapter,
                    subCategoryAdapter, position);
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

    rockwellAutomationChildViewHolder.recyclerViewSubCategory.addOnItemTouchListener(
        new RecyclerViewItemTouchListener(context,
            rockwellAutomationChildViewHolder.recyclerViewSubCategory,
            new RecyclerViewItemTouchListener.OnRecyclerViewItemClickListener() {
              @Override public void onClick(View view, int position) {
                Log.d(TAG, "onClick() -> selected sub category position: " + position);
                handleSubCategorySelection(rockwellAutomationChildViewHolder, subCategoryAdapter,
                    position);
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
      RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      CategoryAdapter categoryAdapter, SubCategoryAdapter subCategoryAdapter,
      int selectedCategoryPosition) {
    List<Category> listOfCategories = categoryAdapter.getListOfCategories();
    setAllCategoryState(listOfCategories, selectedCategoryPosition);
    Category selectedCategory = categoryAdapter.getCategory(selectedCategoryPosition);
    CategoryState selectedCategoryState = selectedCategory.getCategoryState();
    List<SubCategory> listOfSubCategories = selectedCategory.getListOfSubCategories();
    initializeSubCategoryState(listOfSubCategories);
    subCategoryAdapter.updateListOfSubCategories(listOfSubCategories);

    handleViewVisibilityForSelectedCategory(rockwellAutomationChildViewHolder,
        selectedCategoryState);

    categoryAdapter.notifyItemRangeChanged(0, listOfCategories.size());
    subCategoryAdapter.notifyItemRangeChanged(0, listOfSubCategories.size());
  }

  private void handleSubCategorySelection(
      RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      SubCategoryAdapter subCategoryAdapter, int selectedSubCategoryPosition) {
    List<SubCategory> listOfSubCategories = subCategoryAdapter.getListOfSubCategories();

    setAllSubCategoryState(listOfSubCategories, selectedSubCategoryPosition);
    SubCategory selectedSubCategory =
        subCategoryAdapter.getSubCategory(selectedSubCategoryPosition);
    SubCategoryState selectedSubCategoryState = selectedSubCategory.getSubCategoryState();
    String productImageUri = selectedSubCategory.getProductImageUri();
    getProductImage(rockwellAutomationChildViewHolder, productImageUri);

    handleViewVisibilityForSelectedSubCategory(rockwellAutomationChildViewHolder,
        selectedSubCategoryState);

    subCategoryAdapter.notifyItemRangeChanged(0, listOfSubCategories.size());
  }

  private void handleViewVisibilityForSelectedCategory(
      RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      CategoryState selectedCategoryState) {
    handleNextImageVisibility(rockwellAutomationChildViewHolder,
        selectedCategoryState.isNextImageVisible());
    handleProductGroupDescriptionLayoutVisibility(rockwellAutomationChildViewHolder,
        selectedCategoryState.isProductGroupDescriptionVisible());
    handleSubCategoryLayoutVisibility(rockwellAutomationChildViewHolder,
        selectedCategoryState.isSubCategoryVisible());
    handleDividerWithNextImageLayoutVisibility(rockwellAutomationChildViewHolder,
        selectedCategoryState.isDividerWithNextImageVisible());
    handleConfigureLayoutVisibility(rockwellAutomationChildViewHolder,
        selectedCategoryState.isConfigureVisible());
  }

  private void handleViewVisibilityForSelectedSubCategory(
      RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      SubCategoryState selectedSubCategoryState) {
    handleDividerWithNextImageLayoutVisibility(rockwellAutomationChildViewHolder,
        selectedSubCategoryState.isDividerWithNextImageVisible());
    handleConfigureLayoutVisibility(rockwellAutomationChildViewHolder,
        selectedSubCategoryState.isConfigureVisible());
  }

  private void getProductImage(RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      String productImageUri) {
    if (productImageUri != null) {
      // TODO: Need to use Picasso lib to load image Uri to the image view
    } else {
      rockwellAutomationChildViewHolder.imageViewProduct.setImageResource(DEFAULT_IMAGE_RESOURCE);
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
      RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      boolean shouldShowNextImage) {
    if (shouldShowNextImage) {
      rockwellAutomationChildViewHolder.imageViewNext1.setVisibility(View.VISIBLE);
    } else {
      rockwellAutomationChildViewHolder.imageViewNext1.setVisibility(View.GONE);
    }
  }

  private void handleProductGroupDescriptionLayoutVisibility(
      RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      boolean shouldShowProductGroupDescription) {
    if (shouldShowProductGroupDescription) {
      rockwellAutomationChildViewHolder.layoutProductGroupDescription.setVisibility(View.VISIBLE);
    } else {
      rockwellAutomationChildViewHolder.layoutProductGroupDescription.setVisibility(View.GONE);
    }
  }

  private void handleSubCategoryLayoutVisibility(
      RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      boolean shouldShowSubCategory) {
    if (shouldShowSubCategory) {
      rockwellAutomationChildViewHolder.layoutSubCategory.setVisibility(View.VISIBLE);
    } else {
      rockwellAutomationChildViewHolder.layoutSubCategory.setVisibility(View.GONE);
    }
  }

  private void handleDividerWithNextImageLayoutVisibility(
      RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      boolean shouldShowDividerWithNextImage) {
    if (shouldShowDividerWithNextImage) {
      rockwellAutomationChildViewHolder.layoutDividerWithNextImage2.setVisibility(View.VISIBLE);
    } else {
      rockwellAutomationChildViewHolder.layoutDividerWithNextImage2.setVisibility(View.GONE);
    }
  }

  private void handleConfigureLayoutVisibility(
      RockwellAutomationChildViewHolder rockwellAutomationChildViewHolder,
      boolean shouldShowConfigure) {
    if (shouldShowConfigure) {
      rockwellAutomationChildViewHolder.layoutConfigure.setVisibility(View.VISIBLE);
    } else {
      rockwellAutomationChildViewHolder.layoutConfigure.setVisibility(View.GONE);
    }
  }

  public void disallowProductGroupListToInterceptTouchEvent() {
    //Log.d(TAG, "disallowProductGroupListToInterceptTouchEvent()");
    rockwellAutomationFragment.disallowProductGroupListToInterceptTouchEvent();
  }

  public List<Object> getItemList() {
    return mItemList;
  }

  class RockwellAutomationParentViewHolder extends ParentViewHolder {

    //private static final float INITIAL_POSITION = 0.0f;
    //private static final float ROTATED_POSITION = 180f;
    private ImageView imageViewProductGroup;
    private TextView textViewProductGroupName;
    private TextView textViewProductGroupDetails;
    private ImageView imageViewExpand;

    public RockwellAutomationParentViewHolder(View itemView) {
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

  class RockwellAutomationChildViewHolder extends ChildViewHolder {

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

    public RockwellAutomationChildViewHolder(View itemView) {
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
