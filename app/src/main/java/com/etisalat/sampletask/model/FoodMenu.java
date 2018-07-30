package com.etisalat.sampletask.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "menu")
public class FoodMenu {
    @ElementList(name = "item", inline = true)
    private List<Food> foods;

    public FoodMenu() {}

    public List<Food> getFoods() {
        return foods;
    }
}
