package de.hsba.bi.demo.subject;

//import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany
    private List<SubjectAssignment> assignments =new ArrayList<>();

    public Subject(String name, User teacher) {
        this.name = name;
        this.teacher = teacher;
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
