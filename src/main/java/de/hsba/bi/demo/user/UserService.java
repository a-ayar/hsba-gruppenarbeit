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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findByRole(User.USER_ROLE);
    }

    public User save(User user) {
        return UserRepository.save(user);
    }

    public User findCurrentUser() {
        return userRepository.findByName(User.getCurrentUserName());
    }
}
