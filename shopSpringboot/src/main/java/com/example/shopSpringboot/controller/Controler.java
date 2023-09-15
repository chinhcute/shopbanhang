package com.example.shopSpringboot.controller;

import com.example.shopSpringboot.Enum.Role;
import com.example.shopSpringboot.Enum.Sex;

import com.example.shopSpringboot.Enum.UserStatus;
import com.example.shopSpringboot.confi.SecurityConfig;
import com.example.shopSpringboot.entity.*;
import com.example.shopSpringboot.repository.*;
import com.example.shopSpringboot.service.AccountService;
import com.example.shopSpringboot.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
public class Controler  {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    EmailService emailService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BankRepository bankRepository;


    @GetMapping("/login")
    public String showLoginForm( Model model) {

        model.addAttribute("action", "j_spring_security_check");
        model.addAttribute("name", "Đăng nhập");

        return "login";
    }

    @RequestMapping(value = "/checkEmail")
    @ResponseBody
    public boolean email(@RequestParam ("email") String email) {
        if (checkEmail(email)){
            return true;
        }
        return false;

    }
    public Boolean checkEmail(String email){
        return email.endsWith("@gmail.com");

    }
    @RequestMapping(value = "/check-email",method = RequestMethod.POST)
    @ResponseBody
    public boolean check_Email(@RequestParam("email") String email) {
        List<AccountEntity> accountEntities = accountRepository.findByEmail(email);
        return accountEntities.isEmpty();
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email =authentication.toString();
        if (authentication instanceof UserDetails){
            email = ((UserDetails) authentication).getUsername();
        }
        model.addAttribute("email", email);
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
        List<ProductEntity> productEntityList = productRepository.findByProductOderByLimit8();
        model.addAttribute("productEntityLists",productEntityList);


        return "home";
    }

    @GetMapping("/admin/home")
    public String home(@RequestParam(defaultValue = "0") int page, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.toString();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        }

        Pageable pageable = PageRequest.of(page, 10);

        Page<OrderEntity> orderPage = orderRepository.admin_order(email,pageable);
        List<OrderEntity> orderEntityList = orderPage.getContent();

        model.addAttribute("orderEntityPage", orderPage);
        model.addAttribute("orderEntityList",orderEntityList);
        model.addAttribute("currentPage", page);

