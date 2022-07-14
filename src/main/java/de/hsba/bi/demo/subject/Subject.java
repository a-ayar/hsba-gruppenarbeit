package de.hsba.bi.demo.subject;


import lombok.Getter;
import lombok.Setter;

import java.util.*;

//@Entity
//@NoArgsConstructor
public class Subject {
//Variablen
    // @Entity
    //@Id
    // @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Setter
    @Getter
    private String name;

    // @Basic (optional = false)
    // private String name;

    //   @Getter
    //   @Setter
    //public List<SubjectEntry> entries;
    private List<SubjectEntry> entries;

    public List<SubjectEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }

}
