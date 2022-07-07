package de.hsba.bi.demo.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@NoArgsConstructor
public class User {

    public static String USER_ROLE = "Student";
    public static String TEACHER_ROLE = "Lehrer";
    public static String ADMIN_ROLE = "ADMIN";

    @Setter

    private Long id;

    private String name;

    private String password;

    private String role;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }



}
