package com.pixienote.pixiepersonservice.person.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = -2772903331160848454L;
    @Id
    private int id;

    private String title;


    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Person.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Person> personList;


}
