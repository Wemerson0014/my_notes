package com.estudo.mynotes.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.estudo.mynotes.R
import com.estudo.mynotes.databinding.ActivityNoteDetailBinding
import com.estudo.mynotes.model.Note
import com.estudo.mynotes.ui.main.MainActivity.Companion.KEY_NOTE

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        bindValues()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun bindValues() {
        val note = intent.getParcelableExtra<Note>(KEY_NOTE)
        binding.detailTitle.text = note?.title
        binding.detailDescription.text = note?.description
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}