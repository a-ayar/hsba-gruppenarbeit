package de.hsba.bi.demo.task;


import de.hsba.bi.demo.subject.Subject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
public class Task {
//Variablen
    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Getter
    @Basic(optional = false)
    private String title;

    @Setter
    @Getter
    @Basic(optional = false)
    private String description;

    @Setter
    @Getter
    @ManyToOne (optional = false)
    private Subject subject;

    @Setter
    @Getter
    @Basic(optional = false)
    private String status;


    @OneToMany (cascade = CascadeType.ALL, mappedBy = "task", orphanRemoval = true)
    private List<TaskEntry> entries;


    public List<TaskEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }

}
