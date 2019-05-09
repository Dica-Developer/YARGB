package com.yarpg.core.usecase.user;

import java.util.Optional;

import com.yarpg.core.entity.User;
import com.yarpg.core.usecase.UserProvider;

public class UserUseCase {

    private UserProvider _userProvider;

    public UserUseCase(UserProvider user) {
        _userProvider = user;
    }

    public User getUser(String name, String password) {
        Optional<User> verifyLogin = _userProvider.verifyLogin(name, password);
        if (verifyLogin.isPresent()) {
            return verifyLogin.get();
        }
        return null;
    }
}
