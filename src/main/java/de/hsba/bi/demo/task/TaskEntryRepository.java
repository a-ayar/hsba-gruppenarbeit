package de.hsba.bi.demo.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskEntryRepository extends JpaRepository<TaskEntry, Long> {
}
