package com.estudo.mynotes.ui.main

import androidx.lifecycle.*
import com.estudo.mynotes.data.repository.NoteRepository
import com.estudo.mynotes.model.Note
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val showAllNotes: LiveData<List<Note>> = noteRepository.allNotes.asLiveData()

    fun saveNote(title: String) {
        val note = Note(title = title, description = "", date = getDate())
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    private fun getDate(): String {
        val date = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("dd/MMMM/yyyy", Locale.getDefault())
        return dateTimeFormat.format(date)
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