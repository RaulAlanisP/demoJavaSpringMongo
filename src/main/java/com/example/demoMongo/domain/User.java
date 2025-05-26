package com.example.demoMongo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class User {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
