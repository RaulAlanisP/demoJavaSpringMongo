package com.example.demoMongo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
