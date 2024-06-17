package com.arch.MealMonitor.repositories;

import com.arch.MealMonitor.entities.MealRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MealRegisterRepository extends JpaRepository<MealRegister, UUID> {
}
