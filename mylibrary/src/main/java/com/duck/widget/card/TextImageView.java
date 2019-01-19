package com.duck.widget.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;

import com.duck.mylibrary.R;


public class TextImageView extends android.support.v7.widget.AppCompatTextView {
    private int mLeftWidth;
    private int mLeftHeight;
    private int mTopWidth;
    private int mTopHeight;
    private int mRightWidth;
    private int mRightHeight;
    private int mBottomWidth;
    private int mBottomHeight;

    public @ColorInt int drawableTint;

    public TextImageView(Context context) {
        super(context);
    }

    public TextImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextImageView);

        mLeftWidth = typedArray.getDimensionPixelOffset(R.styleable.TextImageView_drawableLeftWidth, 40);
        mLeftHeight = typedArray.getDimensionPixelOffset(R.styleable.TextImageView_drawableLeftHeight, 40);
        mTopWidth = typedArray.getDimensionPixelOffset(R.styleable.TextImageView_drawableTopWidth, 40);
        mTopHeight = typedArray.getDimensionPixelOffset(R.styleable.TextImageView_drawableTopHeight, 40);
        mRightWidth = typedArray.getDimensionPixelOffset(R.styleable.TextImageView_drawableRightWidth, 40);
        mRightHeight = typedArray.getDimensionPixelOffset(R.styleable.TextImageView_drawableRightHeight, 40);
        mBottomWidth = typedArray.getDimensionPixelOffset(R.styleable.TextImageView_drawableBottomWidth, 40);
        mBottomHeight = typedArray.getDimensionPixelOffset(R.styleable.TextImageView_drawableBottomHeight, 40);

        drawableTint = typedArray.getColor(R.styleable.TextImageView_drawableTint, -1);

        typedArray.recycle();
        setDrawablesSize();
    }

    private void setDrawablesSize() {
        Drawable[] compoundDrawables = getCompoundDrawables();
        for (int i = 0; i < compoundDrawables.length; i++) {
            switch (i) {
                case 0:
                    setDrawableBounds(compoundDrawables[0], mLeftWidth, mLeftHeight);
                    break;
                case 1:
                    setDrawableBounds(compoundDrawables[1], mTopWidth, mTopHeight);
                    break;
                case 2:
                    setDrawableBounds(compoundDrawables[2], mRightWidth, mRightHeight);
                    break;
                case 3:
                    setDrawableBounds(compoundDrawables[3], mBottomWidth, mBottomHeight);
                    break;
                default:

                    break;
            }

        }
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
    }

    private void setDrawableBounds(Drawable drawable, int width, int height) {
        if (drawable != null) {

            if (drawableTint != -1) {
                DrawableCompat.setTint(DrawableCompat.wrap(drawable).mutate(), drawableTint);
            }

            double scale = ((double) drawable.getIntrinsicHeight()) / ((double) drawable.getIntrinsicWidth());
            drawable.setBounds(0, 0, width, height);
            Rect bounds = drawable.getBounds();
            //高宽只给一个值时，自适应
            if (bounds.right != 0 || bounds.bottom != 0) {
                if (bounds.right == 0) {
                    bounds.right = (int) (bounds.bottom / scale);
                    drawable.setBounds(bounds);
                }
                if (bounds.bottom == 0) {
                    bounds.bottom = (int) (bounds.right * scale);
                    drawable.setBounds(bounds);
                }
            }

        }
    }

    /**
     * Drawable color
     */
    public void setDrawableTint(@ColorInt int colorInt) {
        drawableTint = colorInt;
        setDrawablesSize();
    }

    /**
     * 設置 左icon寬
     */
    public void setLeftWidth(int mLeftWidth) {
        this.mLeftWidth = mLeftWidth;
    }

    /**
     * 設置 左icon高
     */
    public void setLeftHeight(int mLeftHeight) {
        this.mLeftHeight = mLeftHeight;
    }

    /**
     * 設置 上icon寬
     */
    public void setTopWidth(int mTopWidth) {
        this.mTopWidth = mTopWidth;
    }

    /**
     * 設置 上icon高
     */
    public void setTopHeight(int mTopHeight) {
        this.mTopHeight = mTopHeight;
    }

    /**
     * 設置 右icon寬
     */
    public void setmRightWidth(int mRightWidth) {
        this.mRightWidth = mRightWidth;
    }

    /**
     * 設置 右icon高
     */
    public void setRightHeight(int mRightHeight) {
        this.mRightHeight = mRightHeight;
    }

    /**
     * 設置 下icon寬
     */
    public void setBottomWidth(int mBottomWidth) {
        this.mBottomWidth = mBottomWidth;
    }

    /**
     * 設置 下icon高
     */
    public void setBottomHeight(int mBottomHeight) {
        this.mBottomHeight = mBottomHeight;
    }

    public void setAll(int width, int height) {
        mLeftWidth = width;
        mLeftHeight = height;
        mTopWidth = width;
        mTopHeight = height;
        mRightWidth = width;
        mRightHeight = height;
        mBottomWidth = width;
        mBottomHeight = height;
        setDrawablesSize();
    }

    public void setAllWidth(int width) {
        mLeftWidth = width;
        mTopWidth = width;
        mRightWidth = width;
        mBottomWidth = width;
        setDrawablesSize();
    }

    public void setAllHeight(int height) {
        mLeftHeight = height;
        mTopHeight = height;
        mRightHeight = height;
        mBottomHeight = height;
        setDrawablesSize();
    }

    public void reloadAttr() {
        setDrawablesSize();
    }

}
