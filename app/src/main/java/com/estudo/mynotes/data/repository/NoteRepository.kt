package com.estudo.mynotes.data.repository

import com.estudo.mynotes.data.dao.NoteDao
import com.estudo.mynotes.data.entities.NoteEntity
import com.estudo.mynotes.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun insertNote(note: Note) {
        val noteEntity = NoteEntity(
                title = note.title,
                description = note.description,
                date = note.date
        )

        noteDao.insert(noteEntity)
    }
}