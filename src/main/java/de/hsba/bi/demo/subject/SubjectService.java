package de.hsba.bi.demo.subject;

import de.hsba.bi.demo.user.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    // Fach erstellen
    public Subject createSubject(String name, User teacher, User students) {
        Subject subject = new Subject(name, teacher, students);
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



