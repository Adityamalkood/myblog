package com.Pratice_Api.Blog.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "username",unique = true,nullable = false)
    private String username;
    @Column(name = "email",unique = true,nullable = false)
    private  String email;
    private  String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
     @JoinTable(name="user_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
             inverseJoinColumns =@JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
