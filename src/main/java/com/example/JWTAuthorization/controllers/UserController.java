package com.example.JWTAuthorization.controllers;

import com.example.JWTAuthorization.models.User;
import com.sun.istack.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/user")
public class UserController {

    // Я тут не оч понимаю что делаю, я хочу залогиненного юзера текущего получать, это для странички User
    @Nullable
    @ModelAttribute
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User getUser(HttpServletRequest request) {
        return (User) request.getAttribute("user");
    }
}
