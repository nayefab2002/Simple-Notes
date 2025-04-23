package com.nayef.simplenote.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {

    private val dao=NoteDatabase.getDatabase(application).noteDao()

    val activeNotes = dao.getActiveNotes().flowOn(Dispatchers.IO)
    val deletedNotes = dao.getDeletedNotes().flowOn(Dispatchers.IO)

    fun upsertNote(note:Note){
        Log.i("Note id : ", note.id.toString())
        viewModelScope.launch { dao.upsertNote(note) }
    }

    fun updateNote(note:Note){
        viewModelScope.launch { dao.updateNote(note) }
    }

    fun moveNoteToTrash(note: Note) {
        val trashed = note.copy(isDeleted = true)
        viewModelScope.launch { dao.upsertNote(trashed) }
    }

    fun restoreNote(note: Note) {
        val restored = note.copy(isDeleted = false)
        viewModelScope.launch { dao.upsertNote(restored) }
    }

    fun permanentlyDeleteNote(note: Note) {
        viewModelScope.launch { dao.deleteNote(note) }
    }
}
