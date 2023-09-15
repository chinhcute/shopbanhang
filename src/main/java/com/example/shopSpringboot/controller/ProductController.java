package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.entity.*;
import com.example.shopSpringboot.repository.*;
import com.example.shopSpringboot.service.ProductService;
import com.example.shopSpringboot.service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
public class ProductController {
    @Autowired
    BankRepository bankRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    QrService qrService;
    @RequestMapping(value = "/admin/add_product", method = RequestMethod.GET)
    public String add_product(Model model){
        Object authen = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authen.toString();
        if (authen instanceof UserDetails){
            email = ((UserDetails) authen).getUsername();
        }

         List<BankEntity> bankEntityList = bankRepository.findByListBank(email);
        if (bankEntityList.isEmpty()){
            return "redirect:/user/add_bank";
        }
        model.addAttribute("product",new ProductEntity());
        model.addAttribute("action", "/admin/add_products");
        model.addAttribute("name","Thêm sản phẩm");
        categorys(model);
        return "product";
    }

    @RequestMapping(value = "/admin/add_products", method = RequestMethod.POST)
    @Transactional
    public String save_product(@RequestParam("imageFile") MultipartFile imageFile,
                               @Valid @ModelAttribute("product") ProductEntity products, BindingResult result,Model model
                              ) {
        if (products.getCategory().getId() == 0) {
            categorys(model);
            result.rejectValue("category.id", "category.id", "Vui lòng chọn danh mục.");
            return "product";
        }
        if (result.hasErrors()){
            categorys(model);
            return "product";
        }

        if (imageFile.isEmpty()) {
            categorys(model);
            model.addAttribute("error" , " vui lòng chọn ảnh sản phẩm");
            return "product";
        }

        Object authen = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authen.toString();
        if (authen instanceof UserDetails){
            email = ((UserDetails) authen).getUsername();
        }

        AccountEntity account = accountRepository.findByEmailLike(email);

        ProductEntity productEntity = productRepository.findByNameAndAdmin_Email(products.getName(), account.getEmail());
        if (productEntity != null) {

            productEntity.setQuantity(productEntity.getQuantity() + products.getQuantity());
            productRepository.save(productEntity);
            return "redirect:/admin/product_view?error=true";
        } else {

            ImageEntity images = new ImageEntity();
            try {
                byte[] bytes = imageFile.getBytes();
                images.setImage(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            imageRepository.save(images);
            products.setImage(images);
            products.setAdmin(account);
            products.setSold(0);

            double price_after_discount = products.getPrice() - (products.getPrice() / 100 *products.getDiscount());

            products.setTotalAmount(price_after_discount);

            String text = "Tên sản phẩm : "+ products.getName()+" \n"+
                    "Email :"+email+" \n" +
                    "Gia gốc :"+products.getPrice()+" \n" +
                    "giảm giá : "+products.getDiscount() + "% \n"+
                    "Giá :" + price_after_discount;
            byte[] bytes = qrService.generateQRCode(text);
            products.setQr(bytes);


            productRepository.save(products);
        }

        return "redirect:/admin/product_view?add_product=true";
    }


    @RequestMapping(value = "/admin/product_view")
    public String view_product(@RequestParam(defaultValue = "0") int page,
                               Model model, @RequestParam(value = "error", required = false) Boolean error,
                               @RequestParam(value = "success", required = false) Boolean success,
                                @RequestParam(value = "add_product", required = false) Boolean add_product){
        Object authen = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authen.toString();
        if (authen instanceof UserDetails){
            email = ((UserDetails) authen).getUsername();
        }
        Pageable pageable = PageRequest.of(page, 10);

        Page<ProductEntity> productEntityPage = productRepository.findByAdmin_Email(email,pageable);
        List<ProductEntity> productEntityList = productEntityPage.getContent();
        if (error != null && error){
            model.addAttribute("error_product", true);
        }
        if (success != null && success){
            model.addAttribute("success_product", true);
        }
        if (add_product != null && add_product){
            model.addAttribute("add_product", true);
        }
        model.addAttribute("productEntityPage",productEntityPage);
        model.addAttribute("products",productEntityList);
        return "product_view";

    }
    @RequestMapping(value = "admin/edit_product/{id}", method = RequestMethod.GET)
    public String edit_product(@PathVariable int id, Model model){
        ProductEntity product = productRepository.getById(id);
        model.addAttribute("product",product);

        model.addAttribute("action", "/admin/edit_product/" + id);
        model.addAttribute("name", "edit product");
        categorys(model);
        return "product";
    }


    @RequestMapping(value = "/admin/edit_product/{id}", method = RequestMethod.POST)
    public String save_edit(@ModelAttribute ProductEntity products,
                            @RequestParam("imageFile") MultipartFile imageFile,
                            @PathVariable int id) {
        productService.save_edits(products, imageFile, id);
        return "redirect:/admin/product_view?success=true";
    }
    @RequestMapping(value = "admin/delete_product/{id}")
    public String delete_product(@PathVariable int id){
        productRepository.deleteById(id);
        return  "redirect:/admin/product_view";
    }




    public void categorys(Model model){
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        Map<Integer, String> map = new HashMap<>();
        for (CategoryEntity category : categoryEntityList){
            map.put(category.getId() , category.getName());
        }
        model.addAttribute("category_list", map);

    }


}
