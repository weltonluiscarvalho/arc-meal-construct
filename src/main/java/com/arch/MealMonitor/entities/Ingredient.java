package com.arch.MealMonitor.entities;

import java.util.Date;
import java.util.Map;

public class Ingredient {

    private String name;

    private Map<Nutrient, Double> NutritionalFacts;

    private Date lastUpdatedDate;
}
