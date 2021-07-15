package com.estudo.mynotes.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estudo.mynotes.databinding.NoteItemBinding
import com.estudo.mynotes.model.Note

class NotesAdapter(private var notes: MutableList<Note>, private val onClickItem: (Note) -> Unit, private val onDeleteItem: (Note) -> Unit) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    init {
        setHasStableIds(true)
    }
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]

        holder.bind(note)
    }

    override fun getItemCount(): Int = notes.size

    fun updateList(notes: MutableList<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class NotesViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.noteTitle.text = note.title
            binding.noteDate.text = note.date
            binding.root.setOnClickListener {
                onClickItem(note)
            }
            binding.noteIconDelete.setOnClickListener {
                onDeleteItem(note)
            }
        }
    }
}
