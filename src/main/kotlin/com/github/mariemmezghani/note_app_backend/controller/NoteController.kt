package com.github.mariemmezghani.note_app_backend.controller

import com.github.mariemmezghani.note_app_backend.database.model.Note
import jakarta.annotation.Resources
import org.bson.types.ObjectId
import org.osgi.annotation.bundle.Requirement
import org.springframework.data.mongodb.core.aggregation.MergeOperation.UniqueMergeId.id
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Timestamp
import kotlin.time.Clock
import kotlin.time.Instant

// POST http:localhost:8080/notes

@RestController
@RequestMapping("/notes")
class NoteController {

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
        val timeStamp: Timestamp
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
        return NoteResponse(
            id= note.id.toHexString(),
            title = note.title,
            content = note.content,
            color = note.color,
            timeStamp = note.timeStamp,
        )
    }
}