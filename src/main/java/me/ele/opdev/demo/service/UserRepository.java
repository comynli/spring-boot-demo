package me.ele.opdev.demo.service;


import me.ele.opdev.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Created by xuemingli on 16/2/22.
 */
public interface UserRepository extends Repository<User, Long> {

    Page<User> findAll(Pageable pageable);
    User findByName(String name);
    User save(User user);

    @Query(value = "select user from User user where user.age <= 19")
    Page<User> getU19(Pageable pageable);

    @Modifying
    @Query(value = "update User user set user.age=?2 where user.id=?1")
    int updateAge(Long id, Integer age);
}
