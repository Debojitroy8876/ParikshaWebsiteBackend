package com.exam;

import com.exam.Helper.UserFoundException;
import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.service.UseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner {

    @Autowired
    private UseService useService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {

            System.out.println(" start code");
        /*try {
            User user = new User();

            user.setFirstName("Debojit");
            user.setLastName("roy");
            user.setUsername("debojit8876");
            user.setPassword(this.bCryptPasswordEncoder.encode("qwerty"));
            user.setEmail("svyt0876@gmail.com");
            user.setProfile("abc.png");

        User user1 =new User();

        user1.setFirstName("rahul");
        user1.setLastName("roy");
        user1.setUsername("rahul8876");
        user1.setPassword("abcd");
        user1.setEmail("rahul0876@gmail.com");
        user1.setProfile("hgf.png");*


            Role role1 = new Role();
            role1.setRole_ID(200L);
            role1.setRoleName("ADMIN");

        Role role2=new Role();
        role2.setRole_ID(101L);
        role2.setRoleName("ADMIN");


            Set<UserRole> userRoleset = new HashSet<>();
            UserRole userRole = new UserRole();
            userRole.setRole(role1);
            userRole.setUser(user);

            userRoleset.add(userRole);

        UserRole userRole2=new UserRole();
        userRole2.setRole(role2);
        userRole2.setUser(user1);

        userRoleset.add(userRole2);


            User us = this.useService.createUser(user, userRoleset);
            System.out.println(us.getUsername());

        User us1= this.useService.createUser(user1,userRoleset);
        System.out.println(us1.getUsername());
        }
        catch(UserFoundException e){
            e.printStackTrace();
        }*/





	}
}
