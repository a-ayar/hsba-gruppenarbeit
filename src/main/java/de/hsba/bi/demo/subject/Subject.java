package de.hsba.bi.demo.subject;

//import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @ManyToOne (optional = false)
    private User teacher;

    @Setter
    @Getter
    @ManyToOne
    private User students;

    public Subject(String name, User teacher, User students) {
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
