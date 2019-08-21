package com.liuxiangdong.jsonview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * A custom view that provides a indentation view of a Json entry.
 * There will be a vertical line displayed for each step of indentation,
 * to create a visual effect for depth.
 * The view will be the width of depth * indentation.
 */
public class IndentationView extends View {
    private Paint paint;
    /**
     * The desired indentation
     */
    private int indentation;
    /**
     * The depth of the Json entry
     */
    private int depth;
    public IndentationView(Context context) {
        super(context);
        init(context);
    }

    public IndentationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IndentationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IndentationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        indentation = context.getResources().getDimensionPixelSize(R.dimen.json_view_indentation);
    }

    /**
     * Set the line color.
     * @param color
     */
    public void setLineColor(@ColorInt int color) {
        paint.setColor(color);
    }

    /**
     * Set the line width.
     * @param width
     */
    public void setLineWidth(int width) {
        paint.setStrokeWidth(width);
    }

    /**
     * Set the step of the indentation.
     * @param indentation
     */
    public void setIndentation(int indentation) {
        this.indentation = indentation;
    }

    /**
     * Set the depth.
     * @param depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.width = depth * indentation;
        setLayoutParams(lp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float startY = 0;
        float stopY = getMeasuredHeight();
        float startX = paint.getStrokeWidth() / 2;
        for (int i = 0; i < depth; i++) {
            canvas.drawLine(startX, startY, startX, stopY, paint);
            startX += indentation;
        }
    }
}
