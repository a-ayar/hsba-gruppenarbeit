package de.hsba.bi.demo.subject;

//import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Subject implements Comparable<Subject> {

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Setter
    @Getter
    @Basic (optional = false)
    private String name;

    @Setter
    @Getter
    //@ManyToOne (optional = false)
    private String teacher;

    @Setter
    @Getter
    @Basic (optional = false)
    private String students;

    public Subject(String name, String teacher, String students) {
        this.name = name;
        this.teacher = teacher;
        this.students = students;
    }

    @Override
    public int compareTo(Subject other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
