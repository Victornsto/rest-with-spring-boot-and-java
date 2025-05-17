package com.victornsto.restwithspringbootandjava.repository;

import com.victornsto.restwithspringbootandjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
