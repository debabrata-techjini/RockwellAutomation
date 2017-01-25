/*
Copyright (c) 2015 Big Nerd Ranch

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
 */

package com.techjini.rockwellautomation.expandablerecyclerview.adapter;

import com.techjini.rockwellautomation.expandablerecyclerview.model.ParentListItem;
import com.techjini.rockwellautomation.expandablerecyclerview.model.ParentWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper for {@link ExpandableRecyclerAdapter}.
 *
 * Created by Ryan Brooks on 6/11/15.
 */
public class ExpandableRecyclerAdapterHelper {

  /**
   * Generates a full list of all {@link ParentListItem} objects and their
   * children, in order.
   *
   * @param parentItemList A list of the {@code ParentListItem} objects from
   * the {@link ExpandableRecyclerAdapter}
   * @return A list of all {@code ParentListItem} objects and their children, expanded
   */
  public static List<Object> generateParentChildItemList(
      List<? extends ParentListItem> parentItemList) {
    List<Object> parentWrapperList = new ArrayList<>();
    ParentListItem parentListItem;
    ParentWrapper parentWrapper;

    int parentListItemCount = parentItemList.size();
    for (int i = 0; i < parentListItemCount; i++) {
      parentListItem = parentItemList.get(i);
      parentWrapper = new ParentWrapper(parentListItem);
      parentWrapperList.add(parentWrapper);

      if (parentWrapper.isInitiallyExpanded()) {
        parentWrapper.setExpanded(true);

        int childListItemCount = parentWrapper.getChildItemList().size();
        for (int j = 0; j < childListItemCount; j++) {
          parentWrapperList.add(parentWrapper.getChildItemList().get(j));
        }
      }
    }

    return parentWrapperList;
  }
}
