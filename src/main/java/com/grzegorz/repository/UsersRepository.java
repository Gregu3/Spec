package com.grzegorz.repository;

import com.grzegorz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
