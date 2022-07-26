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

    public void init() {
        userRepository.save(new User( "Aylin Ayar", "Aylin", "1234"));
        userRepository.save(new User( "Nils Voß", "Nils", "1234"));
        userRepository.save(new User( "Marc Schümann", "Marc", "1234"));
        userRepository.save(new User( "Andreas Hoppe", "Andreas", "1234"));
    }
    public List<User> findAll() {return userRepository.findAll();
    }

    public User save(User user) {return userRepository.save(user);
    }

    // Fach erstellen
    public User createUser(String name, String username, String password) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }
}
