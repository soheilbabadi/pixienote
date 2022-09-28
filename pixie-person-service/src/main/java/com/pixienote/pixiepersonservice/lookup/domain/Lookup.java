package com.pixienote.pixiepersonservice.lookup.domain;

import com.pixienote.pixiepersonservice.person.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Lookup implements Serializable {
    @Serial
    private static final long serialVersionUID = 5460764138888811982L;

    @Id
    private long id;


    @Column(columnDefinition = "varchar(100)", length = 100, unique = true, nullable = false)
    private String value;

    @Column(columnDefinition = "varchar(100)", length = 100, unique = true, nullable = false)
    private String title;

    private int sortOrder;

    private long parentId;
    private String parentTitle;

    private long groupId;
    private String groupTitle;


    @OneToMany(mappedBy = "sex", fetch = FetchType.LAZY, targetEntity = Person.class, cascade = CascadeType.ALL)
    private Set<Lookup> sexSet;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, targetEntity = Person.class, cascade = CascadeType.ALL)
    private Set<Lookup> citySet;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY, targetEntity = Person.class, cascade = CascadeType.ALL)
    private Set<Lookup> countrySet;

    @OneToMany(mappedBy = "timeZone", fetch = FetchType.LAZY, targetEntity = Person.class, cascade = CascadeType.ALL)
    private Set<Lookup> timeZoneSet;


}
