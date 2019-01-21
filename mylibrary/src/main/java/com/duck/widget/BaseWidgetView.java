//package com.duck.widget;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.FrameLayout;
//
//public abstract class BaseWidgetView extends FrameLayout {
//    private View baseView;
//
//    public BaseWidgetView(@NonNull Context context) {
//        super(context);
//        inflateView();
//        initAttr(null);
//    }
//
//    public BaseWidgetView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        inflateView();
//        initAttr(attrs);
//    }
//
//    @Override protected void onFinishInflate() {
//        super.onFinishInflate();
//        init();
//        syncAttr();
//    }
//
//    private void inflateView() {
//        baseView = inflate(getContext(), getWidgetLayout(), this);
//    }
//
//    protected abstract @LayoutRes int getWidgetLayout();
//
//    protected abstract void initAttr(AttributeSet attrs);
//
//    protected abstract void init();
//
//    protected abstract void syncAttr();
//
//    protected View getBaseView() {
//        return baseView;
//    }
//
//    protected String getStringAttrOfDefault(TypedArray a, int index, String defValue) {
//        if (a.hasValue(index))
//            return a.getString(index);
//        return defValue;
//    }
//}
