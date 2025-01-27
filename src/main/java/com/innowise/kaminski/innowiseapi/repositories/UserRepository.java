package com.innowise.kaminski.innowiseapi.repositories;

import com.innowise.kaminski.innowiseapi.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  UserRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
}
