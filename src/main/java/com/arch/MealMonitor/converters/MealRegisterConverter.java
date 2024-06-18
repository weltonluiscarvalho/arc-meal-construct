package com.arch.MealMonitor.converters;

import com.arch.MealMonitor.entities.Meal;
import com.arch.MealMonitor.entities.MealRegister;
import com.arch.MealMonitor.records.MealRegisterRecord;
import org.springframework.stereotype.Service;

@Service
public class MealRegisterConverter {

    public MealRegister recordToEntity(MealRegisterRecord record, String imageUrl) {
        MealRegister entity = new MealRegister();

        entity.setMeal(new Meal(record.mealName()));
        entity.setMass(record.mass());
        entity.setRegisterDate(record.registerDate());
        entity.setImageUrl(imageUrl);

        return entity;
    }
}
