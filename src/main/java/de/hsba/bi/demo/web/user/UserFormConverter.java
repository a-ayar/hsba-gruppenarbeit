package de.hsba.bi.demo.web.user;

import de.hsba.bi.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserFormConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    User update(User user, UserForm form) {
        user.setName(form.getName());
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole(form.getRole());
        return user;
    }
}
