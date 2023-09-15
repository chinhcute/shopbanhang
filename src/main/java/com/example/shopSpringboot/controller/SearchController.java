package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.entity.*;
import com.example.shopSpringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
public class SearchController {
    @Autowired
    SearchRepository searchRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/search")
    @ResponseBody
    public List<String> search(@RequestParam ("search") String search){
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email =authentication.toString();
        if (authentication instanceof UserDetails){
            email = ((UserDetails) authentication).getUsername();
        }
        AccountEntity account = accountRepository.findByEmailLike(email);
        List<String> stringList = new ArrayList<>();

        if (search.length() == 0){

            List<SearchEntity> searchEntityList = searchRepository.findByAccount_email(email);
            for (SearchEntity searchEntity :searchEntityList){
                stringList.add(searchEntity.getSearch());
            }
        }else {
            List<ProductEntity> productEntityList = productRepository.findByNameContaining(search);
            for (ProductEntity product :productEntityList){
                stringList.add(product.getName());
            }
        }
        return stringList;
    }

    @RequestMapping(value = "/search/product")
    public String search_product(@RequestParam String search, Model model){
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email =authentication.toString();
        if (authentication instanceof UserDetails){
            email = ((UserDetails) authentication).getUsername();
        }
        model.addAttribute("email", email);
        AccountEntity  account = accountRepository.findByEmailLike(email);
        model.addAttribute("email", email);
        List<ProductEntity> productEntityList = productRepository.findByNameLikeOrDescriptionLike(search, search);
        model.addAttribute("productEntityList",productEntityList);


        List<String> objectList = categoryRepository.findCategoryNamesOrderByTotalSoldDesc();
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        for (String category : objectList){
            CategoryEntity categoryEntity = categoryRepository.findByName(category);
            categoryEntityList.add(categoryEntity);
        }

        model.addAttribute("category_list", categoryEntityList);

        Map<Integer, String> map = new HashMap<>();

        Map<Integer, Double> doubleMap = new HashMap<>();

        for (CategoryEntity category : categoryEntityList) {
            for (ProductEntity product : category.getProductEntityList()) {
                byte[] imageBytes = product.getImage().getImage();
                String imageData = Base64.getEncoder().encodeToString(imageBytes);
                map.put(product.getId(), imageData);

                double rating = 0;
                for (ReviewEntity reviewEntity : product.getReviewEntityList()){
                    rating += reviewEntity.getRating();
                }
                rating = rating / product.getReviewEntityList().size();
                double averageRating = product.getReviewEntityList().size() > 0  ? Math.round(rating * 10)/10 : 0;
                doubleMap.put(product.getId(), averageRating);
            }
        }
        model.addAttribute("rating",doubleMap);
        model.addAttribute("map", map);
        List<ProductEntity> productEntityLists = productRepository.findByProductOderByLimit8();
        model.addAttribute("productEntityLists",productEntityLists);

        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setSearch(search);
        searchEntity.setAccount(account);
        searchEntity.setCreatedDate(LocalDate.now());
        searchRepository.save(searchEntity);
        return "home_search";
    }

