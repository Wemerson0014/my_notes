package com.estudo.mynotes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.estudo.mynotes.data.entities.NoteEntity
import com.estudo.mynotes.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: NoteEntity)

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>
}