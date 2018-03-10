//
//package com.i.homework02.user;
//
//import com.fasterxml.jackson.annotation.JsonView;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
////@RestController
////public class UserController {
////private final UserRepository userRepository;
////
////    public UserController(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
////
////    @RequestMapping("/user")
////    public Iterable<User> getUsers() {
////        return userRepository.findAll();
////    }
////}
//@RestController
//@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
//public class UserController {
//
//    final Logger logger = LoggerFactory.getLogger(UserController.class);
//
//    @Autowired
//    UserService userService;
//
//    @JsonView({UserView.FindByOffidFNameLNameMnamePositionDoccodeCitcode.class})
//    @PostMapping(value = "/list")
//    @ResponseStatus(HttpStatus.FOUND)
//    public @ResponseBody
//    List<User> searchUser(@RequestBody @Valid User user){
//        logger.info(user.toString());
//        return userService.searchUser(user.getOffice().getId(), user.getUserFirstname(), user.getUserLastname(), user.getUserMiddlename(), user.getUserPosition(), user.getDocument().getDocumentCode(), user.getCountry().getCountryCode());
//    }
//
//    @JsonView(UserView.OfficeFindById.class)
//    @GetMapping(path = "/{id}")
//    @ResponseStatus(HttpStatus.FOUND)
//    public @ResponseBody
//    User findOfficeById(@PathVariable Long id) {
//        return userService.findById(id);
//    }
//}