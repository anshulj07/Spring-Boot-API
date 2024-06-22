package com.anshul.Practiseone.Entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Data
@Document(collection = "Users")
public class User {
    private int id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    @DBRef
    private List<General> general = new ArrayList<>();
}
