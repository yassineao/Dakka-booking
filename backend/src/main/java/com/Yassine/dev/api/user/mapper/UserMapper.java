package com.Yassine.dev.api.user.mapper;


import com.Yassine.dev.api.user.dto.UserCreateRequest;
import com.Yassine.dev.api.user.dto.UserResponse;
import com.Yassine.dev.api.user.dto.UserUpdateRequest;
import com.Yassine.dev.api.user.entity.Role;
import com.Yassine.dev.api.user.entity.User;

public class UserMapper {
    public static UserResponse toResponse(User user) {
        UserResponse r = new UserResponse();
        r.setId(user.getId());
        r.setEmail(user.getEmail());
        r.setRole(user.getRole());
        r.setCreatedAt(user.getCreatedAt());
        r.setUpdatedAt(user.getUpdatedAt());

        return r;
    }

    public static User toEntity(UserCreateRequest request) {
        User user = new User();

        user.setEmail(request.getEmail());
        user.setRole(Role.USER); // default role

        return user;
    }
    public static void userUpdate(UserUpdateRequest request, User user) {

        if  (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if  (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }

    }
}
