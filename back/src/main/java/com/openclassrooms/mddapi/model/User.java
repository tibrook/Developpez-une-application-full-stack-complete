package com.openclassrooms.mddapi.model;

import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Entity
@Data
@Table(name = "Users")
public class User {
	
    @Id
   /** La clé primaire est générée automatiquement **/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;
    
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

}