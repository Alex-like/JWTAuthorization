package com.example.JWTAuthorization.service;

import com.example.JWTAuthorization.models.Group;
import com.example.JWTAuthorization.models.User;
import com.example.JWTAuthorization.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl {
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group findById(final Long id) {
        return id == null ? null : groupRepository.findById(id).orElse(null);
    }

    public void addUser(final Long groupId, final User username) {
        Group group = findById(groupId);
        if (group != null) {
            group.addUser(username);
            groupRepository.save(group);
        }
    }
}
