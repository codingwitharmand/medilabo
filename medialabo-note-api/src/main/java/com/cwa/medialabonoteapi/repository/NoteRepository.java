package com.cwa.medialabonoteapi.repository;

import com.cwa.medialabonoteapi.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByPatientId(Long id);
}
