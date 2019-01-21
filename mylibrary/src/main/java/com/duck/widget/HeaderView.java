package com.duck.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.duck.mylibrary.R;
import com.duck.mylibrary.R2;
import com.duck.tools.ToolsPhonePixels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderView extends BaseWidgetView {
    @BindView(R2.id.tvHeaderTitle) AppCompatTextView tvHeaderTitle;
    @BindView(R2.id.menuImg) ImageView menuImg;
    @BindView(R2.id.headerLayout) ConstraintLayout headerLayout;
    @BindView(R2.id.barViewV) View barView;
    @BindView(R2.id.rightImg) ImageView rightImg;

    private String titleText;
    public @ColorInt int titleTextColor;
    public int titleTextGravity;

    public @DrawableRes int headerDrawable;
    public @ColorInt int headerDrawableTint;
    public Drawable headerBackground;

    public @DrawableRes int headerRightIcon;
    public @ColorInt int headerRightIconTint;

    public boolean showInsideStatusBar; //是否marginTop狀態列高度，請確認Activity是否有支援

    public @Dimension int icon_drawable_width; //icon寬
    public @Dimension int icon_drawable_height; //icon高
    public @Dimension int icon_drawable_padding;

    private View.OnClickListener menuPressListener;
    private View.OnClickListener rightImgListener;

    public HeaderView(@NonNull Context context) {
        super(context);
    }

    public HeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected int getWidgetLayout() {
        return R.layout.widget_header_view;
    }

    @Override protected void initAttr(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.HeaderView, 0, 0);

        titleText = a.getString(R.styleable.HeaderView_hd_headerText);
        titleTextColor = a.getColor(R.styleable.HeaderView_hd_headerTextColor, getResources().getColor(R.color.black));


        titleTextGravity = a.getInt(R.styleable.HeaderView_hd_header_gravity, 3);


        headerDrawable = a.getResourceId(R.styleable.HeaderView_hd_headerDrawable, 0);
        headerDrawableTint = a.getColor(R.styleable.HeaderView_hd_headerDrawableTint, 0);

        headerBackground = a.getDrawable(R.styleable.HeaderView_hd_headerBackground);

        headerRightIcon = a.getResourceId(R.styleable.HeaderView_hd_headerRightIcon, 0);
        headerRightIconTint = a.getColor(R.styleable.HeaderView_hd_headerRightIconTint, 0);

        showInsideStatusBar = a.getBoolean(R.styleable.HeaderView_hd_showInsideStatusBar, false);

        icon_drawable_width = (int) a.getDimension(R.styleable.HeaderView_hd_icon_drawable_mWidth,
                                                   TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                                                             40,
                                                                             getResources().getDisplayMetrics()));
        icon_drawable_height = (int) a.getDimension(R.styleable.HeaderView_hd_icon_drawable_mHeight,
                                                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                                                              40,
                                                                              getResources().getDisplayMetrics()));

        icon_drawable_padding = (int) a.getDimension(R.styleable.HeaderView_hd_icon_drawable_mPadding,
                                                     TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                                                               10,
                                                                               getResources().getDisplayMetrics()));

        a.recycle();
    }

    @Override protected void init() {
        ButterKnife.bind(this);

        menuImg.setOnClickListener(v -> {
            if (menuPressListener != null) {
                menuPressListener.onClick(v);
            }
        });

        rightImg.setOnClickListener(v -> {
            if (rightImgListener != null) {
                rightImgListener.onClick(v);
            }
        });

    }

    @Override protected void syncAttr() {
        tvHeaderTitle.setText(titleText);
        tvHeaderTitle.setTextColor(titleTextColor);
        switch (titleTextGravity) {
            case 1:
                tvHeaderTitle.setGravity(Gravity.START);
                break;
            case 2:
                tvHeaderTitle.setGravity(Gravity.START | Gravity.CENTER);
                break;
            case 3:
                tvHeaderTitle.setGravity(Gravity.CENTER);
                break;
            case 4:
                tvHeaderTitle.setGravity(Gravity.END);
                break;
            case 5:
                tvHeaderTitle.setGravity(Gravity.END | Gravity.CENTER);
                break;
            case 6:
                tvHeaderTitle.setGravity(Gravity.TOP);
                break;
            case 7:
                tvHeaderTitle.setGravity(Gravity.TOP | Gravity.CENTER);
                break;
            case 8:
                tvHeaderTitle.setGravity(Gravity.BOTTOM);
                break;
            case 9:
                tvHeaderTitle.setGravity(Gravity.BOTTOM | Gravity.CENTER);
                break;
        }


        menuImg.setImageResource(headerDrawable);
        if (headerDrawableTint != 0) {
            menuImg.setColorFilter(headerDrawableTint);
        }

        if (headerRightIcon != 0) {
            rightImg.setVisibility(VISIBLE);
            rightImg.setImageResource(headerRightIcon);
            rightImg.setColorFilter(headerRightIconTint);
        } else {
            rightImg.setVisibility(INVISIBLE);
        }

        menuImg.getLayoutParams().height = icon_drawable_height;
        menuImg.getLayoutParams().width = icon_drawable_width;
        menuImg.setPaddingRelative(icon_drawable_padding, icon_drawable_padding, icon_drawable_padding, icon_drawable_padding);

        rightImg.getLayoutParams().height = icon_drawable_height;
        rightImg.getLayoutParams().width = icon_drawable_width;
        rightImg.setPaddingRelative(icon_drawable_padding, icon_drawable_padding, icon_drawable_padding, icon_drawable_padding);

        headerLayout.setBackground(headerBackground);

        //xml設置才有效，其餘隱藏
        if (showInsideStatusBar) {
            barView.setVisibility(VISIBLE);
            //獲取狀態列高度
            ViewGroup.LayoutParams t = null;
            if (barView.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
                t = barView.getLayoutParams();
            } else if (barView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                t = barView.getLayoutParams();
            } else if (barView.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                t = barView.getLayoutParams();
            }

            if (t != null) {
                t.height = ToolsPhonePixels.getStatusBarHeight(getContext());
                barView.setLayoutParams(t);
            }
        } else {
            barView.setVisibility(GONE);
        }

    }

    public void setOnMenuPressListener(View.OnClickListener listener) {
        this.menuPressListener = listener;
    }

    public void setOnRightImgListener(View.OnClickListener rightImgListener) {
        this.rightImgListener = rightImgListener;
    }

    public ImageView getMenuImg() {
        return menuImg;
    }
}
