package com.pixienote.pixiepersonservice.person.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
//@Entity
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = -3641240247652678544L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition = "varchar(100)", length = 100, nullable = false)
    private String fullName;

    @Column(columnDefinition = "varchar(200)", length = 200, unique = true, nullable = false)
    private String username;

    @Column(columnDefinition = "varchar(100)", length = 100, nullable = false)
    @Email
    private String email;

    @Column(columnDefinition = "varchar(500)", length = 500, nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(20)", length = 20, nullable = false, unique = true)
    private String phone;

    @Column(columnDefinition = "varchar(200)", length = 200, nullable = false)
    @URL
    private String profilePicture;

    @Column(columnDefinition = "varchar(2000)", length = 2000, nullable = false)
    private String bio;

    @Column(columnDefinition = "varchar(100)", length = 100, nullable = false)
    @URL
    private String website;


    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private boolean credentialsNonExpired;

    @Column(nullable = false)
    private boolean accountNonLocked;

    @Column(nullable = false)
    private boolean accountNonExpired;

    @Column(nullable = false)
    private LocalDateTime registerOn;

    @Column(nullable = false)
    private LocalDateTime loginOn;

    @Column(nullable = false)
    private LocalDateTime updateOn;


    @ManyToMany(targetEntity = Role.class, mappedBy = "personList", cascade = CascadeType.ALL)
    private List<Role> roleList;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Lookup.class, cascade = CascadeType.ALL)
    private Lookup city;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Lookup.class, cascade = CascadeType.ALL)
    private Lookup country;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Lookup.class, cascade = CascadeType.ALL)
    @JoinColumn()
    private Lookup sex;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Lookup.class, cascade = CascadeType.ALL)
    @JoinColumn()
    private Lookup timeZone;


    @OneToMany(mappedBy = "person", targetEntity = PersonPicture.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PersonPicture> personPictureList;

}
