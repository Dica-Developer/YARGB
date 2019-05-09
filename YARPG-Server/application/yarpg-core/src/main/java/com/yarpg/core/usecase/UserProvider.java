package com.yarpg.core.usecase;

import java.util.Optional;

import com.yarpg.core.entity.User;

public interface UserProvider {
    public abstract Optional<User> verifyLogin(String name, String password);

    public abstract void registerUser(String name, String password);

    public abstract void deleteUser(String name);
}
