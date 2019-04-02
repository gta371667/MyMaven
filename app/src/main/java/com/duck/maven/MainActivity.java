package com.duck.maven;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duck.maven.adpter.TestAdapter;
import com.duck.maven.model.TestModel;
import com.duck.widget.DuckListView;
import com.duck.widget.HeaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mDuckRecyclerView2) DuckListView mDuckRecyclerView2;
    @BindView(R.id.header) HeaderView header;

    List<TestModel> strings = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        for (int i = 0; i <= 100; i++) {
            if (i == 0) {
                strings.add(new TestModel(true, String.valueOf(i)));
            } else {
                strings.add(new TestModel(false, String.valueOf(i)));
            }
        }

        mDuckRecyclerView2.setAdapter(new TestAdapter(strings));
        mDuckRecyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mDuckRecyclerView2.setAdapterOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TestModel model = (TestModel) adapter.getItem(position);
                model.setSelect(!model.isSelect());
                adapter.notifyItemChanged(position);
            }
        });

        mDuckRecyclerView2.setOnSwipeRefreshListener(() -> {
            mDuckRecyclerView2.showEmptyView(DuckListView.Loading);
            initModel();
            mDuckRecyclerView2.loadMoreEnd();
        });

    }

    private List<TestModel> initModel() {
        strings.clear();
        for (int i = 0; i <= 100; i++) {
            if (i == 0) {
                strings.add(new TestModel(true, String.valueOf(i)));
            } else {
                strings.add(new TestModel(false, String.valueOf(i)));
            }
        }
        return strings;
    }

}
