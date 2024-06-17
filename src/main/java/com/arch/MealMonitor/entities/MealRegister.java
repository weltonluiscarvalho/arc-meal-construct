package com.arch.MealMonitor.entities;

import java.time.LocalDateTime;
import java.util.Date;

public class MealRegister {

    private Meal meal;
    private LocalDateTime registerDateTime;
    private Double mass;
    private String imageUrl;

    public MealRegister() {
    }

    public Double getMass() {
        return mass;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public LocalDateTime getRegisterDate() {
        return registerDateTime;
    }

    public void setRegisterDate(LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.imageUrl = imageUrl;
    }
}
