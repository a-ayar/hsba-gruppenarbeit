package de.hsba.bi.demo.task;


import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
public class Task {

    //Variable für den Status der Bearbeitung - Aylin
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
    private Status status;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "task", orphanRemoval = true)
    private List<TaskEntry> entries;

   // Methode um alle Antworten einer Aufgabe abzufragen - Aylin
    public List<TaskEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }
 // Methode um eine Antwort einer Aufgabe abzufragen - Aylin
    public TaskEntry getEntryById(Long entryId) {
        for(TaskEntry entry : entries){
            if (entry.getId().equals(entryId)){
                return entry;
            }
        }
        return new TaskEntry();
    }


}
