package com.grzegorz.repository;

import com.grzegorz.model.Category;
import com.grzegorz.model.Role;
import com.grzegorz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by grzeg on 03.06.2017.
 */
@Component
public class Init {

    @Autowired
    UserRepository userRepository;


    @Autowired
    CategoriesRepository categoriesRepository;


    @PostConstruct
    public void init() {
        User user1 = userRepository.getUser("user@o2.pl");
        User admin = userRepository.getUser("admin@o2.pl");
        if (user1 == null) {
            User user = new User();
            user.setEmail("user@o2.pl");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode("user"));
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);
        }
        if (admin == null) {
            User user = new User();
            user.setEmail("admin@o2.pl");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode("admin"));
            user.setRole(Role.ROLE_ADMIN);
            userRepository.save(user);
        }

        Category[] categories =
                {new Category("Murarz"),
                        new Category("Hydraulik"),
                        new Category("Elektryk"),
                        new Category("Cieśla"),
                        new Category("Glazurnik"),
                        new Category("Tynkarz"),
                        new Category("Gazownik"),
                        new Category("Stolarz"),
                        new Category("Architekt"),
                        new Category("Szklarz")
                };
        for (Category c : categories) {
            Category cc = categoriesRepository.getByName(c.getName());
            if (cc == null) {
               categoriesRepository.saveCategory(c);
            }
        }
    }
}
