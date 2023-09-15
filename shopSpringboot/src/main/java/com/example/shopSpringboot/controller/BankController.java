package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.entity.BankEntity;
import com.example.shopSpringboot.repository.AccountRepository;
import com.example.shopSpringboot.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class BankController {
    @Autowired
    BankRepository bankRepository;
    @Autowired
    AccountRepository accountRepository;
    @RequestMapping(value = "/user/add_bank" , method = RequestMethod.GET)
    public String add_bank(Model model){
        model.addAttribute("bank", new BankEntity());
        model.addAttribute("action", "/user/save_bank");

        return "bank";
    }

    @RequestMapping(value = "/user/save_bank" , method = RequestMethod.POST)
    public String save_bank(@Valid @ModelAttribute("bank") BankEntity bank , BindingResult result , Model model){
        if (result.hasErrors()){
            return "bank";
        }
        BankEntity bankEntity = bankRepository.findByAccount_number(bank.getAccount_number());
        if (bankEntity != null){
            result.rejectValue("account_number", "account_number", "số tài khoản đã đc dùng");
            return "bank";
        }
        Object authen = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authen.toString();
        if (authen instanceof UserDetails){
            email = ((UserDetails) authen).getUsername();
        }

        AccountEntity account = accountRepository.findByEmailLike(email);
        bank.setSurplus(200000);
        bank.setAccount(account);
        bankRepository.save(bank);

        return "redirect:/home";
    }

}
