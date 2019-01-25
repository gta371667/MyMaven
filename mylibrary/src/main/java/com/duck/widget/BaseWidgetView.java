package com.duck.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public abstract class BaseWidgetView extends FrameLayout {
    private View baseView;

    public BaseWidgetView(@NonNull Context context) {
        super(context);
        inflateView();
        initAttr(null);
    }

    public BaseWidgetView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateView();
        initAttr(attrs);
    }

    public BaseWidgetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView();
        initAttr(attrs);
    }

    /**
     * 僅在xml有效
     * 手動new Instance時不會調用
     */
    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        init();
        syncAttr();
    }

    private void inflateView() {
        baseView = inflate(getContext(), getWidgetLayout(), this);
    }

    protected abstract @LayoutRes int getWidgetLayout();

    protected abstract void initAttr(AttributeSet attrs);

    protected abstract void init();

    protected abstract void syncAttr();

    protected View getBaseView() {
        return baseView;
    }

}
