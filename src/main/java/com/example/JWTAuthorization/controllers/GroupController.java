package com.example.JWTAuthorization.controllers;

import com.example.JWTAuthorization.configs.jwt.JwtUtils;
import com.example.JWTAuthorization.models.Group;
import com.example.JWTAuthorization.models.User;
import com.example.JWTAuthorization.pojo.CreateGroupRequest;
import com.example.JWTAuthorization.pojo.MessageResponse;
import com.example.JWTAuthorization.repository.GroupRepository;
import com.example.JWTAuthorization.service.GroupServiceImpl;
import com.example.JWTAuthorization.service.UserDetailsServiceImpl;
import com.example.JWTAuthorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/group")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GroupController {
    private final GroupServiceImpl groupService;
    private final GroupRepository groupRepository;
    private final UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    public GroupController(GroupServiceImpl groupService, GroupRepository groupRepository, UserService userService) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Group getGroup(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        String jwt = jwtUtils.parseJwt(httpServletRequest);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            User currentUser = userService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(jwt));
            Group group = groupService.findById(id);
            return group == null
                    ? null
                    : group.getUsers().stream().anyMatch(user -> user.equals(currentUser))
                    ? group
                    : null;
        }
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequest request, HttpServletRequest httpServletRequest) {
        if (request.getName().length() < 3) {
            return ResponseEntity.badRequest().body(new MessageResponse("too short group name"));
        }
        String jwt = jwtUtils.parseJwt(httpServletRequest);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            groupRepository.save(new Group(
                    request.getName(),
                    0,
                    userService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(jwt))));
        }
        return ResponseEntity.ok("Group created");
    }

    @PostMapping("/{groupId}/adduser/{username}")
    public ResponseEntity<?> addUser(@PathVariable Long groupId, @PathVariable String username) {
        groupService.addUser(groupId, userService.loadUserByUsername(username));
        return ResponseEntity.ok("user added");
    }
}