        model.addAttribute("message", "Hello Admin: " + email);
        return "admin";
    }


    @RequestMapping(value = "/signout")
    public String sign_out(Model model){
        model.addAttribute("action", "/signup");
        model.addAttribute("name", "Đăng kí");
        model.addAttribute("accounts", new AccountEntity());
        return "register";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
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
        String emailContent = "Chào bạn,<br>" +
                "Đây là mã xác thực của bạn: " + number + "<br>" +
                "Sử dụng mã này để hoàn tất quá trình xác thực địa chỉ email của bạn.<br>" +
                "Trân trọng,<br>" +
                "Nhóm hỗ trợ của chúng tôi";
        session.setAttribute("number", number);


        String subject = "Mã xác thực";

        emailService.sendEmail(account.getEmail(), subject, emailContent);
        model.addAttribute("accuracy", "/accuracy");
        return "accuracy";
    }


    @RequestMapping(value = "/accuracy" , method = RequestMethod.POST)
    public String checkAccuracy(@RequestParam String input, HttpServletRequest request, Model model, HttpSession session){
        String number= (String) request.getSession().getAttribute("number");
        if (!input.equals(number)) {
            model.addAttribute("error", "Mã xác thực không đúng. Vui lòng nhập lại.");
            return "accuracy";
        }
       AccountEntity account= (AccountEntity) request.getSession().getAttribute("account");
        accountService.saveAccount(account);
        session.invalidate();

        return "redirect:/login";


    }
    @RequestMapping(value = "/user/account")
    public String account(@RequestParam (value = "error", required = false) Boolean error,
                          Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.toString();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        }

        if (error != null && error) {
            model.addAttribute("showError", true);
        }

        AccountEntity accountEntity = accountRepository.findByEmailLike(email);
        model.addAttribute("accountEntity",accountEntity);
        List<BankEntity> bankEntityList = bankRepository.findByListBank(email);
        model.addAttribute("bankEntityList",bankEntityList);
        return "account";
    }
    @RequestMapping(value = "/user/recharge/{id}")
    public String recharge(@PathVariable int id, Model model){
        Optional<BankEntity> bank = bankRepository.findById(id);
        BankEntity bankEntity = bank.get();
        String action = "/user/recharge/"+id;
        model.addAttribute("bankEntity",bankEntity);
        model.addAttribute("action",action);
        return "recharge";

    }
    @RequestMapping(value = "/user/recharge/{id}", method = RequestMethod.POST)
    public String recharge(@PathVariable int id, @RequestParam  double price , Model model) {
        if (price == 0){
            model.addAttribute("error", "vui lòng nhập số tiền");
            return "recharge";
        }
        Optional<BankEntity> bank = bankRepository.findById(id);
        BankEntity bankEntity = bank.get();
        bankEntity.setSurplus(bankEntity.getSurplus() + price);
        bankRepository.save(bankEntity);
        return "redirect:/user/account";
    }
    @RequestMapping(value = "/forgot_password")
    public String forgot_password(Model model){
        model.addAttribute("action", "/forgot_passwords");
        return "forgot_password";
    }
    @RequestMapping(value = "/forgot_passwords", method = RequestMethod.POST)
    public String forgot_passwords(@RequestParam String email, Model model, HttpSession httpSession) throws MessagingException {
        AccountEntity account = accountRepository.findByEmailLike(email);
        if (account == null){

            model.addAttribute("error", "Không có tài khoản này");
            return "forgot_password";
        }
        Random random = new Random();
        int so = random.nextInt(10000);
        String numbers = String.valueOf(so);


        String emailSubject = "Xác thực địa chỉ email";
        String emailContent = "Chào bạn,<br>" +
                "Đây là mã xác thực của bạn: " + numbers + "<br>" +
                "Sử dụng mã này để hoàn tất quá trình xác thực địa chỉ email của bạn.<br>" +
                "Trân trọng,<br>" +
                "Nhóm hỗ trợ của chúng tôi";
        emailService.sendEmail(email, emailSubject, emailContent);
        httpSession.setAttribute("numbers",numbers);
        httpSession.setAttribute("account_email", account);
        model.addAttribute("accuracy", "/accuracy_email");
        return "accuracy";
    }
    @RequestMapping(value = "/accuracy_email" , method = RequestMethod.POST)
    public String accuracy_email(@RequestParam String input, HttpServletRequest request, Model model, HttpSession session){
        String number= (String) request.getSession().getAttribute("numbers");
        if (!input.equals(number)) {
            model.addAttribute("error", "Mã xác thực không đúng. Vui lòng nhập lại.");
            return "accuracy";
        }

        model.addAttribute("action", "/new_password");
        return "new_password";


    }
    @RequestMapping(value = "/new_password", method = RequestMethod.POST)
    public String new_password (HttpSession session, HttpServletRequest request, Model model,
                                @RequestParam String input1, @RequestParam String input2){
        if (!input1.equals(input2)){
            model.addAttribute("error", "vui lòng kiểm tra lại mật khẩu");
            return "new_password";
        }
        AccountEntity account= (AccountEntity) request.getSession().getAttribute("account_email");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(input1);
        account.setPassword(password);
        accountRepository.save(account);
        session.invalidate();
        return "redirect:/login";
    }




    @GetMapping("/products")
    @ResponseBody
    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/category_all")
    @ResponseBody
    public List<CategoryEntity> categoryEntityList(){
        return categoryRepository.findAll();
    }





}

