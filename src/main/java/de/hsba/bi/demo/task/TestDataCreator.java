package de.hsba.bi.demo.task;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectService;
import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class TestDataCreator {

    //private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskService taskService;
    private final SubjectService subjectService;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        if (!userService.findAll().isEmpty()) {
            // prevent initialization if DB is not empty
            return;
        }


        //user
        User marc = createUser("marc", "marc", "password", User.TEACHER_ROLE);
        User aylin = createUser ("aylin", "aylin", "password", User.STUDENT_ROLE);
        createUser ("nils", "nils", "password", User.ADMIN_ROLE);


        // add some subjects
        Subject subject = subjectService.createSubject("Deutsch", aylin, List.of(marc));
        Subject subject2 = subjectService.createSubject("Mathe", aylin, List.of(marc));
        subjectService.save(subject);
        subjectService.save(subject2);


        // add example Task for testing
        Task task = taskService.createTask("Gleichung lösen", "Löse die Gleichung 34 + x = 67", subject);
        taskService.addTaskEntry(task, new TaskEntry("Die Antwort ist 33", marc));
        taskService.save(task);

    }

    private User createUser(String name, String username, String password, String role) {
        return userService.save(new User(name, username, passwordEncoder.encode(password), role));
    }
}
