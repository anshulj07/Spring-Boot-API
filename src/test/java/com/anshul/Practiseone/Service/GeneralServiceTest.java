package com.anshul.Practiseone.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.anshul.Practiseone.Entity.General;
import com.anshul.Practiseone.Repository.GeneralRepo;

@SpringBootTest
public class GeneralServiceTest {

    @Mock
    private GeneralRepo generalRepo;

    @InjectMocks
    private GeneralService generalService;

    private General existingGeneral;
    private General updatedGeneral;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize sample General objects
        existingGeneral = new General();
        existingGeneral.setId(1);
        existingGeneral.setTitle("Existing Title");
        existingGeneral.setContent("Existing Content");

        updatedGeneral = new General();
        updatedGeneral.setTitle("Updated Title");
        updatedGeneral.setContent("Updated Content");
    }

    @Test
    public void testUpdateGeneralEntry_GeneralFound() {
        when(generalRepo.findById(anyInt())).thenReturn(Optional.of(existingGeneral));
        when(generalRepo.save(any(General.class))).thenReturn(existingGeneral);

        ResponseEntity<?> response = generalService.updateGeneralEntry(1, updatedGeneral);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        General result = (General) response.getBody();
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Content", result.getContent());
    }

    @Test
    public void testUpdateGeneralEntry_GeneralNotFound() {
        when(generalRepo.findById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<?> response = generalService.updateGeneralEntry(1, updatedGeneral);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}

