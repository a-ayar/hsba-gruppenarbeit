package de.hsba.bi.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    //Datenbankabfrage User nach Rolle
    @Query("select s from User s where s.role = :role ")
    List<User> findByRole (String role);


}
