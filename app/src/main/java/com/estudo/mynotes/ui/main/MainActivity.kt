package com.estudo.mynotes.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estudo.mynotes.R
import com.estudo.mynotes.databinding.ActivityMainBinding
import com.estudo.mynotes.model.Note

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val notes = mutableListOf(
            Note("Estudar Kotlin", "Olhar material para estudos", "24/05/2021"),
            Note("Estudar MVVM", "Estudar sobre a arquitutetura", "19/05/2021"),
            Note(
                "Escrever novo capítulo",
                "Avaliar a história e escrever novo capítulo",
                "22/05/2021"
            ),
            Note("Pegar a bicicleta", "Verificar freio e calibragem dos pneus", "15/05/2021")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_notes)
        recyclerView.adapter = NotesAdapter(notes)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}