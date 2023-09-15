//package com.example.shopSpringboot;
//
//import com.example.shopSpringboot.Enum.Role;
//import com.example.shopSpringboot.entity.BankEntity;
//import com.example.shopSpringboot.entity.OrderEntity;
//import com.example.shopSpringboot.entity.ProductEntity;
//import com.example.shopSpringboot.entity.RoleEntity;
//import com.example.shopSpringboot.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//@SpringBootApplication
//public class main implements CommandLineRunner {
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private BankRepository bankRepository;
//    @Autowired
//    private AccountRepository accountRepository;
//    @Autowired
//    private CartItemsRepository cartItemsRepository;
//    @Autowired
//    ProductRepository productRepository;
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        boolean isMatch = encoder.matches("123", "$2a$10$jnhjQBCqqMEC1rXWFx7jVOAoocbRinfDwmFztgTCFAGX/prcqAKDC");
//        System.out.println(isMatch);
//
////        String password = "123";
////        String encodedPassword = encoder.encode(password);
////        System.out.println("Mật khẩu gốc: " + password);
////        System.out.println("Mật khẩu mã hóa: " + encodedPassword);
////        System.out.println("heloo");
////        Random random = new Random();
////        int number = random.nextInt(1000);
////        String so = String.valueOf(number);
////        System.out.println("mã xác thực là " + so);
////        Set<RoleEntity> roleEntitySet =roleRepository.findByRole(Role.ROLE_USER.name());
////        System.out.println(roleEntitySet);
//
////        List<BankEntity> bankEntityList = bankRepository.findByListBank("chinh20043008@gmail.com");
////        for (BankEntity bank : bankEntityList){
////            System.out.println(bank.getName());
//
////        String s = "iphon";
////        String s1 = "";
////        List<ProductEntity> productEntityList = productRepository.findByNameLikeOrDescriptionLike(s, s);
////        for (ProductEntity product :productEntityList){
////            System.out.println(product.getName());
////        }
////        List<OrderEntity> orderEntityList = orderRepository.admin_order("nlam07485@gmail.com");
////        for (OrderEntity order : orderEntityList){
////            System.out.println(order.getId());
////        }
////        String encodedPasswords = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123");
////        System.out.println(encodedPasswords);
//
//
//
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(main.class, args);
//    }
//}
