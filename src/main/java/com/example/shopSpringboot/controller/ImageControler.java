package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.entity.ImageEntity;
import com.example.shopSpringboot.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class ImageControler {
    @Autowired
    ImageRepository imageRepository;
    @GetMapping(value = "/from")
    public String from(Model model) {
        model.addAttribute("action", "/upload");

        return "form";
    }


    @PostMapping(value = "/upload")
    public String saveImage(@RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {

            try {

                byte[] imageData = imageFile.getBytes();


                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setImage(imageData);


                imageRepository.save(imageEntity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "redirect:/form_image";
    }

    @GetMapping(value = "/form_image")
    public String show_Image(Model model) {
        List<ImageEntity> imageEntityList = imageRepository.findAll();
        List<String> imageDataList = new ArrayList<>();

        for (ImageEntity imageEntity : imageEntityList) {
            String imageData = Base64.getEncoder().encodeToString(imageEntity.getImage());
            imageDataList.add(imageData);
        }

        model.addAttribute("imageDataList", imageDataList);
        return "show";
    }
}
