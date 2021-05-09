package com.nkdroid.noteit.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nkdroid.core.data.Note
import com.nkdroid.noteit.R
import com.nkdroid.noteit.framework.NoteViewModel

class NoteFragment : Fragment() {

    private lateinit var fabSave: FloatingActionButton
    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText

    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)
    private var noteId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabSave = view.findViewById(R.id.fabSave)
        etTitle = view.findViewById(R.id.etTitle)
        etContent = view.findViewById(R.id.etContent)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L) {
            viewModel.getNote(noteId)
        }

        fabSave.setOnClickListener {
            if (etTitle.text.toString() != "" || etContent.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.apply {
                    title = etTitle.text.toString()
                    content = etContent.text.toString()
                    updateTime = time
                    if (id == 0L) {
                        creationTime = time
                    }
                }
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(etTitle).popBackStack()
            } else {
                Toast.makeText(context, "Something went wrong, please try again",
                        Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.currentNote.observe(this, Observer {note->
            note?.let {
                currentNote = it
                etTitle.setText(it.title, TextView.BufferType.EDITABLE)
                etContent.setText(it.content, TextView.BufferType.EDITABLE)
            }
        })
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(etTitle.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.deleteNote -> {
                if (context != null && noteId != 0L) {
                    AlertDialog.Builder(context!!)
                            .setTitle("Delete note")
                            .setMessage("Are you sure want to delete this note")
                            .setPositiveButton("Yes") {dialogInterface, i->
                                viewModel.deleteNote(currentNote)
                            }
                            .setNegativeButton("Cancel") {dialogInterface, i->}
                            .create()
                            .show()
                }
            }
        }
        return true
    }
}