package com.pixienote.pixienoteservice.note.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Note implements Serializable {

    @Serial
    private static final long serialVersionUID = -205779047523248611L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition = "varchar(100)", length = 100, nullable = false)
    private String title;
    @Column(columnDefinition = "varchar(2000)", length = 2000)
    private String content;
    @Column(columnDefinition = "varchar(100)", length = 100, nullable = false)
    private String personId;
    @Column(columnDefinition = "varchar(100)", length = 100, nullable = false)
    private String personName;
    @Column( nullable = false)
    private LocalDateTime createdOn;
    @Column( nullable = false)
    private LocalDateTime updatedOn;

    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Category.class)
    private Category category;



}
