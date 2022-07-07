package de.hsba.bi.demo.subject;

public class SubjectEntry {

    //Variablen

    private int subjectId;

    private String subjectName;

    private String subjectTeacher;

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
}
