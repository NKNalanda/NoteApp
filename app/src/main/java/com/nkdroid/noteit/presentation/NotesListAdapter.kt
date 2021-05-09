package com.nkdroid.noteit.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nkdroid.core.data.Note
import com.nkdroid.noteit.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

class NotesListAdapter(
        var notes: ArrayList<Note>,
        val actions: IListAction
): RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder = NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val cvNoteLayout = view.findViewById<CardView>(R.id.cvNoteLayout)
        private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        private val tvContent = view.findViewById<TextView>(R.id.tvContent)
        private val tvDate = view.findViewById<TextView>(R.id.tvDate)
        private val tvWordCount = view.findViewById<TextView>(R.id.tvWordCount)

        fun bind(note: Note) {
            tvTitle.text = note.title
            tvContent.text = note.content
            tvWordCount.text = "Words: ${note.wordCount}"

            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(note.updateTime)
            tvDate.text = "Last updated: ${sdf.format(resultDate)}"

            cvNoteLayout.setOnClickListener {
                actions.onClick(note.id)
            }
        }
    }
}