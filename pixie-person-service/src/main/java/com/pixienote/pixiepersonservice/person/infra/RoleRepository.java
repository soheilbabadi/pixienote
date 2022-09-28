package com.pixienote.pixiepersonservice.person.infra;

import com.pixienote.pixiepersonservice.person.domain.Person;
import com.pixienote.pixiepersonservice.person.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {


    Iterable<Role> findByTitleContains(String title);

    Iterable<Role> findByPersonList(List<Person> personList);


}
