package com.exam.service;

import com.exam.models.User;
import com.exam.models.UserRole;
import java.util.Set;

public interface UseService {

    //create user function
    public User createUser( User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);

    // delete user by id
    public void deleteUser(Long userId);


}
