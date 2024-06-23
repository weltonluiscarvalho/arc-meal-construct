package com.arch.MealMonitor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class MealRegister {

    @Id
    @GeneratedValue
    private UUID mealId;
    private String meal;
    private LocalDateTime registerDateTime;
    private Double mass;
    private String imageUrl;

    public MealRegister() {
    }

    public Double getMass() {
        return mass;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
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
