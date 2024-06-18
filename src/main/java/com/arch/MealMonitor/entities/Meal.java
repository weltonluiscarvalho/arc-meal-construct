package com.arch.MealMonitor.entities;

import java.util.Map;

public class Meal {

    private String name;

    private Map<Ingredient, Double> ingredients;


    public Meal(String name) {
        this.name = name;
    }

    public Meal(String name, Map<Ingredient, Double> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }


}
