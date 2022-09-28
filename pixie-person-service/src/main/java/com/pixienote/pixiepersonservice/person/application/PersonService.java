package com.pixienote.pixiepersonservice.person.application;

import com.pixienote.pixiepersonservice.person.domain.dto.LoginDto;
import com.pixienote.pixiepersonservice.person.domain.dto.PersonDto;
import com.pixienote.pixiepersonservice.person.domain.dto.RoleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    PersonDto getPersonProfile(long personId);

    List<RoleDto> getPersonRole(long personId);

    List<PersonDto> getPersonList(Pageable pageable);

    List<PersonDto> getPersonList(String columnName, boolean descending);

    PersonDto createPerson(PersonDto personDto);

    PersonDto updatePerson(PersonDto personDto);

    PersonDto login(LoginDto dto);

    PersonDto getPublicProfile(long personId);

    void deletePerson(long personId);

}
