package com.duck.widget.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.duck.mylibrary.R;
import com.duck.mylibrary.R2;
import com.duck.widget.BaseWidgetView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardTextView extends BaseWidgetView {
    @BindView(R2.id.materialCardView) MaterialCardView materialCardView;
    @BindView(R2.id.contentLayout) LinearLayout contentLayout;
    @BindView(R2.id.mTextImageView) TextImageView mTextImageView;

    private final int icon_default_width = 40;
    private final int icon_default_height = 40;

    public @Dimension int radius_all; //外框圓角dp，目前僅支援全部角度，若要單角請用shape

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
    public @Dimension int iconMargin;

    public @Dimension float contentSizeSp;
    public String contentText;
    public @ColorInt int contentColor;
    public Drawable contentBackground;

    private boolean isLike;

    public CardTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected int getWidgetLayout() {
        return R.layout.widget_card_text_view;
    }

    @Override protected void initAttr(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CardTextView, 0, 0);

        radius_all = (int) a.getDimension(R.styleable.CardTextView_card_radius, 0);
        borderColor = a.getColor(R.styleable.CardTextView_card_borderColor, -1);
        borderWidth = a.getDimensionPixelSize(R.styleable.CardTextView_card_borderWidth, -1);

        contentBackground = a.getDrawable(R.styleable.CardTextView_card_bgColor);

        mPadding = (int) a.getDimension(R.styleable.CardTextView_card_padding, 0);
        mPaddingLeft = (int) a.getDimension(R.styleable.CardTextView_card_paddingLeft, 0);
        mPaddingRight = (int) a.getDimension(R.styleable.CardTextView_card_paddingRight, 0);
        mPaddingTop = (int) a.getDimension(R.styleable.CardTextView_card_paddingTop, 0);
        mPaddingBottom = (int) a.getDimension(R.styleable.CardTextView_card_paddingBottom, 0);

        iconMargin = (int) a.getDimension(R.styleable.CardTextView_card_drawable_margin, 0);
        iconDrawable = a.getResourceId(R.styleable.CardTextView_card_drawable, 0);
        iconTint = a.getColor(R.styleable.CardTextView_card_drawable_tint, 0);
        iconWidth = a.getDimensionPixelOffset(R.styleable.CardTextView_card_drawable_width, icon_default_width);
        iconHeight = a.getDimensionPixelOffset(R.styleable.CardTextView_card_drawable_height, icon_default_height);

        contentText = a.getString(R.styleable.CardTextView_card_contentText);
        contentColor = a.getColor(R.styleable.CardTextView_card_content_textColor, getColor(R.color.stroke_default_color));
        contentSizeSp = a.getDimension(R.styleable.CardTextView_card_contentSizeSp,
                                       TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));

        a.recycle();
    }

    @Override protected void init() {
        ButterKnife.bind(this);
    }

    @Override protected void syncAttr() {
        materialCardView.setRadius(radius_all);
        materialCardView.setStrokeColor(borderColor);
        materialCardView.setStrokeWidth(borderWidth);

        contentLayout.setBackground(contentBackground);
        if (mPadding != 0) {
            contentLayout.setPadding(mPadding, mPadding, mPadding, mPadding);
        } else {
            contentLayout.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        }

        mTextImageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSizeSp);
        mTextImageView.setText(contentText);
        mTextImageView.setTextColor(contentColor);
        mTextImageView.setDrawableTint(iconTint);
        mTextImageView.setCompoundDrawables(getDrawable(R.drawable.ic_wechat),
                                            getDrawable(R.drawable.ic_wechat),
                                            getDrawable(R.drawable.ic_wechat),
                                            getDrawable(R.drawable.ic_wechat));

        if (iconWidth != icon_default_width) {
            mTextImageView.setAllWidth(iconWidth);
        }

        if (iconHeight != icon_default_height) {
            mTextImageView.setAllHeight(iconHeight);
        }

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
        this.borderColor = getResources().getColor(borderColor);
        syncAttr();
    }


    private Drawable getDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(getContext(), id);
    }

    private int getColor(@ColorRes int id) {
        return getResources().getColor(id);
        //        return ContextCompat.getColor(getContext(), id);
    }

}
