package com.innowise.kaminski.innowiseapi.controllers;

import com.innowise.kaminski.innowiseapi.models.Account;
import com.innowise.kaminski.innowiseapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getUsers() {
        List<Account> accounts = (List<Account>) userService.getUsers();
        return accounts.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(accounts, HttpStatus.OK);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody Account account) {
        Account accountUpdated = userService.updateUser(id, account);
        if(accountUpdated == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else  {
            return new ResponseEntity<>("User was updated: "+ accountUpdated, HttpStatus.OK);
        }
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
