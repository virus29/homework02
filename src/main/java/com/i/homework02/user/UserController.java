
package com.i.homework02.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

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

//    @RequestMapping("/user")
//    public User user(@RequestParam(value="login") String login, @RequestParam(value="login") String password, @RequestParam(value="login") String firstname) {
//        return new User(login,password,firstname);
//    }
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/api/user/list")
    Collection<User> users() {
        return this.userRepository.findAll();
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = this.userRepository.findOne(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}