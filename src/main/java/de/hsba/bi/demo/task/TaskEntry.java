package de.hsba.bi.demo.task;
import de.hsba.bi.demo.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TaskEntry {

    //Variable für den Status der Bearbeitung - Aylin
    @Getter
    @Setter
    private boolean answerIsOnEdit;

    //Variable für die bewertung - Aylin
    @Getter
    @Setter
    private boolean evaluationIsOnEdit;

    @Id
    @GeneratedValue
    @Getter
    @Setter(AccessLevel.NONE)
    private Long id;


    @ManyToOne(optional = false)
    private Task task;

    @Basic(optional = false)
    private String solution;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User student;

    @Getter
    @Setter
    @Basic(optional = false)
    private Evaluation evaluation;

    @Getter
    @Setter
    private String comment;

//Constructor mit dem Default Wert als unbenotet - Aylin
    public TaskEntry( String solution, User student) {
        this.solution = solution;
        this.student = student;
        setAnswerIsOnEdit(false);
        this.evaluation = Evaluation.UNBENOTET;
    }

}
