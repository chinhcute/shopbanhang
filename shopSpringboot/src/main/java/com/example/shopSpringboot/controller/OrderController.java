package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.Enum.PaymentType;
import com.example.shopSpringboot.Enum.ProductStatus;
import com.example.shopSpringboot.entity.*;
import com.example.shopSpringboot.repository.BankRepository;
import com.example.shopSpringboot.repository.OrderRepository;
import com.example.shopSpringboot.repository.PaymentHistoryRepository;
import com.example.shopSpringboot.repository.ProductRepository;
import com.example.shopSpringboot.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    PaymentHistoryRepository paymentHistoryRepository;
    @RequestMapping(value = "/user/order_list")
    public String listOrder(@RequestParam(name = "status", required = false) String status, Model model, @RequestParam(value = "success", required = false) Boolean success) {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authentication.toString();
        if (authentication instanceof UserDetails) {
            email = ((UserDetails) authentication).getUsername();
        }

        List<OrderEntity> orderEntityList;

        if ("Completed".equals(status)) {
            orderEntityList = orderRepository.findCompletedOrders(email);
        } else if ("Unpaid".equals(status)) {
            orderEntityList = orderRepository.findUnpaidOrders(email);
        } else if ("Cancel".equals(status)) {
            orderEntityList = orderRepository.findCancelledOrders(email);
        } else if ("Paid".equals(status)) {
            orderEntityList = orderRepository.findPaidOrders(email);
        } else {
            orderEntityList = orderRepository.OderList(email);
        }

        model.addAttribute("orderEntityList", orderEntityList);

        if (success != null && success) {
            model.addAttribute("order_success", true);
        }

        return "order_list";
    }



    @RequestMapping(value = "/user/delete_orders/{id}")
    public String delete_order(@PathVariable int id, Model model){
        Optional<OrderEntity> order = orderRepository.findById(id);
        OrderEntity orderEntity = order.get();
        double price = 0;
        if (orderEntity != null){
            if (orderEntity.getPaymentType().equals(PaymentType.online) || orderEntity.getStatus().equals(ProductStatus.Paid)){
                List<Oder_detailEntity > oderDetailEntityList = orderEntity.getOderDetailEntityList();
                for (Oder_detailEntity oderDetail : oderDetailEntityList){
                    price += oderDetail.getTotalAllAmount();

                }
                price = price / 100 * 70;
            }

            List<BankEntity> bankEntityList =orderEntity.getUser().getBankEntityList();

            List<ProductEntity> productEntityList = productRepository.list_order(id);

            model.addAttribute("price",price);
            model.addAttribute("productEntityList",productEntityList);
            model.addAttribute("bankEntityList", bankEntityList);
            model.addAttribute("orderEntity",orderEntity);
            String url = "/user/delete_orders/" +id;
            model.addAttribute("action", url);
        }
        return "receive";
    }
    @RequestMapping(value = "/user/delete_orders/{id}", method = RequestMethod.POST)
    public String delete (@PathVariable int id, int bankId) throws MessagingException {
        Optional<OrderEntity> order = orderRepository.findById(id);
        OrderEntity orderEntity = order.get();
        Optional<BankEntity> bank = bankRepository.findById(bankId);
        BankEntity bankEntity = bank.get();
        List<Oder_detailEntity> oderDetailEntityList = orderEntity.getOderDetailEntityList();
        if (bankEntity != null){
            if (orderEntity.getPaymentType().equals(PaymentType.online) || orderEntity.getStatus().equals(ProductStatus.Paid)){

                for (Oder_detailEntity oderDetail : oderDetailEntityList){
                    double price = oderDetail.getTotalAllAmount();
                    price = price / 100 * 70;
                    bankEntity.setSurplus(bankEntity.getSurplus() + price);


                    List<BankEntity> bankAdmin = bankRepository.findByListBank(oderDetail.getProduct().getAdmin().getEmail());
                    for (BankEntity bank1 : bankAdmin){
                        if (bank1.getSurplus() >= price){
                            bank1.setSurplus(bank1.getSurplus() -price);
                            bankRepository.save(bank1);
                            break;
                        }

                    }
                    bankRepository.save(bankEntity);



                }

            }

            orderEntity.setStatus(ProductStatus.Cancel);
            orderRepository.save(orderEntity);
            for (Oder_detailEntity oderDetail : oderDetailEntityList){
                ProductEntity product =oderDetail.getProduct();
                product.setQuantity(product.getQuantity() + oderDetail.getQuantity());
                productRepository.save(product);
            }

            String link = "http://localhost:8080/user/order_list";
            String emailContent = "Bạn vừa đặt hàng thành công:<br/>" +
                    "Tên người đặt: " + orderEntity.getName() + "<br/>" +
                    "Địa chỉ: " + orderEntity.getShippingAddress() + "<br/>" +
                    "Ngày đặt: " + orderEntity.getCreatedDate() + "<br/>" +
                    "Xem đơn hàng: <a href='" + link + "'>" + link + "</a>" ;

            String subject = "Huỷ đơn hàng thành công";

            String email = orderEntity.getUser().getEmail();


            emailService.sendEmail(email, subject, emailContent);


        }

        return "redirect:/user/order_list?success=true";

    }
    @RequestMapping(value = "/user/pay_order/{id}")
    public String pay_order(@PathVariable int id, Model model){
        Optional<OrderEntity> order = orderRepository.findById(id);
        OrderEntity orderEntity = order.get();
        double price = 0;
        if (orderEntity != null){
            if (orderEntity.getPaymentType().equals(PaymentType.offline) || orderEntity.getStatus().equals(ProductStatus.Unpaid)){
                List<Oder_detailEntity > oderDetailEntityList = orderEntity.getOderDetailEntityList();
                for (Oder_detailEntity oderDetail : oderDetailEntityList){
                    price += oderDetail.getTotalAllAmount();

                }

            }

            List<BankEntity> bankEntityList =orderEntity.getUser().getBankEntityList();

            List<ProductEntity> productEntityList = productRepository.list_order(id);


            model.addAttribute("price",price);
            model.addAttribute("productEntityList",productEntityList);
            model.addAttribute("bankEntityList", bankEntityList);
            model.addAttribute("orderEntity",orderEntity);
            String url = "/user/pay_order/" +id;
            model.addAttribute("action", url);
        }
        return "receive";
    }
    @RequestMapping(value = "/user/pay_order/{id}", method = RequestMethod.POST)
    public String pay_oder(@PathVariable int id,int bankId){
        Optional<OrderEntity> order = orderRepository.findById(id);
        OrderEntity orderEntity = order.get();
        Optional<BankEntity> bank = bankRepository.findById(bankId);
        BankEntity bankEntity = bank.get();

        List<Oder_detailEntity> oderDetailEntityList = orderEntity.getOderDetailEntityList();
        double price = 0;
        for (Oder_detailEntity oderDetail : oderDetailEntityList){
            price += oderDetail.getTotalAllAmount();
        }
        if (orderEntity.getStatus().equals(ProductStatus.Unpaid)){

            if (bankEntity.getSurplus() >=price){
                bankEntity.setSurplus(bankEntity.getSurplus() -price);
                bankRepository.save(bankEntity);
               for (Oder_detailEntity oderDetail : oderDetailEntityList){
                    List<BankEntity> bankEntityList = bankRepository.findByListBank(oderDetail.getProduct().getAdmin().getEmail());
                    for (BankEntity bank1 : bankEntityList){
                        if (bank1 != null){
                            bank1.setSurplus(bank1.getSurplus()+oderDetail.getTotalAllAmount());
                            bankRepository.save(bank1);
                            break;
                        }
                    }
               }
            }else {
                return "redirect:/user/account";
            }

        }
        orderEntity.setStatus(ProductStatus.Completed);
        orderRepository.save(orderEntity);
        return "redirect:/user/order_list";

    }
}
