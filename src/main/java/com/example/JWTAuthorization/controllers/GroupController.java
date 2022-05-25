package com.example.JWTAuthorization.controllers;

import com.example.JWTAuthorization.models.Group;
import com.example.JWTAuthorization.pojo.CreateGroupRequest;
import com.example.JWTAuthorization.pojo.MessageResponse;
import com.example.JWTAuthorization.repository.GroupRepository;
import com.example.JWTAuthorization.service.GroupServiceImpl;
import com.example.JWTAuthorization.service.UserDetailsServiceImpl;
import com.example.JWTAuthorization.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GroupController {
    private final GroupServiceImpl groupService;
    private final GroupRepository groupRepository;
    private final UserService userService;

    public GroupController(GroupServiceImpl groupService, GroupRepository groupRepository, UserService userService) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Group getGroup(@PathVariable Long id) {
        return groupService.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequest request) {
        if (request.getName().length() < 3) {
            return ResponseEntity.badRequest().body(new MessageResponse("too short group name"));
        }
        groupRepository.save(new Group(request.getName(), 0));
        return ResponseEntity.ok("Group created");
    }

    @PostMapping("/{groupId}/adduser/{username}")
    public ResponseEntity<?> addUser(@PathVariable Long groupId, @PathVariable String username) {
        groupService.addUser(groupId, userService.loadUserByUsername(username));
        return ResponseEntity.ok("user added");
    }
}
