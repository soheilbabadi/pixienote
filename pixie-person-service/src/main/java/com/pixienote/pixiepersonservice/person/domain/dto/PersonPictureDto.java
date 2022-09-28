package com.pixienote.pixiepersonservice.person.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class PersonPictureDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -1978728915146951723L;
    private UUID id;

    @Lob
    private byte[] fileContent;

    private long size;
    private String contentType;
    private String filename;
    private String pictureUrl;
    private LocalDateTime registerOn;
    private LocalDateTime updateOn;

    private long personId;
    private String personName;

}
