package com.eshagibalov.cakesShop.users;

import com.eshagibalov.cakesShop.exceptions.UserExistException;
import com.eshagibalov.cakesShop.rest.dto.user.User;

public interface UserService {

    void addUser(User user) throws UserExistException;

    Long getUserId(String number);
}
