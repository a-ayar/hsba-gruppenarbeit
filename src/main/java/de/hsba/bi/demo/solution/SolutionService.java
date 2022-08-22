package de.hsba.bi.demo.solution;

import de.hsba.bi.demo.solution.Solution;
import de.hsba.bi.demo.solution.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class SolutionService {

    private final SolutionRepository solutionRepository;

    public List<Solution> findAll() {return solutionRepository.findAll();}

    public Solution save(Solution solution) {return solutionRepository.save(solution);
    }

    public Solution getSolution(Long id) {
        return solutionRepository.findById(id).orElse(null);
    }


    // Fach erstellen
    public Solution createSolution(String mark, String comment) {
        Solution solution = new Solution();
        solution.setMark(mark);
        solution.setComment(comment);
        return solutionRepository.save(solution);
    }


}
