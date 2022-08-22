package de.hsba.bi.demo.user;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.task.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void delete(Long id) { userRepository.deleteById(id);};

    public List<User> findAll() {return userRepository.findAll();}

    public List<User> findAllAdmins() {return userRepository.findByRole("Admin");}
    public List<User> findAllTeacher() {return userRepository.findByRole("Lehrer");}
    public List<User> findAllStudents() {return userRepository.findByRole("Schüler");}

    public User save(User user) {return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    // Fach erstellen
    public User createUser(String name, String username, String password, String role) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        return userRepository.save(user);
    }


}
