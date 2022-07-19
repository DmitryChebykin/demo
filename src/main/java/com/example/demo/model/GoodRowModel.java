package com.example.demo.model;

import javafx.beans.property.SimpleStringProperty;

public class GoodRowModel {
    private SimpleStringProperty value = new SimpleStringProperty();

    public GoodRowModel(SimpleStringProperty value) {
        this.value = value;
    }

    public GoodRowModel(String s) {
        value.set(s);
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }
}
