package de.hsba.bi.demo.user;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectAssignment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Users")
@Entity
@NoArgsConstructor
public class User implements Comparable<User> {

    @Basic(optional = false)
    public static String STUDENT_ROLE = "Schüler";
    public static String TEACHER_ROLE = "Lehrer";
    public static String ADMIN_ROLE = "Admin";

    //Variabl für den Status der bearbeitung
    @Getter
    @Setter
    private boolean UserIsOnEdit;

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
    @Column(unique = true)//
    private String username;

    @Getter
    @Setter
    @Basic(optional = false)
    private String password;

    @Getter
    @Setter
    @Basic(optional = false)
    private String role;

    @Getter
    @Setter
    @OneToMany
    private List<SubjectAssignment> subjects=new ArrayList<>();




    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
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
