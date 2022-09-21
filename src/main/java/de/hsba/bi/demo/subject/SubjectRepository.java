package de.hsba.bi.demo.subject;

import de.hsba.bi.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    //@Query("select t from Subject t where t.teacher = :teacher ")
    @Query(value = "SELECT * FROM SUBJECT WHERE TEACHER_ID = :teacher", nativeQuery = true)
    List<Subject> findSubjectByTeacherId (Long teacher);

    @Query(value = "SELECT * FROM SUBJECT s INNER JOIN SUBJECT_ASSIGNMENT sa ON s.ID = sa.SUBJECTS_ID WHERE sa.STUDENT_ID = :student", nativeQuery = true)
    List<Subject> findSubjectByStudentId (Long student);

}
