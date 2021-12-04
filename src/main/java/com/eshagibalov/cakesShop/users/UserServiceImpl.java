package com.eshagibalov.cakesShop.users;

import com.eshagibalov.cakesShop.exceptions.UserExistException;
import com.eshagibalov.cakesShop.rest.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) throws UserExistException {
        if(userRepository.existsUserEntityByNumber(user.getNumber())) {
            throw new UserExistException("User is already exist");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setNumber(user.getNumber());
        userRepository.saveAndFlush(userEntity);
    }

    @Override
    public Long getUserId(String number) {
        return userRepository.findUserEntityByNumber(number).getId();
    }
}
