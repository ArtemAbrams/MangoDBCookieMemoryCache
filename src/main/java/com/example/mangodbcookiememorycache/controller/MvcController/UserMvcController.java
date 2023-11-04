package com.example.mangodbcookiememorycache.controller.MvcController;


import com.example.mangodbcookiememorycache.domain.data.LoginData;
import com.example.mangodbcookiememorycache.domain.data.UserData;
import com.example.mangodbcookiememorycache.service.impl.CookieServiceImpl;
import com.example.mangodbcookiememorycache.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Base64;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc_user")
public class UserMvcController {
    private final UserServiceImpl userService;
    private final CookieServiceImpl cookieService;
    private static final int DURATION = 60*60*24*7;
    @GetMapping("/registration")
    public String showRegistrationForm(ModelMap model) {
        model.addAttribute("userData", new UserData());
        return "registration";
    }
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userData") UserData userData, RedirectAttributes redirectAttributes){
        try {
            userService.registerNewUserAccount(userData);
            redirectAttributes.addFlashAttribute("success", true);
            return "redirect:/mvc_user/login";
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/mvc_user/registration";
        }
    }
    @GetMapping("/login")
    public String login(HttpServletRequest request, ModelMap modelMap){
        var email = cookieService.retrieveCookie(request,"email_todolist");
        var password = cookieService.retrieveCookie(request,"password_todolist");
        modelMap.addAttribute("email_todolist", email);
        modelMap.addAttribute("password_todolist", password);
        return "login";
    }
    @PostMapping("/login/submit")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes){
       try {
           var userDto = userService.loginAccount(email, password);
           cookieService.createCookie(response,
                   userDto.getEmail(),
                   "email_todolist",
                   DURATION);
           cookieService.createCookie(response,
                    password,
                   "password_todolist",
                   DURATION);
           return "redirect:/mvc_user/login";
       }
       catch (Exception exception){
           log.error(exception.getMessage());
           redirectAttributes.addFlashAttribute("error", true);
           return "redirect:/mvc_user/login";
       }
    }
}
