package de.hsba.bi.demo.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskEntryRepository extends JpaRepository<TaskEntry, Long> {

    //Datenbankabfrage Lösung gefiltert nach eingeloggten Schüler im Bezug zur jeweiligen Aufgabe - Marc
    @Query(value = "SELECT * FROM TASK_ENTRY WHERE STUDENT_ID = :student AND TASK_ID = :task", nativeQuery = true)
    List<TaskEntry> findTaskEntriesByStudentId(Long student, Long task);


}
