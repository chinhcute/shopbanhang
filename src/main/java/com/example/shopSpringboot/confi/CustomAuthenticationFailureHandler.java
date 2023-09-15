package com.example.shopSpringboot.confi;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Lỗi xác thực: ";

        if (exception instanceof DisabledException) {
            errorMessage += "Mật khẩu không đúng. Vui lòng thử lại.";
        } else if (exception instanceof LockedException) {
            errorMessage += "Tài khoản đã bị khóa. Vui lòng liên hệ quản trị viên.";
        } else if (exception instanceof BadCredentialsException) {
            errorMessage += "Tài khoản không tồn tại.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage += "Tài khoản tạm khoá, đăng nhập sai quá nhiều.";}
        else {
            errorMessage += "Lỗi không xác định.";
        }
        request.getSession().setAttribute("errorMessage", errorMessage);
        response.sendRedirect("/login");
    }
}
