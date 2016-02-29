package me.ele.opdev.demo.service;

import me.ele.opdev.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuemingli on 16/2/22.
 */

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Page<User> getU19(Pageable pageable) {
        return userRepository.getU19(pageable);
    }

    public int updateAge(User user, int age) {
        return userRepository.updateAge(user.getId(), age);
    }

}
