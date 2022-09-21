package de.hsba.bi.demo.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM TASK t INNER JOIN SUBJECT s ON t.SUBJECT_ID = s.Id WHERE s.TEACHER_ID = :teacher", nativeQuery = true)
    List<Task> findTasksByTeacherId(Long teacher);

    @Query(value = "SELECT * FROM TASK t INNER JOIN SUBJECT_ASSIGNMENT sa on t.SUBJECT_ID = sa.SUBJECTS_ID WHERE sa.STUDENT_ID = :student", nativeQuery = true)
    List<Task> findTasksByStudentId(Long student);


}
