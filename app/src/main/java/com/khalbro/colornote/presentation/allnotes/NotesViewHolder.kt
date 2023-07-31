package com.khalbro.colornote.presentation.allnotes

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khalbro.colornote.databinding.ItemNoteBinding
import com.khalbro.colornote.domain.models.InfoNote

class NotesViewHolder(private val binding: ItemNoteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: InfoNote,
        listener: NotesAdapter.OnClickListener,
    ) {

        val color = Color.parseColor(item.getBackgroundColor())
        binding.constraintLayoutEditNote.setBackgroundColor(color)
        binding.tvNoteText.text = item.text
        binding.tvNoteTitleText.text = item.title
        if (binding.tvNoteTitleText.text.isBlank())
            binding.tvNoteTitleText.visibility = View.GONE
        else
            binding.tvNoteTitleText.visibility = View.VISIBLE

        itemView.setOnClickListener {
            listener.onItemClick(item)
        }

        itemView.setOnLongClickListener {
            listener.onLongItemClick(item)

            return@setOnLongClickListener true
        }
    }

    companion object {
        fun from(parent: ViewGroup): NotesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
            return NotesViewHolder(binding)
        }
    }
}