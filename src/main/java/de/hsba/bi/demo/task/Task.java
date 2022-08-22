package de.hsba.bi.demo.task;


import de.hsba.bi.demo.subject.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor //
public class Task {

    //Variabl für den Status der bearbeitung
    @Getter
    @Setter
    private boolean taskIsOnEdit;

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
 // neue Ergänzungen:
    public TaskEntry getEntryById(Long entryId) {
        for(TaskEntry entry : entries){
            if (entry.getId().equals(entryId)){
                return entry;
            }
        }
        //Hier sollte er nicht hinkommen, wenn doch,
        // ist kein Entry mit der ID vorhanden udn hier sollte stattdessen eine Fehlerbehandlung stattfinden
        return new TaskEntry();
    }


}
