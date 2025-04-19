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

    val notes=dao.getAllNotes().flowOn(Dispatchers.IO)

    fun upsertNote(note:Note){
        Log.i("Note id : ", note.id.toString())
        viewModelScope.launch { dao.upsertNote(note) }
    }
    fun updateNote(note:Note){
        viewModelScope.launch { dao.updateNote(note) }
    }
    fun deleteNote(note:Note){
        viewModelScope.launch { dao.deleteNote(note) }
    }

}