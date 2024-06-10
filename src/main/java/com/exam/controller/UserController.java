package com.exam.controller;

import com.exam.models.User;
import com.exam.models.Role;
import com.exam.models.UserRole;
import com.exam.service.UseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UseService useService;
       // Creating user or save data
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        // going to encode password using BCryptPasswordEncoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role=new Role();
        role.setRole_ID(103L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.useService.createUser(user,roles);

    }

    // get the user data

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username")String username){

      return this.useService.getUser(username);


    }

    // delete user id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.useService.deleteUser(userId);

    }



}
