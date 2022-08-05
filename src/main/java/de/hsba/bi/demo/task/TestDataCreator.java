package de.hsba.bi.demo.task;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectService;
import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Transactional
public class TestDataCreator {

    //private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskService taskService;
    private final SubjectService subjectService;


    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        //user
        User aylin = userService.save(new User(  "Aylin Ayar", "Aylin", "1234", User.ADMIN_ROLE));
        User nils = userService.save(new User( " Nils Voß", "Nils", "1234", User.STUDENT_ROLE));
        User marc = userService.save(new User( " Marc Schümann", "Marc", "1234", User.STUDENT_ROLE));
        User andreas = userService.save(new User( "Andreas Hoppe", "Andreas", "1234", User.TEACHER_ROLE));
        User uwe = userService.save(new User( "uwe Hoppe", "uwe", "1234", User.TEACHER_ROLE));

        // add some subjects
        Subject subject = subjectService.createSubject("Deutsch", aylin, marc);
        Subject mathe = subjectService.save(new Subject("Mathe", nils, marc));
        subjectService.save(subject);
        subjectService.save(new Subject("Deutsch", andreas, nils));

        // add example Journal for testing
        Task task = taskService.createTask("Gleichung lösen", "Löse die Gleichung 34 + x = 67", mathe, "initial"); //String
        taskService.addTaskEntry(task, new TaskEntry("Die Antwort ist 33",  aylin));
        taskService.save(task);

    }
}
