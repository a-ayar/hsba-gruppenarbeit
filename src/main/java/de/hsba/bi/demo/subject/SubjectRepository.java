package de.hsba.bi.demo.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    //Datenbankabfrage Fächer gefiltert nach eingeloggten Lehrer - Marc
    @Query(value = "SELECT * FROM SUBJECT WHERE TEACHER_ID = :teacher", nativeQuery = true)
    List<Subject> findSubjectByTeacherId (Long teacher);

    //Datenbankabfrage Fächer gefiltert nach eingeloggten Student - Marc
    @Query(value = "SELECT * FROM SUBJECT s INNER JOIN SUBJECT_ASSIGNMENT sa ON s.ID = sa.SUBJECTS_ID WHERE sa.STUDENT_ID = :student", nativeQuery = true)
    List<Subject> findSubjectByStudentId (Long student);

}
