package com.pixienote.pixiepersonservice.person.application;

import com.pixienote.pixiepersonservice.person.domain.Role;

import com.pixienote.pixiepersonservice.person.domain.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getPersonRoles(long personId);

    RoleDto addRole(RoleDto roleDto);

    RoleDto updateRole(RoleDto roleDto);

    void deleteRole(int roleId);

    long countAll();

    List<RoleDto> getAll();

}
