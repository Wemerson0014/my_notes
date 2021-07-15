package com.estudo.mynotes.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.estudo.mynotes.data.dao.NoteDao
import com.estudo.mynotes.data.entities.NoteEntity
import com.estudo.mynotes.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note) {
        val noteEntity = NoteEntity(
            title = note.title,
            description = note.description,
            date = note.date
        )

        noteDao.insert(noteEntity)
    }

    suspend fun deleteNote(note: Note) {
        val noteEntity = NoteEntity(
            id = note.id,
            title = note.title,
            description = note.description,
            date = note.date
        )

        noteDao.delete(noteEntity)
    }

    suspend fun updateNote(note: Note) {
        noteDao.update(note.id, note.title, note.description)
    }

    fun search(searchQuery: String): LiveData<List<Note>> {
        return noteDao.getSearchResults(searchQuery)
    }
}