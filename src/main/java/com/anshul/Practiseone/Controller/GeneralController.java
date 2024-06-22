package com.anshul.Practiseone.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anshul.Practiseone.Entity.General;
import com.anshul.Practiseone.Repository.GeneralRepo;

@RestController
@RequestMapping("/general")
public class GeneralController {
        @Autowired
        private GeneralRepo generalRepo;

        @PostMapping
        public ResponseEntity<General> createGeneralEntry(@RequestBody General general){
            return ResponseEntity.ok(generalRepo.save(general));
        }

        @GetMapping
        public ResponseEntity<List<General>> getAllGeneralEntry(){
            List<General> generals = generalRepo.findAll();
            if(generals.isEmpty()) return ResponseEntity.notFound().build();
            else return ResponseEntity.ok(generals);
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> updateGeneralEntry(@PathVariable int id, @RequestBody General general){
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

        @DeleteMapping("{id}")
        public ResponseEntity<?> deleteGeneralEntryById(@PathVariable int id){
            Optional<General> optionalans = generalRepo.findById(id);
            if(optionalans.isPresent()){
                generalRepo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @DeleteMapping
        public ResponseEntity<String> deleteGeneralEntry(){
            generalRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }
}
