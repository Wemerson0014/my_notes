package com.estudo.mynotes.data.dao

import androidx.room.*
import com.estudo.mynotes.data.entities.NoteEntity
import com.estudo.mynotes.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: NoteEntity)

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("UPDATE note SET title = :title, description = :description WHERE id == :id")
    suspend fun update(id: Long, title: String, description: String)
}