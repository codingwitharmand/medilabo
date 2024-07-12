package com.cwa.medialabonoteapi.controller;

import com.cwa.medialabonoteapi.entity.Note;
import com.cwa.medialabonoteapi.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteRepository noteRepository;

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return ResponseEntity.ok(noteRepository.save(note));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Note>> getNoteByPatientId(@PathVariable Long id) {
        return ResponseEntity.ok(noteRepository.findByPatientId(id));
    }
}
