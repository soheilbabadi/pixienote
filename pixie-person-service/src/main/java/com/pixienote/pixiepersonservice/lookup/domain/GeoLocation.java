package com.pixienote.pixiepersonservice.lookup.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class GeoLocation implements Serializable {
    @Serial
    private static final long serialVersionUID = 7769862434367462726L;

    @Id
    private long id;
    private String title;
    private long parentId;
    private float latitude;
    private float longitude;
    private long population;

}
