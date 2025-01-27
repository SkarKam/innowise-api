package com.innowise.kaminski.innowiseapi.services;

import com.innowise.kaminski.innowiseapi.models.Account;
import com.innowise.kaminski.innowiseapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<Account> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public Iterable<Account> getUsers(){
        return userRepository.findAll();
    }

    public Account updateUser(Long id, Account account){

        Optional<Account> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            Account updatedAccount = optionalUser.get();
            updatedAccount.setUsername(account.getUsername());
            updatedAccount.setPassword(account.getPassword());
            return userRepository.save(updatedAccount);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long id){
        Optional<Account> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return true;
        } else {
            return false;
        }
    }
}
