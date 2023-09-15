package com.example.shopSpringboot.service;

import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.entity.CategoryEntity;
import com.example.shopSpringboot.entity.ImageEntity;
import com.example.shopSpringboot.entity.ProductEntity;
import com.example.shopSpringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;

@Service
public class ProductService {
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
    QrService qrService;
    @Transactional
    public void save_products(ProductEntity products, AccountEntity account, MultipartFile images) {
        ImageEntity image = new ImageEntity();
        try {
            image.setImage(images.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageRepository.save(image);

        products.setImage(image);
        products.setAdmin(account);

        productRepository.save(products);
    }

    public void save_edits(ProductEntity products, MultipartFile file, int id) {
        ProductEntity product = productRepository.getById(id);
        ImageEntity oldImage = product.getImage();

        if (!file.isEmpty()) {
            ImageEntity image = new ImageEntity();
            try {
                byte[] bytes = file.getBytes();
                image.setImage(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



            if (oldImage != null) {
                imageRepository.delete(oldImage);
            }

            imageRepository.save(image);
            product.setImage(image);
        }
        else {
            product.setImage(oldImage);
        }

        double price_after_discount = products.getPrice() - (products.getPrice() / 100 *products.getDiscount());

        product.setTotalAmount(price_after_discount);

        product.setName(products.getName());
        product.setDescription(products.getDescription());
        product.setPrice(products.getPrice());
        product.setDiscount(products.getDiscount());
        product.setQuantity(products.getQuantity());
        product.setCategory(products.getCategory());

        String text = "Tên sản phẩm : "+ products.getName()+" \n"+
                "Email :"+product.getAdmin().getEmail()+" \n" +
                "Gia gốc :"+products.getPrice()+" \n" +
                "giảm giá : "+products.getDiscount() + "% \n"+
                "Giá :" + price_after_discount;
        byte[] bytes = qrService.generateQRCode(text);
        product.setQr(bytes);

        productRepository.save(product);
    }
}
