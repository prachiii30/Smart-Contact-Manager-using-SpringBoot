package com.Prachi.KontactHub.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Contact {

    @Id
    private String id;
    private String name;
    private  String email;
    private String phNumber;
    private String address;
    private String picture;
    private String description;
    private boolean favourite = false;

//    private List<String> socialLinks =new ArrayList<>();
    private String instagramLink;
    private String facebookLink;

   @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact",cascade= CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> links=new ArrayList<>();

}
