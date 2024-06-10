package com.exam.service.imple;

import com.exam.Repository.UserRepository;
import com.exam.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImple implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =this.userRepository.findByUsername(username);
        if(user==null){
          System.out.println("user not found");
          throw new UsernameNotFoundException("Invalid credentials");
        }

        return user;
    }
}