    @RequestMapping(value = "/search/{name}")
    public String search_category(@PathVariable String name, Model model){
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email =authentication.toString();
        if (authentication instanceof UserDetails){
            email = ((UserDetails) authentication).getUsername();
        }
        model.addAttribute("email", email);
        AccountEntity  account = accountRepository.findByEmailLike(email);

        CategoryEntity categorys = categoryRepository.findByName(name);
        List<ProductEntity> productEntityList = categorys.getProductEntityList();
        model.addAttribute("productEntityList",productEntityList);

        List<String> objectList = categoryRepository.findCategoryNamesOrderByTotalSoldDesc();
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        for (String category : objectList){
            CategoryEntity categoryEntity = categoryRepository.findByName(category);
            categoryEntityList.add(categoryEntity);
        }

        model.addAttribute("category_list", categoryEntityList);

        Map<Integer, String> map = new HashMap<>();

        Map<Integer, Double> doubleMap = new HashMap<>();

        for (CategoryEntity category : categoryEntityList) {
            for (ProductEntity product : category.getProductEntityList()) {
                byte[] imageBytes = product.getImage().getImage();
                String imageData = Base64.getEncoder().encodeToString(imageBytes);
                map.put(product.getId(), imageData);

                double rating = 0;
                for (ReviewEntity reviewEntity : product.getReviewEntityList()){
                    rating += reviewEntity.getRating();
                }
                rating = rating / product.getReviewEntityList().size();
                double averageRating = product.getReviewEntityList().size() > 0  ? Math.round(rating * 10)/10 : 0;
                doubleMap.put(product.getId(), averageRating);
            }
        }
        model.addAttribute("rating",doubleMap);
        model.addAttribute("map", map);
        List<ProductEntity> productEntityLists = productRepository.findByProductOderByLimit8();
        model.addAttribute("productEntityLists",productEntityLists);

        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setSearch(name);
        searchEntity.setAccount(account);
        searchEntity.setCreatedDate(LocalDate.now());
        searchRepository.save(searchEntity);
        return "home_search";
    }
    @RequestMapping(value = "/search/price")
    public String search_price(@RequestParam int  stat, @RequestParam int end, Model model ){
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email =authentication.toString();
        if (authentication instanceof UserDetails){
            email = ((UserDetails) authentication).getUsername();
        }
        model.addAttribute("email", email);
        List<ProductEntity> productEntityList = productRepository.list_Stat_end(stat, end);
        model.addAttribute("productEntityList",productEntityList);
        List<String> objectList = categoryRepository.findCategoryNamesOrderByTotalSoldDesc();
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        for (String category : objectList){
            CategoryEntity categoryEntity = categoryRepository.findByName(category);
            categoryEntityList.add(categoryEntity);
        }

        model.addAttribute("category_list", categoryEntityList);

        Map<Integer, String> map = new HashMap<>();

        Map<Integer, Double> doubleMap = new HashMap<>();

        for (CategoryEntity category : categoryEntityList) {
            for (ProductEntity product : category.getProductEntityList()) {
                byte[] imageBytes = product.getImage().getImage();
                String imageData = Base64.getEncoder().encodeToString(imageBytes);
                map.put(product.getId(), imageData);

                double rating = 0;
                for (ReviewEntity reviewEntity : product.getReviewEntityList()){
                    rating += reviewEntity.getRating();
                }
                rating = rating / product.getReviewEntityList().size();
                double averageRating = product.getReviewEntityList().size() > 0  ? Math.round(rating * 10)/10 : 0;
                doubleMap.put(product.getId(), averageRating);
            }
        }
        model.addAttribute("rating",doubleMap);
        model.addAttribute("map", map);
        List<ProductEntity> productEntityLists = productRepository.findByProductOderByLimit8();
        model.addAttribute("productEntityLists",productEntityLists);


        return "home_search";

    }
    @RequestMapping(value = "/search/rating/{number}")
    public String search_rating(@PathVariable int number, Model model ){
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email =authentication.toString();
        if (authentication instanceof UserDetails){
            email = ((UserDetails) authentication).getUsername();
        }
        model.addAttribute("email", email);
        List<ProductEntity> productEntityList = productRepository.findBYRating(number);
        model.addAttribute("productEntityList",productEntityList);
        List<String> objectList = categoryRepository.findCategoryNamesOrderByTotalSoldDesc();
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        for (String category : objectList){
            CategoryEntity categoryEntity = categoryRepository.findByName(category);
            categoryEntityList.add(categoryEntity);
        }

        model.addAttribute("category_list", categoryEntityList);

        Map<Integer, String> map = new HashMap<>();

        Map<Integer, Double> doubleMap = new HashMap<>();

        for (CategoryEntity category : categoryEntityList) {
            for (ProductEntity product : category.getProductEntityList()) {
                byte[] imageBytes = product.getImage().getImage();
                String imageData = Base64.getEncoder().encodeToString(imageBytes);
                map.put(product.getId(), imageData);

                double rating = 0;
                for (ReviewEntity reviewEntity : product.getReviewEntityList()){
                    rating += reviewEntity.getRating();
                }
                rating = rating / product.getReviewEntityList().size();
                double averageRating = product.getReviewEntityList().size() > 0  ? Math.round(rating * 10)/10 : 0;
                doubleMap.put(product.getId(), averageRating);
            }
        }
        model.addAttribute("rating",doubleMap);
        model.addAttribute("map", map);
        List<ProductEntity> productEntityLists = productRepository.findByProductOderByLimit8();
        model.addAttribute("productEntityLists",productEntityLists);


        return "home_search";

    }

    @RequestMapping(value = "/admin/search_order", method =  RequestMethod.GET)
    public  String search_admin_order(@RequestParam String key,@RequestParam(defaultValue = "0") int page, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.toString();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        }
        Pageable pageable = PageRequest.of(page, 10);

        Page<OrderEntity> orderPage = orderRepository.list_search_admin_order(email, key, key, key, pageable);
        List<OrderEntity> orderEntityList = orderPage.getContent();
        model.addAttribute("orderEntityPage", orderPage);
        model.addAttribute("orderEntityList",orderEntityList);
        model.addAttribute("currentPage", page);
        return "admin";

    }



}
