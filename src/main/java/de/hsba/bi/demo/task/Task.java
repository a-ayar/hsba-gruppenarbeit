package de.hsba.bi.demo.task;


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
    @Basic(optional = false)
    private String subject;

    @Setter
    @Getter
    @Basic(optional = false)
    private String status;


    @OneToMany (cascade = CascadeType.ALL, mappedBy = "task")
    private List<TaskEntry> entries;


    public List<TaskEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }

}
