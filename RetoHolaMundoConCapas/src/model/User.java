/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 2dami
 */
public class User {
    
    private String username;
    private String password;
    private int age;
    private String residence;
    
    public User (){
        this.username="";
        this.password="";
        this.age=0;
        this.residence="";
    }
    public User (String username,String password,int age, String residence){
        this.username=username;
        this.password=password;
        this.age=age;
        this.residence=residence;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", age=" + age + ", residence=" + residence + '}';
    }
    
    
}