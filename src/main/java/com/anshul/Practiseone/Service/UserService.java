package com.anshul.Practiseone.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anshul.Practiseone.Entity.General;
import com.anshul.Practiseone.Entity.User;
import com.anshul.Practiseone.Repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GeneralService generalService;

    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepo.findAll());
    }
    
    public ResponseEntity<?> getUsersWithGeneral(String username){
        User user = userRepo.findByUserName(username);
        if(user != null){
            List<General> generals = user.getGeneral();
            if(generals != null && !generals.isEmpty()) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> createUser(User user){
        try{
            return ResponseEntity.ok(userRepo.save(user));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<?> createUserWithGeneral(General general, String username){
        try{
            generalService.createGeneralEntryInsideUser(general, username);
            return new ResponseEntity<>(general, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<User> getUserWithId(ObjectId id){
       Optional<User> optionalans = userRepo.findById(id);
       if(optionalans.isPresent()){
            User ans = optionalans.get();
            return new ResponseEntity<>(ans, HttpStatus.OK);
       }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updateUser(User user, String username) {
       User updateUser = userRepo.findByUserName(username);
       if(updateUser != null){
            updateUser.setUserName(user.getUserName() != null && !user.getUserName().equals("") ? user.getUserName() : updateUser.getUserName());
            updateUser.setPassword(user.getPassword() != null && !user.getPassword().equals("") ? user.getPassword() : updateUser.getPassword());
            userRepo.save(updateUser);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteGeneralFromUser(String username, int id){
        User user = userRepo.findByUserName(username);
        if(user != null){
            user.getGeneral().removeIf(x -> x.getId() == id);
            userRepo.save(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<?> updateGeneralInUser(String username, int id){
        User user = userRepo.findByUserName(username);
        if(user != null){
            ResponseEntity<General> responseEntity = generalService.getGeneralEntry(id);
            if(responseEntity.getStatusCode() == HttpStatus.OK){
                General general = responseEntity.getBody();
                user.getGeneral().add(general);
                userRepo.save(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
