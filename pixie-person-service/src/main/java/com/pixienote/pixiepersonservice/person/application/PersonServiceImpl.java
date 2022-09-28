package com.pixienote.pixiepersonservice.person.application;

import com.pixienote.pixiepersonservice.exception.LookupExeption;
import com.pixienote.pixiepersonservice.exception.PersonException;
import com.pixienote.pixiepersonservice.lookup.infra.LookupRepository;
import com.pixienote.pixiepersonservice.person.domain.Person;
import com.pixienote.pixiepersonservice.person.domain.Role;
import com.pixienote.pixiepersonservice.person.domain.RoleEnum;
import com.pixienote.pixiepersonservice.person.domain.dto.LoginDto;
import com.pixienote.pixiepersonservice.person.domain.dto.PersonDto;
import com.pixienote.pixiepersonservice.person.domain.dto.RoleDto;
import com.pixienote.pixiepersonservice.person.infra.PersonRepository;
import com.pixienote.pixiepersonservice.person.infra.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LookupRepository lookupRepository;


    @Override
    public PersonDto getPersonProfile(long personId) {
        var person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonException("Person not found"));

        return convertToDto(person);
    }

    @Override
    public List<RoleDto> getPersonRole(long personId) {
        var person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonException("Person not found"));
        var roleList = person.getRoleList();
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (var role : roleList) {
            var dto = RoleDto.builder().id(role.getId()).title(role.getTitle()).build();
            roleDtoList.add(dto);
        }
        return roleDtoList;
    }

    @Override
    public List<PersonDto> getPersonList(Pageable pageable) {
        var personList = personRepository.findAll(pageable);
        List<PersonDto> personDtoList = new ArrayList<>();
        for (var person : personList) {
            var dto = new PersonDto();
            dto = getPersonProfile(person.getId());
            personDtoList.add(dto);
        }
        return personDtoList;
    }

    @Override
    public List<PersonDto> getPersonList(String columnName, boolean descending) {
        Sort sort = descending ? Sort.by(Sort.Direction.DESC, columnName) : Sort.by(Sort.Direction.ASC, columnName);

        var personList = personRepository.findAll(sort);
        List<PersonDto> personDtoList = new ArrayList<>();
        for (var person : personList) {
            var dto = getPersonProfile(person.getId());
            personDtoList.add(dto);
        }
        return personDtoList;
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {

        var role = roleRepository.findById(RoleEnum.USER.getValue()).orElse(null);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        var person = new Person();
        person.setFullName(personDto.getFullName());
        person.setUsername(personDto.getUsername());
        person.setEmail(personDto.getEmail());
        person.setPhone(personDto.getPhone());
        person.setWebsite(personDto.getWebsite());
        person.setBio(personDto.getBio());
        person.setProfilePicture("https://picsum.photos/200");
        person.setAccountNonExpired(true);
        person.setAccountNonLocked(true);
        person.setCity(lookupRepository.findById(personDto.getCityId()).orElseThrow(() -> new LookupExeption("City not found")));
        person.setCountry(lookupRepository.findById(personDto.getCountryId()).orElseThrow(() -> new LookupExeption("Country not found")));
        person.setSex(lookupRepository.findById(personDto.getSexId()).orElseThrow(() -> new LookupExeption("Sex not found")));
        person.setEnabled(true);
        person.setLoginOn(LocalDateTime.now(ZoneOffset.UTC));
        person.setRegisterOn(LocalDateTime.now(ZoneOffset.UTC));
        person.setUpdateOn(LocalDateTime.now(ZoneOffset.UTC));
        person.setCredentialsNonExpired(true);
        person.setRoleList(roleList);

        return getPersonProfile(personRepository.save(person).getId());

    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) {
        var person = personRepository.findById(personDto.getId())
                .orElseThrow(() -> new PersonException("Person not found"));

        person.setFullName(personDto.getFullName());
        person.setUsername(personDto.getUsername());
        person.setEmail(personDto.getEmail());
        person.setPhone(personDto.getPhone());
        person.setWebsite(personDto.getWebsite());
        person.setBio(personDto.getBio());
        person.setProfilePicture(personDto.getProfilePicture());
        person.setAccountNonExpired(true);
        person.setAccountNonLocked(true);
        person.setUpdateOn(LocalDateTime.now(ZoneOffset.UTC));

        person.setCity(lookupRepository.findById(personDto.getCityId()).orElseThrow(() -> new LookupExeption("City not found")));
        person.setCountry(lookupRepository.findById(personDto.getCountryId()).orElseThrow(() -> new LookupExeption("Country not found")));
        person.setSex(lookupRepository.findById(personDto.getSexId()).orElseThrow(() -> new LookupExeption("Sex not found")));

        personRepository.save(person);

        return getPersonProfile(personDto.getId());
    }

    @Override
    public PersonDto login(LoginDto dto) {
        var person = personRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
                .orElseThrow(() -> new PersonException("Person not found"));
        return getPersonProfile(person.getId());

    }

    @Override
    public PersonDto getPublicProfile(long personId) {

        var dto = getPersonProfile(personId);

        if (!dto.isEnabled() || !dto.isAccountNonExpired() || !dto.isAccountNonLocked() || !dto.isCredentialsNonExpired()) {
            throw new PersonException("Person credentials not valid");
        }

        dto.setPhone(null);
        dto.setLoginOn(null);
        dto.setUpdateOn(null);
        dto.setEmail(null);

        return dto;
    }

    @Override
    public void deletePerson(long personId) {
        personRepository.deleteById(personId);
    }

    private PersonDto convertToDto(Person person) {

        return PersonDto.builder().accountNonExpired(person.isAccountNonExpired())
                .bio(person.getBio())
                .cityId(person.getCity().getId())
                .cityTitle(person.getCity().getTitle())
                .countryId(person.getCountry().getId())
                .countryTitle(person.getCountry().getTitle())
                .credentialsNonExpired(person.isCredentialsNonExpired())
                .enabled(person.isEnabled())
                .fullName(person.getFullName())
                .id(person.getId())
                .loginOn(person.getLoginOn())
                .phone(person.getPhone())
                .profilePicture(person.getProfilePicture())
                .updateOn(person.getUpdateOn())
                .username(person.getUsername())
                .email(person.getEmail())
                .registerOn(person.getRegisterOn())
                .website(person.getWebsite())
                .roleList(getPersonRole(person.getId()))
                .accountNonLocked(person.isAccountNonLocked())
                .enabled(person.isEnabled())
                .profilePicture(person.getProfilePicture())
                .sexId(person.getSex().getId())
                .sexTitle(person.getSex().getTitle())
                .build();
    }


}
