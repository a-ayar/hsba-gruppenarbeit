package de.hsba.bi.demo.user;

import de.hsba.bi.demo.subject.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {return userRepository.findAll();}

    public List<User> findAllAdmins() {return userRepository.findByRole("Admin");}
    public List<User> findAllTeacher() {
        List<User> teachers = userRepository.findByRole("Lehrer");
        teachers.forEach(teacher -> System.out.println("Teacher Id: " + teacher.getId()));
        return teachers;

    }
    public List<User> findAllStudents() {return userRepository.findByRole("Schüler");}

    public User save(User user) {return userRepository.save(user);
    }
    //public List<User> findUsers() {return userRepository.findByRole(User.USER_ROLE);}
//    public User findCurrentUser() {return userRepository.findByName(User.getCurrentUsername());}


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
