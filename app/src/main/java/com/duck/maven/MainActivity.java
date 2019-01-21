package com.duck.maven;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> strings = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        for (int i = 0; i <= 100; i++) {
        //            strings.add(String.valueOf(i));
        //        }
        //
        //        mDuckListView.setAdapter(new TestAdapter(strings));
        //        mDuckListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //        mDuckListView.addDefaultItemDecoration();
        //
        //        mDuckListView.setAdapterOnItemChildClickListener((adapter, view, position) -> {
        //
        //            //            if (position + 1 < strings.size()) {
        //            //                mDuckListView.smoothScrollToPosition(position + 1);
        //            //            } else {
        //            mDuckListView.smoothScrollToPosition(position);
        //            //            }
        //        });
        //
        //        mDuckListView.setLinHelper();

    }
}
