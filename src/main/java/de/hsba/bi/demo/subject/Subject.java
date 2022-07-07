package de.hsba.bi.demo.subject;


import java.util.*;

//@Entity
//@NoArgsConstructor
public class Subject {
//Variablen
    // @Entity
    //@Id
    // @GeneratedValue
    // private int id;

    // @Getter
    // @Setter
    // @Basic (optional = false)
    // private String name;

    //   @Getter
    //   @Setter
    public List<SubjectEntry> entries;

    public List<SubjectEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }

}
