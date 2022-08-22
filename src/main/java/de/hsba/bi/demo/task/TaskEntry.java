package de.hsba.bi.demo.task;
import de.hsba.bi.demo.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TaskEntry {

    //Variabl für den Status der bearbeitung
    @Getter
    @Setter
    private boolean isOnEdit;

    @Id
    @GeneratedValue
    @Getter
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(optional = false)
    private Task task;

    @Basic(optional = false)
    private String solution;

    @ManyToOne(optional = false)
    private User student;

    public TaskEntry( String solution, User student) {
        this.solution = solution;
        this.student = student;
        setOnEdit(false);
    }

}
