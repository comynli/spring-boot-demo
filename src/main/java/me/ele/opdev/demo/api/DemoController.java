package me.ele.opdev.demo.api;

import me.ele.opdev.demo.domain.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xuemingli on 16/2/22.
 */

@RestController
public class DemoController {

    @RequestMapping(value = "/demo", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public User demo(@RequestBody @Validated User user) {
        user.setAge(18);
        return user;
    }
}
