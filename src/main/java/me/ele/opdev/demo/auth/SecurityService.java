package me.ele.opdev.demo.auth;

import me.ele.opdev.demo.domain.Group;
import me.ele.opdev.demo.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by xuemingli on 16/3/7.
 */

@Service
public class SecurityService {

    public User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean hasRole(String... roles) {
        Set<String> require = new HashSet<>(Arrays.asList(roles));
        require.stream().forEach(System.out::println);
        User user = getPrincipal();
        Set<String> mine = user.getGroups().stream().map(Group::getName).collect(Collectors.toSet());
        mine.stream().forEach(System.out::println);
        for(String req: require) {
            if (mine.contains(req)) {
                return true;
            }
        }
        return false;
    }
}
