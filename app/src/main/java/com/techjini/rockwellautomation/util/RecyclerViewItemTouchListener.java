package com.techjini.rockwellautomation.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Debu
 */
public class RecyclerViewItemTouchListener implements RecyclerView.OnItemTouchListener {

  private static final String TAG = RecyclerViewItemTouchListener.class.getSimpleName();
  private GestureDetector gestureDetector;

  private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

  public RecyclerViewItemTouchListener(Context context, final RecyclerView recyclerView,
      final OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
    this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

      @Override public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
      }
    });
  }

  @Override
  public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    //Log.d(TAG, "onInterceptTouchEvent()");
    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

    if (child != null && onRecyclerViewItemClickListener != null && gestureDetector.onTouchEvent(
        motionEvent)) {
      onRecyclerViewItemClickListener.onClick(child, recyclerView.getChildAdapterPosition(child));
    }

    return false;
  }

  @Override public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
  }

  @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
  }

  public interface OnRecyclerViewItemClickListener {
    void onClick(View view, int position);
  }
}
