package de.hsba.bi.demo.subject;

//import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.task.Task;
import de.hsba.bi.demo.task.TaskEntry;
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

    @Getter
    @Setter
    private boolean SubjectIsOnEdit;

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
    @ManyToOne (optional = false, fetch = FetchType.LAZY)
    private User teacher;

    @Setter
    @Getter
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubjectAssignment> assignments =new ArrayList<>();

    @Getter
    @Setter
    @OneToMany (mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks=new ArrayList<>();



    public Subject(String name, User teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public  SubjectAssignment getSubjectAssignmentById(Long subjectAssignmentId) {
        for(SubjectAssignment subjectAssignment : assignments ){
            if (subjectAssignment.getId().equals(subjectAssignmentId)){
                return subjectAssignment;
            }
        }
        return new SubjectAssignment();
    }

    @Override
    public int compareTo(Subject other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }

    public void addToAssignments(SubjectAssignment newAssignment){
        assignments.add(newAssignment); //neue Methode um die Assignment zu übergeben
    }


}
