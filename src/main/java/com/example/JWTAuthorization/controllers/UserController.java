package com.example.JWTAuthorization.controllers;

import com.example.JWTAuthorization.configs.jwt.JwtUtils;
import com.example.JWTAuthorization.models.Group;
import com.example.JWTAuthorization.models.User;
import com.example.JWTAuthorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private JwtUtils jwtUtils;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser(HttpServletRequest request) {
        String jwt = jwtUtils.parseJwt(request);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            return userService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(jwt));
        }
        return null;
    }

    @GetMapping("/groups")
    public Set<Group> findGroups(HttpServletRequest request) {
        String jwt = jwtUtils.parseJwt(request);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            User user = userService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(jwt));
            return user == null ? null : user.getGroups();
        }
        return null;
    }
}
