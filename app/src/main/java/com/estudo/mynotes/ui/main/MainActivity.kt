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
import com.estudo.mynotes.ui.detail.NoteDetailActivity

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

        setupToolbar()
        setupRecyclerView()
        setAddListener()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerNotes

        mainViewModel.showAllNotes.observe(this) {
            recyclerView.adapter = NotesAdapter(notes = it.toMutableList(),
                onClickItem = { note ->
                    val intent = Intent(this, NoteDetailActivity::class.java)
                    intent.putExtra(KEY_NOTE, note)
                    startActivity(intent)
                },
                onDeleteItem = { note ->
                    showDeleteNoteDialog(note)
                })
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

    }

    private fun setAddListener() {
        binding.fabAdd.setOnClickListener {
            showInsertNoteDialog()
        }
    }

    private fun showInsertNoteDialog() {
        val binding = AddNoteBinding.inflate(layoutInflater)
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_insert_title))
            .setView(binding.root)
            .setPositiveButton(getString(R.string.add)) { _, _ ->
                mainViewModel.saveNote(binding.editAddNote.text.toString())
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            .show()
    }

    private fun showDeleteNoteDialog(note: Note) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.delete))
            .setMessage(getString(R.string.delete_message_dialog))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                mainViewModel.deleteNote(note)
            }
            .setNegativeButton(getString(R.string.no)) { _, _  -> }
            .show()
    }

    companion object {
        const val KEY_NOTE = "Note"
    }
}