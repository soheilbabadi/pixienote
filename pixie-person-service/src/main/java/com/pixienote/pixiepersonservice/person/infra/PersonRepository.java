package com.pixienote.pixiepersonservice.person.infra;

import com.pixienote.pixiepersonservice.lookup.domain.Lookup;
import com.pixienote.pixiepersonservice.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

    Optional<Person> findByPhone(String phone);

    Optional<Person> findByUsername(String username);

    Iterable<Person> findByFullNameContaining(String name);

    Iterable<Person> findByCountry(Lookup country);

    Iterable<Person> findByCityOrCountry(Lookup city, Lookup country);

    @Query(value = "select p from Person p where p.country = ?1 or p.city = ?1")
    Iterable<Person> search(String name);

    Optional<Person> findByEmailAndPassword(String email, String password);


}
