package com.pixienote.pixiepersonservice.lookup.infra;

import com.pixienote.pixiepersonservice.lookup.domain.Lookup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LookupRepository extends CrudRepository<Lookup, Long> {

    Iterable<Lookup> findByParentIdOrderBySortOrder(long parentId);

    Iterable<Lookup> findByGroupIdOrderBySortOrder(long groupId);

    Optional<Lookup> findByValueOrderBySortOrder(String value);

    Optional<Lookup> findByTitleOrderBySortOrder(String title);

    Iterable<Lookup> findByParentTitleContains(String title);

    Iterable<Lookup> findByGroupTitleContains(String title);


    @Query(value = "select l from Lookup l where l.title like %?1% or l.parentTitle like %?1% or l.groupTitle like %?1%")
    Iterable<Lookup> search(String title);


    long countByParentId(long parentId);

    long countByGroupId(long groupId);


}
