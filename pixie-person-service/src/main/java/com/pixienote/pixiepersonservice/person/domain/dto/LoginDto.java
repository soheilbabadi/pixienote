package com.pixienote.pixiepersonservice.person.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class LoginDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -940369510756621496L;
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
