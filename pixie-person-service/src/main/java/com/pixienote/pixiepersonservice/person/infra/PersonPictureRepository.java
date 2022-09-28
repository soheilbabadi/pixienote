package com.pixienote.pixiepersonservice.person.infra;


import com.pixienote.pixiepersonservice.person.domain.Person;
import com.pixienote.pixiepersonservice.person.domain.PersonPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonPictureRepository extends JpaRepository<PersonPicture, UUID> {

    Optional<PersonPicture> findByPerson(Person person);


}
