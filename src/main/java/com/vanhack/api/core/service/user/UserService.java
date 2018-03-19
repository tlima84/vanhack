package com.vanhack.api.core.service.user;

import com.vanhack.api.core.repository.model.User;
import com.vanhack.api.resources.user.request.UserRequest;
import com.vanhack.api.resources.user.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getUser(String slug, String name);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(UserRequest userRequest, String slug);

    void deleteUser(String userSlug);

    User getUser(String slug);
}
