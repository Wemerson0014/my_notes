package com.estudo.mynotes.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.estudo.mynotes.data.database.NoteDatabase
import com.estudo.mynotes.data.repository.NoteRepository
import com.estudo.mynotes.databinding.ActivityNoteDetailBinding
import com.estudo.mynotes.model.Note
import com.estudo.mynotes.ui.main.MainActivity.Companion.KEY_NOTE

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding

    private lateinit var noteDetailViewModel: NoteDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteRepository = NoteRepository(NoteDatabase.getDatabase(this).noteDao())
        val noteDetailViewModelFactory = NoteDetailViewModelFactory(noteRepository)

        noteDetailViewModel = ViewModelProvider(this, noteDetailViewModelFactory).get(
            NoteDetailViewModel::class.java
        )

        setupToolbar()
        bindValues()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun bindValues() {
        val note = intent.getParcelableExtra<Note>(KEY_NOTE)
        binding.detailTitle.setText(note?.title)
        binding.detailDescription.setText(note?.description)

        binding.save.setOnClickListener {
            note?.let { updateNote(it) }
            finish()
        }
    }

    private fun updateNote(note: Note) {
        val noteToUpdate = note.copy(
            title = binding.detailTitle.text.toString(),
            description = binding.detailDescription.text.toString()
        )
        noteDetailViewModel.updateNote(noteToUpdate)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}