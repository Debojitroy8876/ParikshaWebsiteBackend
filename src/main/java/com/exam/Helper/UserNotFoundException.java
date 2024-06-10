package com.exam.Helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User with this username is not found in database");

    }
    public UserNotFoundException(String msz){
        super(msz);
    }
}
