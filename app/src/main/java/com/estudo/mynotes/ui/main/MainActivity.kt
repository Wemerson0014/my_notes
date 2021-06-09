package com.estudo.mynotes.ui.main

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.estudo.mynotes.R
import com.estudo.mynotes.data.database.NoteDatabase
import com.estudo.mynotes.data.repository.NoteRepository
import com.estudo.mynotes.databinding.ActivityMainBinding
import com.estudo.mynotes.databinding.AddNoteBinding
import com.estudo.mynotes.model.Note
import com.estudo.mynotes.ui.detail.NoteDetail

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModelFactory: NoteViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = NoteDatabase.getDatabase(this).noteDao()
        val repository = NoteRepository(dao)
        viewModelFactory = NoteViewModelFactory(repository)

        mainViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        setupRecyclerView()
        setAddListener()
    }

    private fun setupRecyclerView() {
        val notes = mutableListOf(
            Note("Estudar Kotlin", "Olhar material para estudos", "24/05/2021"),
            Note("Estudar MVVM", "Estudar sobre a arquitetura", "19/05/2021"),
            Note(
                "Escrever novo capítulo",
                "Avaliar a história e escrever novo capítulo",
                "22/05/2021"
            ),
            Note("Pegar a bicicleta", "Verificar freio e calibragem dos pneus", "15/05/2021")
        )

        val recyclerView = binding.recyclerNotes
        recyclerView.adapter = NotesAdapter(notes) { note ->
            val intent = Intent(this, NoteDetail::class.java)
            intent.putExtra(KEY_NOTE, note)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setAddListener() {
        binding.fabAdd.setOnClickListener {
            showInsertNoteDialog()
        }
    }

    private fun showInsertNoteDialog() {
        val binding = AddNoteBinding.inflate(layoutInflater)
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setView(binding.root)
            .setPositiveButton(getString(R.string.dialog_positive_button)) { _, _ ->
                mainViewModel.saveNote(binding.editAddNote.text.toString())
            }
            .setNegativeButton(getString(R.string.dialog_negative_button)) { _, _ -> }
            .show()
    }

    companion object {
        const val KEY_NOTE = "Note"
    }
}