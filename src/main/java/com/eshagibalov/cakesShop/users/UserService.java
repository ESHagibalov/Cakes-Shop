package com.eshagibalov.cakesShop.users;

import com.eshagibalov.cakesShop.exceptions.UserExistException;
import com.eshagibalov.cakesShop.rest.dto.user.User;

import java.util.List;

public interface UserService {

    void addUser(User user) throws UserExistException;

    List<User> getUsers();

    Long getUserId(String number);
}
