package de.hsba.bi.demo.subject;

import org.springframework.stereotype.Service;
import java.util.Collection;
import javax.transaction.Transactional;

@Service // da von dieser Klasse ein Objekt erstellt werden soll
@Transactional

//Service mit den Methoden
public class SubjectService {


    private final SubjectRepository repository;

    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public Subject createSubject(String name) {
        Subject subject = new Subject();
        subject.setName(name);
        return repository.save(subject);
    }

    public Subject save(Subject subject) {
        return repository.save(subject);
    }

    public Subject getSubject(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addSubjectEntry(Subject subject, SubjectEntry entry) {
        entry.setSubject(subject);
        subject.getEntries().add(entry);
}

    public Collection<Subject> getAll() {
        return repository.findAll();
    }
}
