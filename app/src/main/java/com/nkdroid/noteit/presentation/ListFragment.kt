package com.nkdroid.noteit.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nkdroid.noteit.R
import com.nkdroid.noteit.framework.ListViewModel

class ListFragment : Fragment(), IListAction {

    private lateinit var rvNotes: RecyclerView
    private lateinit var fabAddNote: FloatingActionButton
    private lateinit var pbNotes: ProgressBar

    private val notesListAdapter = NotesListAdapter(arrayListOf(), this)
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNotes = view.findViewById(R.id.rvNotes)
        fabAddNote = view.findViewById(R.id.fabAddNote)
        pbNotes = view.findViewById(R.id.pbNotes)

        rvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }

        fabAddNote.setOnClickListener { goToNoteDetails() }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.notes.observe(this, Observer {notesList->
            pbNotes.visibility = View.GONE
            rvNotes.visibility = View.VISIBLE
            notesListAdapter.updateNotes(notesList.sortedByDescending { it.updateTime })
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action = ListFragmentDirections.actionListFragmentToNoteFragment(id)
        Navigation.findNavController(rvNotes).navigate(action)
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }
}