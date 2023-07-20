package com.khalbro.colornote.presentation.allnotes

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.khalbro.colornote.domain.models.InfoNote

class NotesAdapter(private val listener: OnClickListener) :
    ListAdapter<InfoNote, NotesViewHolder>(NotesDiffCallback()) {

    interface OnClickListener {
        fun onItemClick(infoNote: InfoNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        Log.d("Ololo", "onCreateViewHolder: $viewType,  $parent")
        return NotesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        Log.d("Ololo", "onBindViewHolder: $holder")
        val item = getItem(position)
        holder.bind(item, listener)

    }
}

class NotesDiffCallback : DiffUtil.ItemCallback<InfoNote>() {
    override fun areItemsTheSame(oldItem: InfoNote, newItem: InfoNote): Boolean {
        Log.d("Ololo", "areItemsTheSame: $oldItem")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: InfoNote, newItem: InfoNote): Boolean {
        Log.d("Ololo", "areContentsTheSame: $oldItem")
        return oldItem == newItem
    }
}