package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.Enum.Sex;
import com.example.shopSpringboot.Enum.UserStatus;
import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.service.AccountService;
import com.example.shopSpringboot.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Random;

@Controller
public class adminController {
    @Autowired
    EmailService emailService;

    @Autowired
    AccountService accountService;
    @RequestMapping(value = "/signout-admin")
    public String sign_out_admin(Model model){
        model.addAttribute("action", "/signup-admin");
        model.addAttribute("name", "admin");
        model.addAttribute("accounts", new AccountEntity());
        return "register";
    }
    @RequestMapping(value = "/signup-admin", method = RequestMethod.POST)
    public String save_admin(@Valid @ModelAttribute ("accounts") AccountEntity account,
                             HttpSession session, Model model, BindingResult result) throws MessagingException {
        if (result.hasErrors()){
            return "register";
        }
        if (account.getAddress().isEmpty() || account.getEmail().isEmpty() || account.getPassword().isEmpty() || account.getBirthday() == null) {
            if (account.getAddress().isEmpty()) {
                result.rejectValue("address", "NotEmpty.account.address", "Vui lòng nhập địa chỉ");
            }
            if (account.getEmail().isEmpty()) {
                result.rejectValue("email", "NotEmpty.account.email", "Vui lòng nhập email");
            }
            if (account.getPassword().isEmpty()) {
                result.rejectValue("password", "NotEmpty.account.password", "Vui lòng nhập mật khẩu");
            }
            if (account.getBirthday() == null) {
                result.rejectValue("birthday", "NotNull.account.birthday", "Vui lòng nhập ngày sinh");
            }
            return "register";
        }


        account.setStatus(UserStatus.ACTIVE);
        session.setAttribute("account", account);

        Random random = new Random();
        int so = random.nextInt(10000);
        String number = String.valueOf(so);
        session.setAttribute("number", number);

        String subject = "Mã xác thực";

        emailService.sendEmail(account.getEmail(), subject, number);
        model.addAttribute("accuracy", "/accuracy-admin");

        return "accuracy";
    }

    @RequestMapping(value = "/accuracy-admin" , method = RequestMethod.POST)
    public String checkAccuracy_admin(@RequestParam String input,HttpSession session,  HttpServletRequest request, Model model){
        String number= (String) request.getSession().getAttribute("number");
        if (!input.equals(number)) {
            model.addAttribute("error", "Mã xác thực không đúng. Vui lòng nhập lại.");
            return "accuracy";
        }
        AccountEntity account= (AccountEntity) request.getSession().getAttribute("account");
        accountService.saveAdmin(account);
        session.invalidate();
        return "redirect:/login";


    }

}
