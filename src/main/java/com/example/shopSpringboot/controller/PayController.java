package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.Enum.CartStatus;
import com.example.shopSpringboot.Enum.PaymentType;
import com.example.shopSpringboot.Enum.ProductStatus;
import com.example.shopSpringboot.entity.*;
import com.example.shopSpringboot.repository.*;
import com.example.shopSpringboot.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class PayController {
    @Autowired
    CartItemsRepository cartItemsRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    Historyrepository historyrepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    PaymentHistoryRepository paymentHistoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/user/order", method = RequestMethod.GET)
    public  String order(Model model, @RequestParam  List<Integer> listId, HttpSession httpSession){



        List<CartItemEntity> itemEntityList = new ArrayList<>();

        for (int id : listId){
            Optional<CartItemEntity> cartItemEntity = cartItemsRepository.findById(id);
            CartItemEntity cartItem = cartItemEntity.get();
            itemEntityList.add(cartItem);

        }
        httpSession.setAttribute("itemEntityList",itemEntityList);


        return "redirect:/user/order_after";

    }



    @RequestMapping("/user/order_after")
    public String getAjaxViewPage(HttpServletRequest request, Model model) {

        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authentication.toString();
        if (authentication instanceof UserDetails) {
            email = ((UserDetails) authentication).getUsername();
        }
        List<BankEntity> bankEntityList = bankRepository.findByListBank(email);
        System.out.println("vaoooo");
        if (bankEntityList.isEmpty()) {
            System.out.println("vào điiiii");
            return "redirect:/user/add_bank";

        }
        List<CartItemEntity> itemEntityList = (List<CartItemEntity>) request.getSession().getAttribute("itemEntityList");

        List<Integer> integers = new ArrayList<>();

        for (CartItemEntity cartItemEntity : itemEntityList){
            if (cartItemEntity.getQuantity() > cartItemEntity.getProduct().getQuantity()){
//                String s = "redirect:/user/cart?error=true" +
//                return "redirect:/user/cart?error=true";
                integers.add(cartItemEntity.getProduct().getId());

            }
        }
        if (!integers.isEmpty()){
            String error =integers.stream().map(Object :: toString).collect(Collectors.joining(","));
            return "redirect:/user/cart?error=true&id=" + error;
        }


//        map_bank(model, bankEntityList);
        model.addAttribute("bankEntityList", bankEntityList);
        model.addAttribute("order", new OrderEntity());
        model.addAttribute("action", "/user/save-oder");
        return "order";
    }
    public void bankEntityList (Model model){
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authentication.toString();
        if (authentication instanceof UserDetails) {
            email = ((UserDetails) authentication).getUsername();
        }
        List<BankEntity> bankEntityList = bankRepository.findByListBank(email);
        model.addAttribute("bankEntityList",bankEntityList);
    }

    @RequestMapping(value = "/user/save-oder", method = RequestMethod.POST)
    public String saveOrder(@Valid @ModelAttribute("order") OrderEntity order, BindingResult result,
                            @RequestParam("bank") int bankId, HttpServletRequest request, Model model) throws MessagingException {

        if (result.hasErrors()) {
            bankEntityList(model);
            return "order";
        }

        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authentication.toString();
        if (authentication instanceof UserDetails){
            email = ((UserDetails) authentication).getUsername();
        }
        AccountEntity account = accountRepository.findByEmailLike(email);

        LocalDate now = LocalDate.now();
        order.setCreatedDate(now);
        order.setShippedDate(now.plusDays(3));
        order.setStatus(order.getPaymentType().equals(PaymentType.offline) ? ProductStatus.Unpaid : ProductStatus.Paid);
        order.setUser(account);
        orderRepository.save(order);

        List<CartItemEntity> itemEntityList = (List<CartItemEntity>) request.getSession().getAttribute("itemEntityList");


        double price = 0;
        if (PaymentType.online.equals(order.getPaymentType())){
            for (CartItemEntity cartItemEntity : itemEntityList){
                price += cartItemEntity.getTotalAllAmount();
                if (cartItemEntity.getQuantity() > cartItemEntity.getProduct().getQuantity()){
                    return "redirect:/user/cart?error=true";
                }
            }
        }

        String products = "";

        Optional<BankEntity> bank = bankRepository.findById(bankId);
        if (bank.get().getSurplus() >= price) {
            for (CartItemEntity cartItemEntity : itemEntityList){
                if (cartItemEntity.getQuantity() <= cartItemEntity.getProduct().getQuantity()){
                    if (PaymentType.online.equals(order.getPaymentType())){
                        // Trừ tiền từ tài khoản ngân hàng của người dùng
                        bank.get().setSurplus(bank.get().getSurplus() - cartItemEntity.getTotalAllAmount());
                        bankRepository.save(bank.get());

                        // Cộng tiền vào tài khoản ngân hàng của người bán
                        List<BankEntity> bankEntityList = bankRepository.findByListBank(cartItemEntity.getProduct().getAdmin().getEmail());
                        for (BankEntity bankEntity : bankEntityList){
                            bankEntity.setSurplus(bankEntity.getSurplus() + cartItemEntity.getTotalAllAmount());
                            bankRepository.save(bankEntity);

                            PaymentHistory paymentHistory = new PaymentHistory();
                            paymentHistory.setContent("Nhận tiền từ : " + bank.get().getName()+ "với số tiền là :" + cartItemEntity.getTotalAllAmount());
                            paymentHistory.setDay_trading(now);
                            paymentHistory.setMoney(cartItemEntity.getTotalAllAmount());
                            paymentHistory.setBank(bank.get());
                            paymentHistoryRepository.save(paymentHistory);
                            break;
                        }

                        // Lưu lịch sử giao dịch
                        PaymentHistory paymentHistory = new PaymentHistory();
                        paymentHistory.setContent("mua hàng với số tiền :" + cartItemEntity.getTotalAllAmount());
                        paymentHistory.setDay_trading(now);
                        paymentHistory.setMoney(cartItemEntity.getTotalAllAmount());
                        paymentHistory.setBank(bank.get());
                        paymentHistoryRepository.save(paymentHistory);
                    }

                    // Cập nhật số lượng sản phẩm
                    ProductEntity product  = cartItemEntity.getProduct();
                    product.setQuantity(cartItemEntity.getProduct().getQuantity() - cartItemEntity.getQuantity());
                    product.setSold(cartItemEntity.getProduct().getSold() + cartItemEntity.getQuantity());
                    productRepository.save(product);



                    Oder_detailEntity oderDetail = new Oder_detailEntity();
                    oderDetail.setProduct(cartItemEntity.getProduct());
                    oderDetail.setQuantity(cartItemEntity.getQuantity());
                    oderDetail.setTotalAllAmount(cartItemEntity.getTotalAllAmount());
                    oderDetail.setOrder(order);

                    orderDetailRepository.save(oderDetail);

                    History history = new History();
                    history.setOderDetail(oderDetail);
                    historyrepository.save(history);

                    products = products + "<li>" + cartItemEntity.getProduct().getName() + "</li>";







                } else {

                    return "redirect:/user/cart?error=true";
                }
            }
        } else {
            return "redirect:/user/account?error=true";
        }

        String link = "http://localhost:8080/user/order_list";
        String emailContent = "Bạn vừa đặt hàng thành công:<br/>" +
                "Danh sách sản phẩm: <br/>" +
                "<ol>" +
                products +
                "</ol>" +
                "Giá tiền: " + price + "<br/>" +
                "Xem đơn hàng: <a href='" + link + "'>" + link + "</a>";



        String subject = "Đặt hàng thành công";
        emailService.sendEmail(email, subject, emailContent);

        return "redirect:/user/delete_all";
    }

    @RequestMapping(value = "/user/delete_all")
    public String delete_all(HttpServletRequest request, HttpSession session){
        List<CartItemEntity> itemEntityList = (List<CartItemEntity>) request.getSession().getAttribute("itemEntityList");
        for (CartItemEntity cartItemEntity : itemEntityList){
            cartItemsRepository.deleteById(cartItemEntity.getId());
        }
       session.removeAttribute("itemEntityList");
        return "redirect:/user/cart?success=true";
    }


//    public void map_bank(Model model , List<BankEntity> bankEntityList){
//        Map<Integer, String> map = new HashMap<>();
//        for (BankEntity bank : bankEntityList){
//            map.put(bank.getId() , bank.getName());
//        }
//        model.addAttribute("mapBank",map);
//    }
}
