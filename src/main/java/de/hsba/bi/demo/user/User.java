package de.hsba.bi.demo.user;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectAssignment;
import de.hsba.bi.demo.task.TaskEntry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Users")
@Entity
@NoArgsConstructor
public class User implements Comparable<User> {

    @Basic(optional = false)
    public static String STUDENT_ROLE = "SCHÜLER";
    public static String TEACHER_ROLE = "LEHRER";
    public static String ADMIN_ROLE = "ADMIN";

    //Methode um den aktuellen User zu bekommen - Aylin
    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

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
    @OneToMany (mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<SubjectAssignment> subjects=new ArrayList<>();


    @Getter
    @Setter
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntry> taskEntries=new ArrayList<>();


    @Getter
    @Setter
    @OneToMany (mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> teacherSubjects=new ArrayList<>();



    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public int compareTo(User other) {
        return this.username.compareTo(other.username);
    }

    @Override
    public String toString() {
        return username;
    }
}
