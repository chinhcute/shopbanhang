package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.entity.ProductEntity;
import com.example.shopSpringboot.entity.ReviewEntity;
import com.example.shopSpringboot.repository.AccountRepository;
import com.example.shopSpringboot.repository.ProductRepository;
import com.example.shopSpringboot.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AccountRepository accountRepository;
    @RequestMapping(value = "/user/save_review/{id}", method = RequestMethod.POST)
    public String save_review(@PathVariable int id,
                             @Valid @ModelAttribute("review") ReviewEntity reviewEntity, BindingResult result
                              ){
        if (result.hasErrors()){
            return "detail";
        }
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email =authentication.toString();
        if (authentication instanceof UserDetails){
            email = ((UserDetails) authentication).getUsername();
        }
        AccountEntity account = accountRepository.findByEmailLike(email);
        Optional<ProductEntity> product = productRepository.findById(id);
        ProductEntity productEntity = product.get();


//        reviewEntity.setRating(rating);
//        reviewEntity.setComment(comment);

        ReviewEntity review = new ReviewEntity();
        review.setAccount(account);
        review.setProduct(productEntity);
        review.setComment(reviewEntity.getComment());
        review.setRating(reviewEntity.getRating());
        reviewRepository.save(review);
        String s = "redirect:/view_detail/" + id;
        return s;
    }
}
