package me.ele.opdev.demo.api;

import me.ele.opdev.demo.domain.User;
import me.ele.opdev.demo.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xuemingli on 16/2/22.
 */

@RestController
public class UserController {

    @Autowired
    private Userservice userservice;

    @RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public User add(@RequestBody @Validated User user) {
        return userservice.save(user);
    }

    @RequestMapping(value = "/user/get/{name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User findByName(@PathVariable(value = "name") String name) {
        return userservice.findByName(name);
    }
}
