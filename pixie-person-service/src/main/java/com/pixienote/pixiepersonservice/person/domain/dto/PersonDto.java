package com.pixienote.pixiepersonservice.person.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8205442428587160521L;

    private long id;

    private String fullName;

    private String username;

    private String email;

    private String phone;

    private long cityId;
    private String cityTitle;

    private long countryId;
    private String countryTitle;

    private long sexId;
    private String sexTitle;

    private String profilePicture;

    private String bio;

    private String website;

    private List<RoleDto> roleList;

    private boolean enabled;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;

    private boolean accountNonExpired;

    private LocalDateTime registerOn;

    private LocalDateTime loginOn;

    private LocalDateTime updateOn;

}
