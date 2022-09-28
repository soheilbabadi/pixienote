package com.pixienote.pixienoteservice.note.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 2207076860617555849L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(columnDefinition = "varchar(100)", length = 100, nullable = false)
    private String title;
    @Column(columnDefinition = "varchar(100)", length = 100, nullable = false)
    private String personId;
}
