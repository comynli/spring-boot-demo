package me.ele.opdev.demo.api;

import me.ele.opdev.demo.config.ConnectSetting;
import me.ele.opdev.demo.domain.User;
import me.ele.opdev.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

/**
 * Created by xuemingli on 16/2/22.
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserController {

    @Value("${demo.page.size:50}")
    private Integer pageSize;

    @Autowired
    private ConnectSetting connectSetting;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public User add(@RequestBody @Validated User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    @ResponseBody
    public User findByName(@PathVariable(value = "name") String name) {
        return userService.findByName(name);
    }

    @RequestMapping(value = "/u19", method = RequestMethod.GET)
    @ResponseBody
    public Page<User> getU19() {
        Pageable pageable = new PageRequest(0, pageSize);
        return userService.getU19(pageable);
    }

    @RequestMapping(value = "/age/{age}", method = RequestMethod.PATCH)
    @ResponseBody
    public User changeAge(@PathVariable(value = "age") Integer age,
            @RequestParam(name = "user", required = true) String username) throws ServletException {
        try{
            User user = userService.findByName(username);
            userService.updateAge(user, age);
            return userService.findByName(username);
        }catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
        }

    }

    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    @ResponseBody
    public String getConnectSetting() {
        return connectSetting.getHost();
    }
}
