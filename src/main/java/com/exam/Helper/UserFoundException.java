package com.exam.Helper;

public class UserFoundException extends Exception{
    public UserFoundException(){
        super("User with this username is already present. Try with another name");

    }
  public UserFoundException(String msz){
        super(msz);
  }
}
