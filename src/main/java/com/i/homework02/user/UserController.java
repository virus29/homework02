
package com.i.homework02.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class UserController {
//private final UserRepository userRepository;
//
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @RequestMapping("/user")
//    public Iterable<User> getUsers() {
//        return userRepository.findAll();
//    }
//}
@RestController
public class UserController {

    @RequestMapping("/user")
    public User user(@RequestParam(value="login") String login, @RequestParam(value="login") String password, @RequestParam(value="login") String firstname) {
        return new User(login,password,firstname);
    }
}