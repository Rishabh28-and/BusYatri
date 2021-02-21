package com.example.busyatri.Model;

public class User {
    private String Name;
    private String Password;
    private String Phone;
    private String IsStaff;

    public User(){

    }
    public User(String name, String password,String phone) {
        Name = name;
        Password = password;
        Phone = phone;
        IsStaff="false";
    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
