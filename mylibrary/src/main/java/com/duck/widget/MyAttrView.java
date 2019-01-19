package com.duck.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.duck.mylibrary.R;
import com.duck.mylibrary.R2;
import com.github.florent37.shapeofview.shapes.RoundRectView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAttrView extends BaseWidgetView {
    @BindView(R2.id.myAttrRound) RoundRectView myAttrRound;
    @BindView(R2.id.myAttrConstraint) LinearLayout myAttrConstraint;
    @BindView(R2.id.myAttrIcon) AppCompatImageView myAttrIcon;
    @BindView(R2.id.myAttrContent) AppCompatTextView myAttrContent;

    public @Dimension int radius_all; //外框圓角dp
    public @Dimension int radius_topLeft; //上左圓角dp
    public @Dimension int radius_topRight; //上右圓角dp
    public @Dimension int radius_bottomLeft; //下左圓角dp
    public @Dimension int radius_bottomRight; //下右圓角dp

    public @ColorInt int borderColor; //外框顏色
    public @Dimension int borderWidth; //外框大小

    public @Dimension int mPadding;
    public @Dimension int mPaddingLeft;
    public @Dimension int mPaddingRight;
    public @Dimension int mPaddingTop;
    public @Dimension int mPaddingBottom;

    public @DrawableRes int iconDrawable;
    public @ColorInt int iconTint;
    public @Dimension int iconWidth;
    public @Dimension int iconHeight;

    public @Dimension float contentSizeSp;
    public String contentText;
    public @ColorInt int contentColor;
    public Drawable contentBackground; //背景顏色

    private boolean isLike;

    public MyAttrView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
    }

    @Override protected int getWidgetLayout() {
        return R.layout.widget_my_attr_view;
    }

    @Override protected void initAttr(AttributeSet attrs) {
        ButterKnife.bind(this);

        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MyAttrView, 0, 0);

        radius_all = (int) a.getDimension(R.styleable.MyAttrView_radius_all, 0);
        borderColor = a.getColor(R.styleable.MyAttrView_mBorderColor, -1);
        borderWidth = a.getDimensionPixelSize(R.styleable.MyAttrView_mBorderWidth, -1);

        contentBackground = a.getDrawable(R.styleable.MyAttrView_contentBackground);

        mPadding = (int) a.getDimension(R.styleable.MyAttrView_mPadding, 0);
        mPaddingLeft = (int) a.getDimension(R.styleable.MyAttrView_mPaddingLeft, 0);
        mPaddingRight = (int) a.getDimension(R.styleable.MyAttrView_mPaddingRight, 0);
        mPaddingTop = (int) a.getDimension(R.styleable.MyAttrView_mPaddingTop, 0);
        mPaddingBottom = (int) a.getDimension(R.styleable.MyAttrView_mPaddingBottom, 0);

        iconDrawable = a.getResourceId(R.styleable.MyAttrView_iconDrawable, 0);
        iconTint = a.getColor(R.styleable.MyAttrView_iconTint, ContextCompat.getColor(getContext(), R.color.transparent));
        iconWidth = (int) a.getDimension(R.styleable.MyAttrView_iconWidthDp, LinearLayout.LayoutParams.WRAP_CONTENT);
        iconHeight = (int) a.getDimension(R.styleable.MyAttrView_iconHeightDp, LinearLayout.LayoutParams.WRAP_CONTENT);

        contentSizeSp = a.getDimension(R.styleable.MyAttrView_contentSizeSp,
                                       TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        contentText = a.getString(R.styleable.MyAttrView_contentText);
        contentColor = a.getColor(R.styleable.MyAttrView_contentColor, ContextCompat.getColor(getContext(), R.color.black));

        myAttrIcon.setVisibility(iconDrawable == 0 ? GONE : VISIBLE);

        a.recycle();
    }

    @Override protected void init() {

    }

    @Override protected void syncAttr() {
        if (radius_all != 0) {
            myAttrRound.setTopLeftRadius(radius_all);
            myAttrRound.setBottomLeftRadius(radius_all);
            myAttrRound.setTopRightRadius(radius_all);
            myAttrRound.setBottomRightRadius(radius_all);
        } else {
            myAttrRound.setTopLeftRadius(radius_topLeft);
            myAttrRound.setBottomLeftRadius(radius_bottomLeft);
            myAttrRound.setTopRightRadius(radius_topRight);
            myAttrRound.setBottomRightRadius(radius_bottomRight);
        }

        myAttrRound.setBorderColor(borderColor);
        myAttrRound.setBorderWidthPx((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                                                     borderWidth,
                                                                     getResources().getDisplayMetrics()));

        myAttrConstraint.setBackground(contentBackground);
        if (mPadding != 0) {
            myAttrConstraint.setPadding(mPadding, mPadding, mPadding, mPadding);
        } else {
            myAttrConstraint.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        }

        myAttrIcon.setImageResource(iconDrawable);
        myAttrIcon.setColorFilter(iconTint);

        myAttrContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSizeSp);
        myAttrContent.setText(contentText);
        myAttrContent.setTextColor(contentColor);

        if (iconWidth > 0) {
            myAttrIcon.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                                                                                 iconWidth,
                                                                                 getResources().getDisplayMetrics());
        }

        if (iconHeight > 0) {
            myAttrIcon.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                                                                                  iconHeight,
                                                                                  getResources().getDisplayMetrics());
        }

        myAttrIcon.requestLayout();
    }

    /**
     * 是否讚
     */
    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean isLike) {
        this.isLike = isLike;
    }

    /**
     * 設置文字text
     */
    public void setText(CharSequence text) {
        this.contentText = text.toString();
        syncAttr();
    }

    /**
     * 設置文字color
     */
    public void setTextColor(@ColorRes int colorRes) {
        this.contentColor = getResources().getColor(colorRes);
        syncAttr();
    }

    /**
     * 設置icon Res
     */
    public void setIconDrawableRes(@DrawableRes int iconDrawable) {
        this.iconDrawable = iconDrawable;
        syncAttr();
    }

    /**
     * 設置icon顏色
     */
    public void setDrawableColor(@ColorRes int colorRes) {
        this.iconTint = getResources().getColor(colorRes);
        syncAttr();
    }

    /**
     * 設置icon、text顏色
     */
    public void setAllColor(@ColorRes int colorRes) {
        this.contentColor = getResources().getColor(colorRes);
        this.iconTint = getResources().getColor(colorRes);
        syncAttr();
    }

    /**
     * 設置外框線顏色
     */
    public void setBorderColor(@ColorRes int borderColor) {
        this.borderColor = ContextCompat.getColor(getContext(), borderColor);
        syncAttr();
    }

}
