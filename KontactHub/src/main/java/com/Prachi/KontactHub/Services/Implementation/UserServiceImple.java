package com.Prachi.KontactHub.Services.Implementation;

import com.Prachi.KontactHub.Repo.UserRepo;
import com.Prachi.KontactHub.Services.UserService;
import com.Prachi.KontactHub.entities.User;
import com.Prachi.KontactHub.helpers.AppConstants;
import com.Prachi.KontactHub.helpers.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId= UUID.randomUUID().toString();
        user.setUserId(userId);

        user.setPassword(passwordEncoder.encode(user.getPassword()));


//        settig user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        logger.info((user.getProvider().toString()));

        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
     User user2=   userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found!"));
        user2.setEmail(user.getEmail());
        user2.setName(user.getName());
        user2.setPassword(user.getPassword());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfileImg(user.getProfileImg());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        User save=userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2=   userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found!"));
        userRepo.delete(user2);



    }

    @Override
    public boolean isUserExist(String id) {
        User user2=   userRepo.findById(id).orElse(null);

        return user2 != null;
    }

    @Override
    public Boolean isUserExistByEmail(String email) {

        User user2=   userRepo.findById(email).orElse(null);

        return user2!=null;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
