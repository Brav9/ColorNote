package com.khalbro.colornote.presentation.allnotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khalbro.colornote.data.local.entity.Note
import com.khalbro.colornote.databinding.ItemNoteBinding

class NotesViewHolder(private val binding: ItemNoteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Note) {
        binding.tvNoteText.text = item.text
        binding.tvNoteTitleText.text = item.title
        if (binding.tvNoteTitleText.text.isBlank())
            binding.tvNoteTitleText.visibility = View.GONE
        else
            binding.tvNoteTitleText.visibility = View.VISIBLE
    }

    companion object {
        fun from(parent: ViewGroup): NotesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
            return NotesViewHolder(binding)
        }
    }
}