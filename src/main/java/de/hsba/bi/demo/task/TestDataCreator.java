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
        User aylin = userService.save(new User( "Aylin Ayar", "Aylin", "1234"));
        User nils = userService.save(new User( "Nils Voß", "Nils", "1234"));
        User marc = userService.save(new User( "Marc Schümann", "Marc", "1234"));
        User andreas = userService.save(new User( "Andreas Hoppe", "Andreas", "1234"));

        // add example Journal for testing
        Task task = taskService.createTask("Gleichung lösen", "Löse die Gleichung 34 + x = 67", "Mathe", "initial");
        taskService.addTaskEntry(task, new TaskEntry("Die Antwort ist 33",  aylin));
        taskService.save(task);

        // add some subjects
        Subject subject = subjectService.createSubject("Mathe", "Aylin", "Günther");
        subjectService.save(subject);
    }
}
