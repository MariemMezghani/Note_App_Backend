package com.github.mariemmezghani.note_app_backend.database.repository

import com.github.mariemmezghani.note_app_backend.database.model.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository: MongoRepository<Note, ObjectId> {
    fun findByOwner(owner: ObjectId): List<Note>
}