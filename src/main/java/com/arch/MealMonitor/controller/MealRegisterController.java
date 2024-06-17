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

@RestController
@RequestMapping("/register")
public class MealRegisterController {

    @Autowired
    private MealRegisterService service;

    @PostMapping(value = "/meal")
    public ResponseEntity<String> postMealRegister(
            @RequestParam("image") MultipartFile imagem,
            @RequestParam("mealName") String mealName,
            @RequestParam("mealMass") Double mealMass,
            @RequestParam("registerDateTime")String registerDateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime receivedDateTime = LocalDateTime.parse(registerDateTime, formatter);

        MealRegisterRecord mealRegister = new MealRegisterRecord(mealName, mealMass, receivedDateTime);

        if (!service.isPayloadValid(mealRegisterRecord, imagem)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payload inválido");
        }

        service.processRegister(mealRegisterRecord, imagem);

        return ResponseEntity.ok("Arquivo enviado e salvo com sucesso!");
    }

}
