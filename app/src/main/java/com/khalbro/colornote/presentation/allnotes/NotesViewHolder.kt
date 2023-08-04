package com.khalbro.colornote.presentation.allnotes

import android.util.Log
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
        val colorSecond = item.getBackgroundColorVerticalLine(binding.root.context)
        val colorFirst = item.getBackgroundColor(binding.root.context)
        binding.constraintLayoutEditNote.setBackgroundColor(colorFirst)
        binding.ivVerticalLineNote.setBackgroundColor(colorSecond)
        Log.d("Ololo", "bind: $colorSecond)")
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