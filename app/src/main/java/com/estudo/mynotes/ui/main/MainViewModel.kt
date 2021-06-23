package com.estudo.mynotes.ui.main

import androidx.lifecycle.*
import com.estudo.mynotes.data.repository.NoteRepository
import com.estudo.mynotes.model.Note
import kotlinx.coroutines.launch

class MainViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val showAllNotes: LiveData<List<Note>> = noteRepository.allNotes.asLiveData()

    fun saveNote(title: String) {
        val note = Note(title = title, description = "", date = "04/06/2021")
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}