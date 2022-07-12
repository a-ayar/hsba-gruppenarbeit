package de.hsba.bi.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class UserRepository extends JpaRepository<User, Long> {

    User findByName (String name);

    List<User> findByRole(String role);
}
