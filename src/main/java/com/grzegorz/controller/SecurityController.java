package com.grzegorz.controller;

import com.grzegorz.config.SecurityUtils;
import com.grzegorz.model.User;
import com.grzegorz.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public @ResponseBody
    User getUserAccount() {
        User user = usersRepository.findByEmail(SecurityUtils.getCurrentLogin());
        if (user != null)
            user.setPassword(null);
        return user;
    }

    //Only for test
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody
    String getText() {
        return "Text only for admin";
    }

    @RequestMapping(value = "/security/newUser", method = RequestMethod.GET)
    public @ResponseBody
    User postUser() {
        User user = usersRepository.findByEmail(SecurityUtils.getCurrentLogin());
        if (user != null)
            user.setPassword(null);
        return user;
    }
}


