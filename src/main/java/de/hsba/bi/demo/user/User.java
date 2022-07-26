package de.hsba.bi.demo.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "Users")
@Entity
@NoArgsConstructor
public class User implements Comparable<User> {

    //public static String USER_ROLE = "Student";
    //public static String TEACHER_ROLE = "Lehrer";
    //public static String ADMIN_ROLE = "ADMIN";

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @Basic(optional = false)
    private String name;

    @Getter
    @Setter
    @Basic(optional = false)
    private String username;

    @Getter
    @Setter
    @Basic(optional = false)
    private String password;


    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
