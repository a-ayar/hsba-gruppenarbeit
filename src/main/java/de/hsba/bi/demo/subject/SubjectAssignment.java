package de.hsba.bi.demo.subject;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SubjectAssignment {

    @Id
    @GeneratedValue
    @Getter
    private Long Id;

    @Getter
    @Setter
    @ManyToOne (fetch = FetchType.LAZY)
    private User student;

    @Getter
    @Setter
    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Subject subjects;
}
