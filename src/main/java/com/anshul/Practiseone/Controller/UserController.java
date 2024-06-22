package com.anshul.Practiseone.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anshul.Practiseone.Entity.General;
import com.anshul.Practiseone.Entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.anshul.Practiseone.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired 
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
      return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUsersWithGeneral(@PathVariable String username){
      return userService.getUsersWithGeneral(username);
    }

    @GetMapping("/id/{id}") 
    public ResponseEntity<User> getUserWithId(@PathVariable ObjectId id){
        return userService.getUserWithId(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> createUserWithGeneral(@RequestBody General general, @PathVariable String username){
        return userService.createUserWithGeneral(general, username);
    }
    
    @PutMapping("/{username}") 
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username){
        return userService.updateUser(user, username);
    }
}
