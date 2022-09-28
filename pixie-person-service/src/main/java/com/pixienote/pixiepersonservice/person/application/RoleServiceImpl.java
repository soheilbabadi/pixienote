package com.pixienote.pixiepersonservice.person.application;

import com.pixienote.pixiepersonservice.exception.RoleException;
import com.pixienote.pixiepersonservice.person.domain.Person;
import com.pixienote.pixiepersonservice.person.domain.Role;
import com.pixienote.pixiepersonservice.person.domain.dto.RoleDto;
import com.pixienote.pixiepersonservice.person.infra.PersonRepository;
import com.pixienote.pixiepersonservice.person.infra.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PersonRepository personRepository;


    @Override
    public List<RoleDto> getPersonRoles(long personId) {
        var personList = new ArrayList<Person>();
        var person = personRepository.findById(personId)
                .orElseThrow(() -> new RoleException("Person not found"));

        personList.add(person);

        List<RoleDto> dtoList = new ArrayList<>();
        var list = roleRepository.findByPersonList(personList);
        for (var role : list) {
            var roleDto = RoleDto.builder().id(role.getId()).title(role.getTitle()).build();
            dtoList.add(roleDto);
        }
        return dtoList;
    }

    @Override
    public RoleDto addRole(RoleDto roleDto) {
        var role = Role.builder().title(roleDto.getTitle()).build();
        roleRepository.save(role);
        return roleDto;
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        var role = roleRepository.findById(roleDto.getId())
                .orElseThrow(() -> new RoleException("Role not found"));
        role.setTitle(roleDto.getTitle());
        roleRepository.save(role);
        return roleDto;
    }

    @Override
    public void deleteRole(int roleId) {
        var role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleException("Role not found"));
        roleRepository.delete(role);
    }

    @Override
    public long countAll() {
        return roleRepository.count();
    }


    @Override
    public List<RoleDto> getAll() {

        var roles = roleRepository.findAll();
        var dtoList = new ArrayList<RoleDto>();


        for (var role : roles) {
            var roleDto = RoleDto.builder().id(role.getId()).title(role.getTitle()).build();
            dtoList.add(roleDto);
        }
        return dtoList;
    }

}
