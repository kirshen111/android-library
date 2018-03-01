package com.example.elika.myapplication999;

/**
 * Created by elika on 2/28/2018.
 */

public class User {
    public int UserTypeId;
    public String UserName;
    public String Password;
    public String LastName;
    public String FirstName;
    public String Email;
    public String City;
    public String Address;

    public User()
    {
    }

    public User(int userTypeId, String userName,
             String password,
             String lastName,
             String firstName,
             String email,
             String city,
             String address)
    {
        UserTypeId = userTypeId;
        UserName = userName;
        Password = password;
        LastName = lastName;
        FirstName = firstName;
        Email = email;
        City = city;
        Address = address;
    }
}
