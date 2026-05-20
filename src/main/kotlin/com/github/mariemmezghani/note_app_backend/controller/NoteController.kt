package com.github.mariemmezghani.note_app_backend.controller

import com.github.mariemmezghani.note_app_backend.database.model.Note
import com.github.mariemmezghani.note_app_backend.database.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

// POST http://8080:localhost/notes
// POST http://8080:localhost/notes?ownerId=123
@RestController
@RequestMapping("/notes")
class NoteController(val repository: NoteRepository) {

    data class NoteRequest(
        val id: String,
        val ownerId: String,
        val title: String,
        val content: String,
        val color: String
    )

    data class NoteResponse(
        val id: String,
        val title: String,
        val content: String,
        val color: String,
        val timeStamp: Instant
    )

    @PostMapping
    fun saveNote(body: NoteRequest): NoteResponse {
        val note = Note(
            id = body.id?.let { ObjectId(it) } ?: ObjectId(),
            title = body.title,
            content = body.content,
            color = body.color,
            timeStamp = Instant.now(),
            owner = ObjectId(body.ownerId)
        )
        repository.save(note)
        return note.toNoteResponse()
    }

    @GetMapping
    fun getNotesByOwnerId(@RequestParam(required = true) ownerId: String): List<NoteResponse> {
        return repository.findByOwner(ObjectId(ownerId)).map {
            it.toNoteResponse()
        }
    }

    private fun Note.toNoteResponse(): NoteResponse {
        return NoteResponse(
            id = id.toHexString(),
            title = title,
            content = content,
            color = color,
            timeStamp = timeStamp
        )
    }
}