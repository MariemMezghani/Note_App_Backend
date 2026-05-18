package com.github.mariemmezghani.note_app_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NoteAppBackendApplication

fun main(args: Array<String>) {
	runApplication<NoteAppBackendApplication>(*args)
}
