package com.pixienote.pixiepersonservice.person.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class RoleDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -8277319403629804008L;

    private int id;
    private String title;

}
