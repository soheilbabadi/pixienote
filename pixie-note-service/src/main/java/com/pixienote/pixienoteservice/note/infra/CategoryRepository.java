package com.pixienote.pixienoteservice.note.infra;

import com.pixienote.pixienoteservice.note.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Iterable<Category> findByTitleContains(String title);
    Iterable<Category> findByPersonId(long personId);

}
