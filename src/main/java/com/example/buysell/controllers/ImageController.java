package com.example.buysell.controllers;
/*
Контроллер получает фотографию из БД, преобразует байты в саму фотографию,
и раздает ее
 */


import com.example.buysell.models.Image;
import com.example.buysell.repos.ImageRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController //представление возвращать не будем, поэтому rest
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepo imageRepo;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image image = imageRepo.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType())) //преобразование типа изображения в константу медиатайп
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
