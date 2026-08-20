package org.example.examenfinalprog2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String ref;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
