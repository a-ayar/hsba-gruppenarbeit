package de.hsba.bi.demo.user;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectAssignment;
import de.hsba.bi.demo.task.TaskEntry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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

    //Wenn ein Schüler gelöscht werden soll dann sollen die Zuweisung der Schüler auch gelöscht werden - Aylin
    @Getter
    @Setter
    @OneToMany (mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<SubjectAssignment> subjects=new ArrayList<>();

    //Wenn ein Schüler gelöscht werden soll dann sollen die Antworten der Schüler auch gelöscht werden - Aylin
    @Getter
    @Setter
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntry> taskEntries=new ArrayList<>();

    //Wenn ein Lehrer gelöscht werden soll dann sollen die Fächer eines Lehrers auch gelöscht werden - Aylin
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
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }


}
