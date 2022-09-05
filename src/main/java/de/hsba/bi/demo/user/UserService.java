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

    //Methoden für verschiedene Funktionen -Aylin
    //löschen - Aylin
    public void delete(Long id) { userRepository.deleteById(id);};
    //Datenbankabfragen - Aylin
    public List<User> findAll() {return userRepository.findAll();}
    public List<User> findAllAdmins() {return userRepository.findByRole("Admin");}
    public List<User> findAllTeacher() {return userRepository.findByRole("Lehrer");}
    public List<User> findAllStudents() {return userRepository.findByRole("Schüler");}
    public User getUser(Long id) {return userRepository.findById(id).orElse(null);}
    //Speichern - Aylin
    public User save(User user) {return userRepository.save(user);
    }
    //User Registrieren - Aylin
    public User createUser(String name, String username, String password, String role) {
        User user = new User(name, username, password, role);
        return userRepository.save(user);
    }
    //User in bearbeitungszustand setzen - Aylin
    public void editUser(Long userId){
        User user = getUser(userId);
        user.setUserIsOnEdit(true);
    }
    //Due User Bearbeitugn abbrechen - Aylin
    public void abortEditUser(Long userId){
        User user = getUser(userId);
        user.setUserIsOnEdit(false);
    }
    //Bearbeiteten User anlegen - Aylin
    public void saveNewUser(Long userId, String newName, String newUsername, String newPassword, String newRole){
        User user = getUser(userId);
        user.setName(newName);
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setRole(newRole);
        user.setUserIsOnEdit(false);
    }


}
