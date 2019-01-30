package com.duck.maven.model;

public class TestModel {
    boolean isSelect;
    String name;

    public TestModel(boolean isSelect, String name) {
        this.isSelect = isSelect;
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
