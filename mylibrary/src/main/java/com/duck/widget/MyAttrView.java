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
import android.support.annotation.StyleableRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import com.duck.mylibrary.R;
import com.duck.mylibrary.R2;
import com.github.florent37.shapeofview.shapes.RoundRectView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAttrView extends BaseWidgetView {
    @BindView(R2.id.duck_av_mRoundRectView) RoundRectView mRoundRectView;
    @BindView(R2.id.duck_av_contentLayout) ConstraintLayout contentLayout;
    @BindView(R2.id.duck_av_mTextView) TextView mTextView;
    @BindView(R2.id.duck_av_image_left) ImageView imgLeft;
    @BindView(R2.id.duck_av_image_top) ImageView imgTop;
    @BindView(R2.id.duck_av_image_right) ImageView imgRight;
    @BindView(R2.id.duck_av_image_bottom) ImageView imgBottom;

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

    public @Dimension float contentSizeSp;
    public String contentText;
    public @ColorInt int contentTextColor;
    public Drawable contentBackground; //背景顏色

    public Drawable icon_drawable_left;
    public Drawable icon_drawable_top;
    public Drawable icon_drawable_right;
    public Drawable icon_drawable_bottom;
    public @ColorInt int icon_drawable_tint;  //icon顏色
    public @Dimension int icon_drawable_width; //icon寬
    public @Dimension int icon_drawable_height; //icon高


    private final int icon_default_margin = -1;
    public @Dimension int icon_drawable_margin;
    public @Dimension int icon_drawable_left_margin;
    public @Dimension int icon_drawable_top_margin;
    public @Dimension int icon_drawable_right_margin;
    public @Dimension int icon_drawable_bottom_margin;

    private boolean isSelect;

    public MyAttrView(@NonNull Context context) {
        super(context, null);
        init();
    }

    public MyAttrView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttr(attrs);
    }

    @Override protected int getWidgetLayout() {
        return R.layout.widget_my_attr_view;
    }

    @Override protected void initAttr(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyAttrView, 0, 0);

        //圓角
        radius_all = (int) a.getDimension(R.styleable.MyAttrView_av_radius, 0);
        radius_topLeft = (int) a.getDimension(R.styleable.MyAttrView_av_radius_topLeft, 0);
        radius_topRight = (int) a.getDimension(R.styleable.MyAttrView_av_radius_topRight, 0);
        radius_bottomLeft = (int) a.getDimension(R.styleable.MyAttrView_av_radius_bottomLeft, 0);
        radius_bottomRight = (int) a.getDimension(R.styleable.MyAttrView_av_radius_bottomRight, 0);

        //外框線
        borderColor = a.getColor(R.styleable.MyAttrView_av_borderColor, -1);
        borderWidth = a.getDimensionPixelSize(R.styleable.MyAttrView_av_borderWidth, -1);

        //背景
        contentBackground = a.getDrawable(R.styleable.MyAttrView_av_contentBackground);
        mPadding = (int) a.getDimension(R.styleable.MyAttrView_av_padding, 0);
        mPaddingLeft = (int) a.getDimension(R.styleable.MyAttrView_av_paddingLeft, 0);
        mPaddingRight = (int) a.getDimension(R.styleable.MyAttrView_av_paddingRight, 0);
        mPaddingTop = (int) a.getDimension(R.styleable.MyAttrView_av_paddingTop, 0);
        mPaddingBottom = (int) a.getDimension(R.styleable.MyAttrView_av_paddingBottom, 0);

        //text
        contentSizeSp = a.getDimension(R.styleable.MyAttrView_av_contentTextSizeSp,
                                       TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        contentText = a.getString(R.styleable.MyAttrView_av_contentText);
        contentTextColor = a.getColor(R.styleable.MyAttrView_av_contentTextColor,
                                      ContextCompat.getColor(getContext(), R.color.duck_text_default_color));

        //icon
        icon_drawable_tint = a.getColor(R.styleable.MyAttrView_av_icon_drawable_tint, 0);
        icon_drawable_width = (int) a.getDimension(R.styleable.MyAttrView_av_icon_drawable_mWidth,
                                                   TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                                                             16,
                                                                             getResources().getDisplayMetrics()));
        icon_drawable_height = (int) a.getDimension(R.styleable.MyAttrView_av_icon_drawable_mHeight,
                                                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                                                              16,
                                                                              getResources().getDisplayMetrics()));
        icon_drawable_left = a.getDrawable(R.styleable.MyAttrView_av_icon_drawable_left);
        icon_drawable_top = a.getDrawable(R.styleable.MyAttrView_av_icon_drawable_top);
        icon_drawable_right = a.getDrawable(R.styleable.MyAttrView_av_icon_drawable_right);
        icon_drawable_bottom = a.getDrawable(R.styleable.MyAttrView_av_icon_drawable_bottom);

        icon_drawable_margin = (int) a.getDimension(R.styleable.MyAttrView_av_icon_drawable_margin, icon_default_margin);
        icon_drawable_left_margin = (int) a.getDimension(R.styleable.MyAttrView_av_icon_drawable_left_margin, 3);
        icon_drawable_top_margin = (int) a.getDimension(R.styleable.MyAttrView_av_icon_drawable_top_margin, 3);
        icon_drawable_right_margin = (int) a.getDimension(R.styleable.MyAttrView_av_icon_drawable_right_margin, 3);
        icon_drawable_bottom_margin = (int) a.getDimension(R.styleable.MyAttrView_av_icon_drawable_bottom_margin, 3);

        a.recycle();
    }

    @Override protected void init() {
        ButterKnife.bind(this);
    }

    @Override protected void syncAttr() {
        if (radius_all != 0) {
            mRoundRectView.setTopLeftRadius(radius_all);
            mRoundRectView.setBottomLeftRadius(radius_all);
            mRoundRectView.setTopRightRadius(radius_all);
            mRoundRectView.setBottomRightRadius(radius_all);
        } else {
            mRoundRectView.setTopLeftRadius(radius_topLeft);
            mRoundRectView.setBottomLeftRadius(radius_bottomLeft);
            mRoundRectView.setTopRightRadius(radius_topRight);
            mRoundRectView.setBottomRightRadius(radius_bottomRight);
        }

        mRoundRectView.setBorderColor(borderColor);
        mRoundRectView.setBorderWidthPx((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                                                                        borderWidth,
                                                                        getResources().getDisplayMetrics()));

        contentLayout.setBackground(contentBackground);
        if (mPadding != 0) {
            contentLayout.setPadding(mPadding, mPadding, mPadding, mPadding);
        } else {
            contentLayout.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        }

        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSizeSp);
        mTextView.setText(contentText);
        mTextView.setTextColor(contentTextColor);

        setImageView(0, imgLeft);
        setImageView(1, imgTop);
        setImageView(2, imgRight);
        setImageView(3, imgBottom);

        imgLeft.setVisibility(icon_drawable_left != null ? VISIBLE : GONE);
        imgTop.setVisibility(icon_drawable_top != null ? VISIBLE : GONE);
        imgRight.setVisibility(icon_drawable_right != null ? VISIBLE : GONE);
        imgBottom.setVisibility(icon_drawable_bottom != null ? VISIBLE : GONE);

        if (icon_drawable_left != null) {
            imgLeft.setImageDrawable(icon_drawable_left);
        }
        if (icon_drawable_top != null) {
            imgTop.setImageDrawable(icon_drawable_top);
        }
        if (icon_drawable_right != null) {
            imgRight.setImageDrawable(icon_drawable_right);
        }
        if (icon_drawable_bottom != null) {
            imgBottom.setImageDrawable(icon_drawable_bottom);
        }
    }

    private void setImageView(int i, ImageView imageView) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = icon_drawable_width;
        layoutParams.height = icon_drawable_height;

        switch (i) {
            case 0: //左icon
                int left = icon_drawable_margin != icon_default_margin ? icon_drawable_margin : icon_drawable_left_margin;
                layoutParams.setMargins(0, 0, left, 0);
                break;
            case 1: //上icon
                int top = icon_drawable_margin != icon_default_margin ? icon_drawable_margin : icon_drawable_top_margin;
                layoutParams.setMargins(0, 0, 0, top);
                break;
            case 2: //右icon
                int right = icon_drawable_margin != icon_default_margin ? icon_drawable_margin : icon_drawable_right_margin;
                layoutParams.setMargins(right, 0, 0, 0);
                break;
            case 3: //下icon
                int bottom = icon_drawable_margin != icon_default_margin ? icon_drawable_margin : icon_drawable_bottom_margin;
                layoutParams.setMargins(0, bottom, 0, 0);
                break;
        }

        if (icon_drawable_tint != 0) {
            imageView.setColorFilter(icon_drawable_tint);
        }
    }

    /**
     * 是否讚
     */
    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean isLike) {
        this.isSelect = isLike;
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
        this.contentTextColor = ContextCompat.getColor(getContext(), colorRes);
        syncAttr();
    }

    /**
     * 設置外框線顏色
     */
    public void setBorderColor(@ColorRes int borderColor) {
        this.borderColor = ContextCompat.getColor(getContext(), borderColor);
        syncAttr();
    }

    /**
     * 設置 左icon
     */
    public void setIconDrawableLeft(@DrawableRes int redId) {
        this.icon_drawable_left = ContextCompat.getDrawable(getContext(), redId);
        syncAttr();
    }

    /**
     * 設置 上icon
     */
    public void setIconDrawableTop(@DrawableRes int redId) {
        this.icon_drawable_top = ContextCompat.getDrawable(getContext(), redId);
        syncAttr();
    }

    /**
     * 設置 右icon
     */
    public void setIconDrawableRight(@DrawableRes int redId) {
        this.icon_drawable_right = ContextCompat.getDrawable(getContext(), redId);
        syncAttr();
    }

    /**
     * 設置 下icon
     */
    public void setIconDrawableBottom(@DrawableRes int redId) {
        this.icon_drawable_bottom = ContextCompat.getDrawable(getContext(), redId);
        syncAttr();
    }

    /**
     * 設置 icon顏色，目前僅支援上下左右全部變色
     */
    public void setIconDrawableTint(@ColorRes int redId) {
        this.icon_drawable_tint = ContextCompat.getColor(getContext(), redId);
        syncAttr();
    }

    /**
     * 背景色
     */
    public void setContentBackground(Drawable contentBackground) {
        this.contentBackground = contentBackground;
        syncAttr();
    }

    public RoundRectView getRoundRectView() {
        return mRoundRectView;
    }

    public ConstraintLayout getContentLayout() {
        return contentLayout;
    }

    public TextView getTextView() {
        return mTextView;
    }

}
