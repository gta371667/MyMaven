package com.duck.maven;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.duck.maven.adpter.TestAdapter;
import com.duck.widget.DuckListView;
import com.duck.widget.DuckRecyclerView;
import com.duck.widget.MyAttrView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.contentView) LinearLayout contentView;
    @BindView(R.id.mDuckRecyclerView1) DuckRecyclerView mDuckRecyclerView1;
    @BindView(R.id.mDuckRecyclerView2) DuckListView mDuckRecyclerView2;

    List<String> strings = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        for (int i = 0; i <= 100; i++) {
            strings.add(String.valueOf(i));
        }

        TestAdapter testAdapter = new TestAdapter(strings);
        mDuckRecyclerView1.setAdapter(testAdapter);
        mDuckRecyclerView1.clearData();
        mDuckRecyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDuckRecyclerView1.setLoadingView(R.layout.adpter_string);
        mDuckRecyclerView1.showEmptyView(DuckRecyclerView.Loading);

        mDuckRecyclerView2.setAdapter(new TestAdapter(strings));
        mDuckRecyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


//        MyAttrView myAttrView = new MyAttrView(this);
//        myAttrView.setIconDrawableLeft(R.drawable.ic_arrow_left);
//        myAttrView.setText("asdasd");
//        myAttrView.setIconDrawableTint(R.color.red);
//        contentView.addView(myAttrView);
    }
}
