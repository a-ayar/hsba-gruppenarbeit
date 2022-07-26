package de.hsba.bi.demo.subject;

import de.hsba.bi.demo.user.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    // Fach erstellen
    public Subject createSubject(String name, String teacher, String students) {
        Subject subject = new Subject();
        subject.setName(name);
        subject.setTeacher(teacher);
        subject.setStudents(students);
        return subjectRepository.save(subject);
    }
    //Aufgabe speicher speichern
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



