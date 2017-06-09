package com.huhx0015.doordashchallenge.view.decorators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.huhx0015.doordashchallenge.R;

/**
 * Created by Michael Yoon Huh on 6/1/2017.
 */

public class ListDividerItemDecoration extends RecyclerView.ItemDecoration {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    private Drawable mDividerLine;

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public ListDividerItemDecoration(Context context) {
        mDividerLine = ContextCompat.getDrawable(context, R.drawable.shape_line_divider);
    }

    /** DECORATOR METHODS ______________________________________________________________________ **/

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDividerLine.getIntrinsicHeight();

            mDividerLine.setBounds(left, top, right, bottom);
            mDividerLine.draw(c);
        }
    }
}
