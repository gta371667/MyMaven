//package com.duck.widget;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.support.annotation.ColorInt;
//import android.support.annotation.IntDef;
//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.LinearSnapHelper;
//import android.support.v7.widget.PagerSnapHelper;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.SnapHelper;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.chad.library.adapter.base.loadmore.LoadMoreView;
//import com.duck.mylibrary.R;
//
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.util.List;
//
//public class DuckListView<T> extends BaseWidgetView {
//    RecyclerView mRecyclerView;
//    SwipeRefreshLayout mSwipeRefreshLayout;
//
//    @IntDef({Loading, NoData, Error})
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface EmptyViewEnum {
//    }
//
//    public static final int Loading = 1;
//    public static final int NoData = 2;
//    public static final int Error = 3;
//
//    public @ColorInt int swipeColor; //SwipeRefreshLayout旋轉顏色
//    private boolean refreshEnabled;
//
//    private SwipeRefreshLayout.OnRefreshListener onSwipeRefreshListener; //下拉刷新回調
//
//    private BaseQuickAdapter<T, BaseViewHolder> adapter;
//    private BaseQuickAdapter.RequestLoadMoreListener loadMoreListener; //加載更多
//
//    private View loadingView, noDataView, errorView;
//
//    public DuckListView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    @Override protected int getWidgetLayout() {
//        return R.layout.widget_duck_recyclerview;
//    }
//
//    /**
//     * 初始化屬性相關
//     */
//    @Override protected void initAttr(AttributeSet attrs) {
//        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.DuckListView, 0, 0);
//
//        swipeColor = a.getColor(R.styleable.DuckListView_swipeColor, getResources().getColor(R.color.swipe_default_color));
//        refreshEnabled = a.getBoolean(R.styleable.DuckListView_refreshEnabled, false);
//
//        a.recycle();
//    }
//
//    /**
//     * 初始化View相關
//     */
//    @Override protected void init() {
//        //        ButterKnife.bind(this);
//        mRecyclerView = findViewById(R.id.mRecyclerView);
//        mSwipeRefreshLayout = findViewById(R.id.mSwipeRefreshLayout);
//
//        loadingView = initEmptyView(getContext(), R.layout.rv_load_view, mSwipeRefreshLayout);
//        noDataView = initEmptyView(getContext(), R.layout.rv_nodata_view, mSwipeRefreshLayout);
//        errorView = initEmptyView(getContext(), R.layout.rv_error_view, mSwipeRefreshLayout);
//
//        //下拉刷新
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override public void onRefresh() {
//                refreshAll();
//            }
//        });
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }
//
//    /**
//     * 更新屬性
//     */
//    @Override protected void syncAttr() {
//        mSwipeRefreshLayout.setEnabled(refreshEnabled);
//        mSwipeRefreshLayout.setColorSchemeColors(swipeColor);
//    }
//
//    /**
//     * 下拉選轉顏色，支援多色
//     */
//    public void setRefreshColor(@ColorInt int... colorResIds) {
//        mSwipeRefreshLayout.setColorSchemeColors(colorResIds);
//    }
//
//    /**
//     * 下拉刷新全部
//     * 請勿在下拉刷新時手動調用
//     */
//    public void refreshAll() {
//        mSwipeRefreshLayout.setRefreshing(false);
//        clearData();
//
//        if (onSwipeRefreshListener != null) {
//            onSwipeRefreshListener.onRefresh();
//        }
//    }
//
//    /**
//     * 清除Adapter Data
//     */
//    public void clearData() {
//        if (adapter != null) {
//            adapter.getData().clear();
//            adapter.setNewData(adapter.getData());
//            adapter.setEmptyView(loadingView);
//            adapter.setEnableLoadMore(true);
//        }
//    }
//
//    /**
//     * 是否禁止下拉刷新
//     */
//    public void setRefreshEnabled(boolean enable) {
//        refreshEnabled = enable;
//        mSwipeRefreshLayout.setEnabled(enable);
//        syncAttr();
//    }
//
//    /**
//     * 下拉刷新監聽
//     * 刷新時會clear Adapter Data
//     */
//    public void setOnSwipeRefreshListener(SwipeRefreshLayout.OnRefreshListener onSwipeRefreshListener) {
//        this.onSwipeRefreshListener = onSwipeRefreshListener;
//    }
//
//    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
//        mRecyclerView.setLayoutManager(layoutManager);
//    }
//
//    /**
//     * 滑動至第幾筆
//     */
//    public void smoothScrollToPosition(int position) {
//        mRecyclerView.smoothScrollToPosition(position);
//    }
//
//    /**
//     * 初始化Adapter
//     */
//    public void setAdapter(@NonNull BaseQuickAdapter<T, BaseViewHolder> adapter) {
//        this.adapter = adapter;
//        this.adapter.setEmptyView(loadingView);
//        mRecyclerView.setAdapter(this.adapter);
//
//        this.adapter.setLoadMoreView(new LoadMoreView() {
//            @Override public int getLayoutId() {
//                return R.layout.rv_custom_load_view;
//            }
//
//            @Override protected int getLoadingViewId() {
//                return R.id.load_more_loading_view_test;
//            }
//
//            @Override protected int getLoadFailViewId() {
//                return R.id.load_more_load_fail_view_test;
//            }
//
//            @Override protected int getLoadEndViewId() {
//                return R.id.load_more_load_end_view_test;
//            }
//        });
//    }
//
//    /**
//     * 給Adapter不同記憶體位置資料刷新用
//     */
//    public void setAdapterNewData(List<T> data) {
//        adapter.setNewData(data);
//    }
//
//    /**
//     * 自訂載入更多View
//     * 列表加載更多時調用
//     */
//    public void setBottomLoadMoreView(LoadMoreView loadingView) {
//        adapter.setLoadMoreView(loadingView);
//    }
//
//    /**
//     * 倒數第幾筆開始執行LoadMore
//     * 預設最後一筆
//     */
//    public void setPreLoadNumber(int number) {
//        adapter.setPreLoadNumber(number);
//    }
//
//    /**
//     * Adapter 設定載入更多listener
//     */
//    public void setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener loadMoreListener) {
//        if (adapter == null) {
//            throw new NullPointerException("must init DuckRecyclerView Adapter first");
//        }
//        this.loadMoreListener = loadMoreListener;
//        adapter.setOnLoadMoreListener(loadMoreListener, mRecyclerView);
//    }
//
//    /**
//     * 加載數據完畢，還有資料
//     * 有setOnLoadMoreListener才有效
//     *
//     * @see #setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener)
//     */
//    public void loadMoreComplete() {
//        onLoad();
//
//        if (loadMoreListener == null) {
//            adapter.notifyDataSetChanged();
//        } else {
//            adapter.loadMoreComplete();
//        }
//
//        if (adapter.getData().size() == 0) {
//            adapter.setEmptyView(noDataView);
//        }
//    }
//
//    /**
//     * 加載數據完畢，沒有更多資料
//     * 有setOnLoadMoreListener才有效
//     *
//     * @see #setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener)
//     */
//    public void loadMoreEnd() {
//        onLoad();
//
//        if (loadMoreListener == null) {
//            adapter.notifyDataSetChanged();
//        } else {
//            adapter.loadMoreEnd();
//        }
//
//        if (adapter.getData().size() == 0) {
//            adapter.setEmptyView(noDataView);
//        }
//    }
//
//    /**
//     * 加載數據失敗fgfh
//     * 有setOnLoadMoreListener才有效
//     *
//     * @see #setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener)
//     */
//    public void loadMoreFail() {
//        onLoad();
//
//        if (loadMoreListener == null) {
//            adapter.notifyDataSetChanged();
//        } else {
//            adapter.loadMoreFail();
//        }
//
//        if (adapter.getData().size() == 0) {
//            adapter.setEmptyView(errorView);
//        }
//    }
//
//    /**
//     * 全部更新
//     */
//    public void notifyDataSetChanged() {
//        adapter.notifyDataSetChanged();
//    }
//
//    /**
//     * 更新第幾筆
//     */
//    public void notifyItemChanged(int pos) {
//        adapter.notifyItemChanged(pos);
//    }
//
//    private void onLoad() {
//        adapter.setEnableLoadMore(true);
//    }
//
//    /**
//     * Adapter 點擊事件
//     */
//    public void setAdapterOnItemChildClickListener(BaseQuickAdapter.OnItemChildClickListener onItemChildClickListener) {
//        if (adapter == null) {
//            throw new NullPointerException("must init DuckRecyclerView Adapter first");
//        }
//        adapter.setOnItemChildClickListener(onItemChildClickListener);
//    }
//
//    /**
//     * 使用Default分隔線
//     */
//    public void addDefaultItemDecoration() {
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
//    }
//
//    /**
//     * 自訂分隔線
//     */
//    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
//        mRecyclerView.addItemDecoration(itemDecoration);
//    }
//
//    /**
//     * Adapter EmptyView
//     */
//    public void showEmptyView(@EmptyViewEnum int emptyEnum) {
//        if (adapter == null) {
//            throw new NullPointerException("must init DuckRecyclerView Adapter first");
//        }
//        switch (emptyEnum) {
//            case Loading:
//                adapter.setEmptyView(loadingView);
//                break;
//            case NoData:
//                adapter.setEmptyView(noDataView);
//                break;
//            case Error:
//                adapter.setEmptyView(errorView);
//                break;
//        }
//    }
//
//    /**
//     * 自訂errorView
//     */
//    public void setErrorView(View errorView) {
//        this.errorView = errorView;
//    }
//
//    /**
//     * 自訂LoadingView
//     */
//    public void setLoadingView(View loadingView) {
//        this.loadingView = loadingView;
//    }
//
//    /**
//     * 自訂NoDataView
//     */
//    public void setNoDataView(View noDataView) {
//        this.noDataView = noDataView;
//    }
//
//    /**
//     * errorView onClick
//     * 預設無點擊事件
//     */
//    public void setErrorViewListener(View.OnClickListener listener) {
//        errorView.setOnClickListener(listener);
//    }
//
//    /**
//     * loadingView onClick
//     * 預設無點擊事件
//     */
//    public void setLoadingViewListener(View.OnClickListener listener) {
//        loadingView.setOnClickListener(listener);
//    }
//
//    /**
//     * noDataView onClick
//     * 預設無點擊事件
//     */
//    public void setNoDataViewListener(View.OnClickListener listener) {
//        noDataView.setOnClickListener(listener);
//    }
//
//    public SwipeRefreshLayout getSwipeRefreshLayout() {
//        return mSwipeRefreshLayout;
//    }
//
//    public RecyclerView getRecyclerView() {
//        return mRecyclerView;
//    }
//
//    public BaseQuickAdapter getAdapter() {
//        return adapter;
//    }
//
//    private static View initEmptyView(Context context, @LayoutRes int layout, @NonNull SwipeRefreshLayout swipeRefreshLayout) {
//        return LayoutInflater.from(context).inflate(layout, (ViewGroup) swipeRefreshLayout.getParent(), false);
//    }
//
//    /**
//     * 自訂SnapHelper
//     */
//    public void setSnapHelper(SnapHelper snapHelper) {
//        snapHelper.attachToRecyclerView(getRecyclerView());
//    }
//
//    /**
//     * 自訂SnapHelper
//     */
//    public void setLinHelper() {
//        new LinearSnapHelper().attachToRecyclerView(getRecyclerView());
//    }
//
//
//    /**
//     * 只搭配水平滾動
//     */
//    public void setPagerSnapHelper() {
//        if (getRecyclerView().getLayoutManager().canScrollHorizontally()) {
//            //            new PagerSnapHelper().attachToRecyclerView(getRecyclerView());
//
//            PagerSnapHelper p = new PagerSnapHelper();
//            //            p.findTargetSnapPosition(getRecyclerView().getLayoutManager(), 100, 0);
//
//            p.attachToRecyclerView(getRecyclerView());
//        }
//    }
//
//
//}
