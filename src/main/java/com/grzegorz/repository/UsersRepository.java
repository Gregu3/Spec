package com.grzegorz.repository;

import com.grzegorz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kosa1010 on 16.05.17.
 */
public interface UsersRepository  extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
