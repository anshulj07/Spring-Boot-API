package com.anshul.Practiseone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.anshul.Practiseone.Service.GeneralService;

@RestController
@RequestMapping("/general")
public class GeneralController {

        @Autowired
        private GeneralService generalService;

        @PostMapping
        public ResponseEntity<General> createGeneralEntry(@RequestBody General general){
            return generalService.createGeneralEntry(general);
        }

        @GetMapping
        public ResponseEntity<List<General>> getAllGeneralEntry(){
            return generalService.getAllGeneralEntry();
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> updateGeneralEntry(@PathVariable int id, @RequestBody General general){
            return generalService.updateGeneralEntry(id, general);
        }

        @DeleteMapping("{id}")
        public ResponseEntity<?> deleteGeneralEntryById(@PathVariable int id){
            return generalService.deleteGeneralEntryById(id);
        }

        @DeleteMapping
        public ResponseEntity<?> deleteGeneralEntry(){
            return generalService.getAllGeneralEntry();
        }
}
