package com.Prachi.KontactHub.Services;

import com.Prachi.KontactHub.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String id);
    Boolean isUserExistByEmail(String email);
    List<User> getAllUser();
}
