package uz.online.springbootpractica.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.online.springbootpractica.domein.User;
import uz.online.springbootpractica.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user) {
        if (!checkPasswordLength(user.getPassword())) {
            return new ResponseEntity<>("Parol uzunligi 4 tadan kam", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserName(user.getUserName())) {
            return new ResponseEntity<>("Bu user oldin royhatdan otgan", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.create(user));
    }

    private Boolean checkPasswordLength(String password) {
        return password.length() >= 4;
    }
}
