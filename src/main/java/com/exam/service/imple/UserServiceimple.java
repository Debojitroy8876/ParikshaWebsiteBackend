package com.exam.service.imple;

import com.exam.Helper.UserFoundException;
import com.exam.models.Role;
import com.exam.Repository.RoleRepository;
import com.exam.Repository.UserRepository;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.service.UseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;
@Service
public class UserServiceimple implements UseService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    // creating User
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());

        if (local != null) {
            System.out.println("User is already present");
            throw new UserFoundException();
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);


    }

    @Override
    public void deleteUser(Long userId) {
          this.userRepository.deleteById(userId);
    }
}
