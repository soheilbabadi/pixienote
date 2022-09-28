package com.pixienote.pixienoteservice.note.infra;

import com.pixienote.pixienoteservice.note.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {
    Iterable<Note> findByTitleContains(String title);
    Iterable<Note> findByPersonId(long personId);
}
