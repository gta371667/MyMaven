package com.duck.maven.adpter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duck.maven.R;
import com.duck.maven.model.TestModel;
import com.duck.widget.MyAttrView;

import java.util.List;

public class TestAdapter extends BaseQuickAdapter<TestModel, BaseViewHolder> {

    public TestAdapter(@Nullable List<TestModel> data) {
        super(R.layout.adpter_string, data);
    }

    @Override protected void convert(BaseViewHolder holder, TestModel obj) {
        holder.addOnClickListener(R.id.myAttrRound);

        MyAttrView myAttrV = holder.getView(R.id.myAttrV);
        myAttrV.setIconDrawableTint(obj.isSelect() ? R.color.red : R.color.mtrl_textinput_default_box_stroke_color);
        myAttrV.setTextColor(obj.isSelect() ? R.color.red : R.color.mtrl_textinput_default_box_stroke_color);
        myAttrV.setIconDrawableLeft(obj.isSelect() ? R.drawable.baseline_favorite_black_48 : R.drawable.baseline_favorite_white_48);
    }

}
