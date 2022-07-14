package de.hsba.bi.demo.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class TestDataCreator {

    private final SubjectRepository subjectRepository;


    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        // add example Journal for testing
        Subject subject = new Subject();
        subject.setName("Fächer");
        subject.getEntries().add(new SubjectEntry(Integer.valueOf(1), "Deutsch", "Nils", "Lara Hans Peter"));
        subject.getEntries().add(new SubjectEntry(Integer.valueOf(2), "Mathe", "Aylin", "Henrick Günter Klaus"));

        subjectRepository.save(subject);
    }
}
