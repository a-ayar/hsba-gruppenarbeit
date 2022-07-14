package de.hsba.bi.demo.subject;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SubjectRepository {

    private Map<Long, Subject> store = new TreeMap<>();
    private AtomicLong sequence = new AtomicLong();

    Subject save(Subject subject) {
        Long id = sequence.incrementAndGet();
        subject.setId(id);
        store.put(id, subject);
        return subject;
    }

    Optional<Subject> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    Collection<Subject> findAll() {
        return store.values();
    }
}
