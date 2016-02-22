package me.ele.opdev.demo.service;


import me.ele.opdev.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

/**
 * Created by xuemingli on 16/2/22.
 */
public interface UserRepository extends Repository<User, Long> {

    Page<User> findAll(Pageable pageable);
    User findByName(String name);
    User save(User user);
}
