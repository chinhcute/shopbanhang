package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;

@Controller
public class QrController {
    @Autowired
    QrService qrService;

    @RequestMapping (value = "/pr")
    public String qr(Model model){
        String text = "https://docs.google.com/document/d/1-q5G2sbkKPBWJH3HfU1u4ljSqewPP9qD/edit?usp=sharing&ouid=114267454989157545758&rtpof=true&sd=true";

        byte[] bytes =qrService.generateQRCode(text);


        String src = Base64.getEncoder().encodeToString(bytes);
        model.addAttribute("src", src);
        return "qr";


    }
}
