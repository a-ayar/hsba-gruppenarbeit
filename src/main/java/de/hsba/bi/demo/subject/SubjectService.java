package de.hsba.bi.demo.subject;

import de.hsba.bi.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;



@Service
@Transactional
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectAssignmentRepository subjectAssignmentRepository;


    // Fach erstellen - Aylin
    public Subject createSubject(String name, User teacher, List<User> students) {
        Subject subject = new Subject(name, teacher);
        for (User student : students) {
            SubjectAssignment subjectAssignment = new SubjectAssignment();
            subjectAssignment.setSubjects(subject);
            subjectAssignment.setStudent(student);
            subjectAssignmentRepository.save(subjectAssignment);
            subject.addToAssignments(subjectAssignment);
        }
        return subjectRepository.save(subject);
    }
    //Fach speichern - Aylin
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    //Fach aufrufen - Aylin
    public Subject getSubject(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public SubjectAssignment getSubjectAssignment (Long subjectId, Long assignmentId){
        Subject subject = subjectRepository.getReferenceById(subjectId);
        SubjectAssignment subjectAssignment = subject.getSubjectAssignmentById(assignmentId);
        return subjectAssignment;
    }
    //Die Facher eines Lehrers - Marc
    public List<Subject> getSubjectById(Long id) {return subjectRepository.findSubjectByTeacherId(id);}

    //Die Fächer eines Schülers - Marc
    public List<Subject> getSubjectByStuId(Long id) {return subjectRepository.findSubjectByStudentId(id);}

    // zum löschen eines Faches - Aylin
    public void deleteSubject(Long subjectId){
        subjectRepository.deleteById(subjectId);
    }

    // Abfrage aller Fächer - Aylin
    public List<Subject> findAll() {return subjectRepository.findAll();
    }

}



