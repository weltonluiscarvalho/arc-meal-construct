package com.arch.MealMonitor.controller;

import com.arch.MealMonitor.records.MealRegisterRecord;
import com.arch.MealMonitor.services.MealRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestController
@RequestMapping("/register")
public class MealRegisterController {

    @Autowired
    private MealRegisterService service;

    @PostMapping(value = "/meal")
    public ResponseEntity<String> postMealRegister(
            @RequestParam("image") MultipartFile image,
            @RequestParam("mealName") String mealName,
            @RequestParam("mealMass") Double mealMass,
            @RequestParam("registerDateTime")String registerDateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime receivedDateTime = LocalDateTime.parse(registerDateTime, formatter);

        MealRegisterRecord mealRegister = new MealRegisterRecord(mealName, mealMass, receivedDateTime, image);

        if (!isRegisterValid(mealRegister)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid register");
        }

        service.processRegister(mealRegister, image);

        return ResponseEntity.ok("ok");
    }

    private static Boolean isRegisterValid(MealRegisterRecord mealRegisterRecord) {
        if (mealRegisterRecord.image().isEmpty() || !isImage(mealRegisterRecord.image())) {
            return Boolean.FALSE;
        }

        if (mealRegisterRecord.mealName().isEmpty()) {
            return Boolean.FALSE;
        }

        if (mealRegisterRecord.mass().isNaN()) {
            return Boolean.FALSE;
        }

        if (mealRegisterRecord.registerDate().isAfter(LocalDateTime.now())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private static boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/gif"));
    }

}
