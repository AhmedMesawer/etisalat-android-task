package com.etisalat.sampletask.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class Food {

    @Element(name = "id")
    private int id;
    @Element(name = "name")
    private String name;
    @Element(name = "cost")
    private int cost;
    @Element(name = "description")
    private String description;

    public Food() {}

    public Food(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}
