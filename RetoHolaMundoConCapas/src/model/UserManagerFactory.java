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
public class UserManagerFactory {
    
    public static User createUser(String username, String password, int age, String residence) {
        return new User(username, password, age, residence);
    }
}