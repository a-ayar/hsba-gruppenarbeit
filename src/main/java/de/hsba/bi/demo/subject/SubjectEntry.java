package de.hsba.bi.demo.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor

public class SubjectEntry {

    //Variablen

    private int subjectId;

    private Subject subject;//new 3

    private String subjectName;

    private String subjectTeacher;

    private String subjectStudent;
//ex3
    public SubjectEntry(final int subjectId, final String subjectName, final String subjectTeacher, final String subjectStudent) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectTeacher = subjectTeacher;
        this.subjectStudent = subjectStudent;
    }

    /*

    public SubjectEntry() {
    }
    //zuweisung

    public SubjectEntry(int subjectId, String subjectName, String subjectTeacher) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectTeacher = subjectTeacher;
    }

    // get und set methoden
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }
    */
}
