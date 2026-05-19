package com.github.mariemmezghani.note_app_backend.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.security.Timestamp

@Document(collection = "notes")
data class Note(
    val title: String,
    val content: String,
    val color: String,
    val timeStamp: Timestamp,
    val owner: ObjectId,
    @Id val id : ObjectId= ObjectId.get()
)
