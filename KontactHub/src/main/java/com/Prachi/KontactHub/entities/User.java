package com.Prachi.KontactHub.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {

    @Id
    private String userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    @Column(length=1000)
    private String about;

    @Column(length=1000)
    private String ProfileImg;
    private String phoneNumber;

    private boolean enabled = false;
    private boolean emailVerified=false;
    private boolean phoneVerified = false;

    //self,google,fb,etc

    private Providers provider=Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user",cascade= CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();



}
