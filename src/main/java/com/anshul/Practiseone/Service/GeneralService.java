package com.anshul.Practiseone.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anshul.Practiseone.Entity.General;
import com.anshul.Practiseone.Entity.User;
import com.anshul.Practiseone.Repository.GeneralRepo;
import com.anshul.Practiseone.Repository.UserRepo;


@Service
public class GeneralService {

    @Autowired
    private GeneralRepo generalRepo;

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<General> createGeneralEntry(General general) {
        return ResponseEntity.ok(generalRepo.save(general));
    }

    public ResponseEntity<List<General>> getAllGeneralEntry() {
        List<General> generals = generalRepo.findAll();
        if(generals.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(generals);
    }

    public void createGeneralEntryInsideUser(General general, String username){
        User user  = userRepo.findByUserName(username);
        General saved = generalRepo.save(general);
        user.getGeneral().add(saved);
        userRepo.save(user);
    }

    public ResponseEntity<?> updateGeneralEntry(int id, General general) {
        Optional<General> optionalans = generalRepo.findById(id);
            if(optionalans.isPresent()){
                General ans = optionalans.get();
                ans.setTitle(general.getTitle() != null && !general.getContent().equals("") ? general.getTitle() : ans.getTitle());
                ans.setContent(general.getContent() != null && !general.getContent().equals("")? general.getContent() : ans.getContent());
                generalRepo.save(ans);
                return ResponseEntity.ok(ans);
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteGeneralEntryById(int id) {
        Optional<General> optionalans = generalRepo.findById(id);
            if(optionalans.isPresent()){
                generalRepo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteGeneralEntry(){
        generalRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
