package com.anshul.Practiseone.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.anshul.Practiseone.Entity.General;
import com.anshul.Practiseone.Repository.GeneralRepo;
import com.anshul.Practiseone.Repository.UserRepo;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;

    @Mock
    private GeneralRepo generalRepo;

    @InjectMocks
    private GeneralService generalService;

    private General general;

    @ParameterizedTest
    @ValueSource(strings = {
        "Anshul",
        "Yoag"
    })
    public void testFindByUserName(String username){
        assertEquals(4, 2+2);
        assertNotNull(userRepo.findByUserName(username));
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "10,2,12"
    })

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        general = new General();
        general.setId(3);
        general.setTitle("From JUnit");
        general.setContent("Test from Junit");
    }

    @SuppressWarnings("deprecation")
    @Test
    public void createGeneralEntry(){
        when(generalRepo.save(any(General.class))).thenReturn(general);

        ResponseEntity<General> response = generalService.createGeneralEntry(general);

        assertEquals(general, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}
