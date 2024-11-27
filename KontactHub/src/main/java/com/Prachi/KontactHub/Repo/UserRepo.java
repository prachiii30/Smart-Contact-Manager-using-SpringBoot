package com.Prachi.KontactHub.Repo;

import com.Prachi.KontactHub.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository <User,String>{

    Optional<User> findByEmail(String email);
}
