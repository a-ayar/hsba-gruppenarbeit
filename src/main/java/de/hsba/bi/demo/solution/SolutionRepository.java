package de.hsba.bi.demo.solution;

import de.hsba.bi.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface SolutionRepository extends JpaRepository<Solution, Long> {

}
//sql für die abfrage der verschiedenen rollen