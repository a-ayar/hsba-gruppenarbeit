package de.hsba.bi.demo.subject;

import de.hsba.bi.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectAssignmentRepository subjectAssignmentRepository;


    // Fach erstellen
    public Subject createSubject(String name, User teacher, List<User> students) {
        Subject subject = new Subject(name, teacher);
        for (User student: students
             ) {SubjectAssignment subjectAssignment = new SubjectAssignment();
            subjectAssignment.setSubjects(subject);
            subjectAssignment.setStudent(student);
            subjectAssignmentRepository.save(subjectAssignment);

        }
        return subjectRepository.save(subject);
    }
    //Aufgabe speicher --> für testdata
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    //Aufgabe aufrufen

    public Subject getSubject(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public List<Subject> findAll() {return subjectRepository.findAll();
    }

}



