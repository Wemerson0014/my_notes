package com.estudo.mynotes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.estudo.mynotes.data.entities.NoteEntity

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: NoteEntity)
}