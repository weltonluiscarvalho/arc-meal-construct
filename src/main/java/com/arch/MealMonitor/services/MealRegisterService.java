package com.arch.MealMonitor.services;

import com.arch.MealMonitor.converters.MealRegisterConverter;
import com.arch.MealMonitor.entities.MealRegister;
import com.arch.MealMonitor.records.MealRegisterRecord;
import com.arch.MealMonitor.repositories.MealRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class MealRegisterService {

    @Value("${application.images.directory}")
    private String UPLOAD_DIR;

    @Autowired
    private MealRegisterRepository repository;

    private MealRegisterConverter converter;

    public MealRegisterService(){
    }

    public void processRegister(MealRegisterRecord mealRegisterRecord, MultipartFile image) {

        MealRegister mealRegister = converter.recordToEntity(mealRegisterRecord, imageUrl(mealRegisterRecord, image));

        try {
            saveImage(image);
        } catch (IOException e) {
        }

        repository.save(mealRegister);
    }

    private String imageUrl(MealRegisterRecord mealRegisterRecord, MultipartFile image) {

        String diet = "diet0123"; //TODO retrieve the actual diet name in database
        String dayOfWeek = mealRegisterRecord.registerDate().getDayOfWeek().toString();
        int hour = mealRegisterRecord.registerDate().getHour();
        int minute = mealRegisterRecord.registerDate().getMinute();
        String fileExtension = Objects.requireNonNull(image.getOriginalFilename()).substring(image.getOriginalFilename().lastIndexOf(".") + 1);
        return UPLOAD_DIR + "/" + diet + "-" + dayOfWeek + "-" + hour + "-" + minute + "." + fileExtension;
    }

    public Boolean isPayloadValid(MealRegisterRecord mealRegisterRecord, MultipartFile image) {

        if (image.isEmpty() || !isImage(image)) {
            return Boolean.FALSE;
        }

        if (Objects.isNull(mealRegisterRecord)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/gif"));
    }

    private void saveImage(MultipartFile file) throws IOException {

        String originalFileName = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString();

        if (!Files.exists(Paths.get(UPLOAD_DIR))) {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        }
        Path filePath = Paths.get(UPLOAD_DIR + originalFileName);
        Files.write(filePath, file.getBytes());
    }
}
