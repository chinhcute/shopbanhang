package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.Enum.CartStatus;
import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.entity.CartEntity;
import com.example.shopSpringboot.entity.CartItemEntity;
import com.example.shopSpringboot.entity.ProductEntity;
import com.example.shopSpringboot.repository.AccountRepository;
import com.example.shopSpringboot.repository.CartItemsRepository;
import com.example.shopSpringboot.repository.CartRepository;
import com.example.shopSpringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemsRepository cartItemsRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProductRepository productRepository;
    @RequestMapping(value = "/user/cart")
    public  String cart(Model model ,@RequestParam(value = "error", required = false) Boolean error,
                        @RequestParam(value = "success", required = false) Boolean success,
                        @RequestParam(value = "id", required = false) String productIds){
        Object authen = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authen.toString();
        if (authen instanceof UserDetails){
            email = ((UserDetails) authen).getUsername();
        }
        CartEntity cart = cartRepository.findByCart(email);
        if (cart == null){
            CartEntity cartEntity = new CartEntity();
          AccountEntity accountEntity =  accountRepository.findByEmailLike(email);
          cartEntity.setUser(accountEntity);
          cartRepository.save(cartEntity);
          cart = cartEntity;
        }
        model.addAttribute("cart", cart);
        if (productIds != null && error && error != null ) {
            List<Integer> errorProductIdList = Arrays.stream(productIds.split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            List<ProductEntity> productEntityList = new ArrayList<>();
            for (int id : errorProductIdList){
               Optional<ProductEntity> product =  productRepository.findById(id);
               productEntityList.add(product.get());
            }
            model.addAttribute("productEntityList", productEntityList);
            model.addAttribute("showErrorModal", true);
        }
        if (success!= null && success){
            model.addAttribute("showSuccess", true);
        }

        return "cart";
    }
    @RequestMapping(value = "/user/carts/{id}")
    public String save_cart(@PathVariable int id, Model model) {
        Optional<ProductEntity> product = productRepository.findById(id);
        ProductEntity productEntity = product.get();
        Object authen = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authen.toString();
        if (authen instanceof UserDetails){
            email = ((UserDetails) authen).getUsername();
        }

        CartEntity cart = cartRepository.findByCart(email);
        if (cart == null) {
            CartEntity cartEntity = new CartEntity();
            AccountEntity accountEntity = accountRepository.findByEmailLike(email);
            cartEntity.setUser(accountEntity);
            cartRepository.save(cartEntity);
            cart = cartEntity;
        }

        boolean check = false;

        for (CartItemEntity cartItem : cart.getCartItemEntityList()){
            if (cartItem.getProduct().getId() == id){
                cartItem.setQuantity(cartItem.getQuantity() +1);
                double total_All_amount = cartItem.getProduct().getTotalAmount() * cartItem.getQuantity();
                cartItem.setTotalAllAmount(total_All_amount);

                cartItemsRepository.save(cartItem);
                check = true;
                break;
            }
        }

        if (!check) {
            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setCart(cart);
            cartItemEntity.setProduct(productEntity);
            cartItemEntity.setQuantity(1);

            double total_All_amount = cartItemEntity.getProduct().getTotalAmount() * cartItemEntity.getQuantity();
            cartItemEntity.setTotalAllAmount(total_All_amount);

            cartItemsRepository.save(cartItemEntity);
            System.out.println("ok");
        }

        return "redirect:/user/cart";
    }

    @RequestMapping(value = "/user/delete_cart/{id}")
    public String delete_cart(@PathVariable int id){
        cartItemsRepository.deleteById(id);
        return "redirect:/user/cart";
    }


    @RequestMapping(value = "/total", method = RequestMethod.GET)
    @ResponseBody
    public double total(@RequestParam("selectedProducts") List<Integer> selectedProducts){
        System.out.println("vào rồi");
        double totalAmount = 0;
        for (Integer id : selectedProducts) {
            Optional<CartItemEntity> cartItemEntity = cartItemsRepository.findById(id);
            if (cartItemEntity.isPresent()) {
                totalAmount += cartItemEntity.get().getTotalAllAmount();
            }
        }
        return totalAmount;

    }
    @RequestMapping(value = "/updateQuantity", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateQuantity(@RequestParam("cartItemId") int cartItemId, @RequestParam("quantity") int quantity) {
        Optional<CartItemEntity> cartItemEntityOptional = cartItemsRepository.findById(cartItemId);

        if (cartItemEntityOptional.isPresent()) {
            CartItemEntity cartItemEntity = cartItemEntityOptional.get();
            cartItemEntity.setQuantity(quantity);
            cartItemEntity.setTotalAllAmount(cartItemEntity.getProduct().getTotalAmount() * quantity);
            cartItemsRepository.save(cartItemEntity);

            double totalAmount = cartItemEntity.getTotalAllAmount();

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("totalAmount", totalAmount);
            return responseData;
        } else {
            return Collections.emptyMap();
        }
    }




}
