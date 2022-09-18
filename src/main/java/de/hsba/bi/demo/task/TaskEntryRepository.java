package de.hsba.bi.demo.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskEntryRepository extends JpaRepository<TaskEntry, Long> {

}
