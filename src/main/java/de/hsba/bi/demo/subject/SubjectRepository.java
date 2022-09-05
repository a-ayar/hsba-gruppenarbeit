package de.hsba.bi.demo.subject;

import de.hsba.bi.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("select t from Subject t where t.teacher = :teacher ")
    List<Subject> findSubjectByTeacher (User teacher);
}
