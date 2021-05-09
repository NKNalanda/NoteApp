package com.nkdroid.noteit.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nkdroid.core.data.Note
import com.nkdroid.core.repository.NoteRepository
import com.nkdroid.core.usecase.AddNote
import com.nkdroid.core.usecase.GetAllNotes
import com.nkdroid.core.usecase.GetNote
import com.nkdroid.core.usecase.RemoveNote
import com.nkdroid.noteit.framework.di.ApplicationModule
import com.nkdroid.noteit.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Nishant Kumar on 09-May-2021.
 */

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private var coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
                .applicationModule(ApplicationModule(getApplication()))
                .build()
                .inject(this)
    }

    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            val note = useCases.getNote(id)
            currentNote.postValue(note)
        }
    }

    fun deleteNote(note: Note) {
        coroutineScope.launch {
            useCases.removeNote(note)
            saved.postValue(true)
        }
    }
}