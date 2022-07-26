package de.hsba.bi.demo.subject;

import org.springframework.data.jpa.repository.JpaRepository;

interface SubjectRepository extends JpaRepository<Subject, Long> {
}
