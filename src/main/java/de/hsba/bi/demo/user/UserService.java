package de.hsba.bi.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    //löschen - Aylin
    public void delete(Long id) { userRepository.deleteById(id);};
    //Datenbankabfragen - Aylin
    public List<User> findAll() {return userRepository.findAll();}
    public List<User> findAllAdmins() {return userRepository.findByRole("ADMIN");}
    public List<User> findAllTeacher() {return userRepository.findByRole("LEHRER");}
    public List<User> findAllStudents() {return userRepository.findByRole("SCHÜLER");}
    public User getUser(Long id) {return userRepository.findById(id).orElse(null);}
    //Speichern - Aylin
    public User save(User user) {return userRepository.save(user);
    }

    public User findCurrentUser() {return userRepository.findByUsername(User.getCurrentUsername());}

}
