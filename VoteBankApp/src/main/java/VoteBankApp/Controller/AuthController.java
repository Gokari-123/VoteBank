package VoteBankApp.Controller;


import VoteBankApp.Entity.User;
import VoteBankApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
     return userRepository.findAll();
    }
}
