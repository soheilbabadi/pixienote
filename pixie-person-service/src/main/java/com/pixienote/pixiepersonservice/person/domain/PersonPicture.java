package com.pixienote.pixiepersonservice.person.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class PersonPicture implements Serializable {
    @Serial
    private static final long serialVersionUID = -2956843318574125597L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Lob
    private byte[] fileContent;


    private long size;
    private String contentType;
    private String filename;
    private String pictureUrl;
    private LocalDateTime registerOn;
    private LocalDateTime updateOn;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Person.class)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;


}
