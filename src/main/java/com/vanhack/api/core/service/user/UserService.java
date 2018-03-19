package com.vanhack.api.core.service.user;

import com.vanhack.api.core.repository.model.User;

public interface UserService {

    User getUser(String slug);
}
