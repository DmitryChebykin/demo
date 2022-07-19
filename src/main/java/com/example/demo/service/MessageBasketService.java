package com.example.demo.service;

import com.example.demo.model.GoodRowModel;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
@Getter
public class MessageBasketService {
    private final List<String> stringList = new CopyOnWriteArrayList<>();

    private final List<GoodRowModel> goodRowModelList = new CopyOnWriteArrayList<>();

    public void setStringList(List<String> stringList) {
        this.stringList.clear();
        this.stringList.addAll(stringList);
        goodRowModelList.clear();
        goodRowModelList.addAll(stringList.stream()
                .map(e -> new GoodRowModel(new SimpleStringProperty(e))).collect(Collectors.toList()));
    }
}
