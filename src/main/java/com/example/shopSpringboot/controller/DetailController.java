package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.entity.ProductEntity;
import com.example.shopSpringboot.entity.ReviewEntity;
import com.example.shopSpringboot.repository.ProductRepository;
import com.example.shopSpringboot.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Controller
public class DetailController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProductRepository productRepository;
    @RequestMapping(value = "/view_detail/{id}")
    public String view(@PathVariable int id, Model model) {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email =authentication.toString();
        if (authentication instanceof UserDetails){
            email = ((UserDetails) authentication).getUsername();
        }
        model.addAttribute("email", email);
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        ProductEntity product = productEntity.get();


        byte [] bytes = product.getImage().getImage();
        String src = Base64.getEncoder().encodeToString(bytes);
        byte [] qrs = product.getQr();
        String qr = Base64.getEncoder().encodeToString(qrs);

        String action = "/user/save_review/" + product.getId();

        model.addAttribute("product",product);
        model.addAttribute("qr", qr);

        model.addAttribute("src",src);
        model.addAttribute("action",action);
        model.addAttribute("review", new ReviewEntity());

        double rating = 0;
        for (ReviewEntity reviewEntity : product.getReviewEntityList()){
            rating += reviewEntity.getRating();
        }
        rating = rating / product.getReviewEntityList().size();
        double averageRating = product.getReviewEntityList().size() > 0  ? Math.round(rating * 10)/10 : 0;
        model.addAttribute("rating",averageRating);

        return "detail";
    }

}
