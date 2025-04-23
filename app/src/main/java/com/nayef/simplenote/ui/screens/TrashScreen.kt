package com.nayef.simplenote.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

import com.nayef.simplenote.data.Note
import com.nayef.simplenote.data.NotesViewModel
import com.nayef.simplenote.ui.components.BottomNavigationBar
import com.nayef.simplenote.ui.components.NoteCard
import com.nayef.simplenote.ui.components.NoteCardMode
import com.nayef.simplenote.ui.components.SimpleNotesTopBar


@Composable
fun TrashScreen(viewModel: NotesViewModel, navController: NavController) {
    val notes by viewModel.deletedNotes.collectAsState(initial = emptyList())
    Scaffold(
        topBar = { SimpleNotesTopBar("Trash") },
        bottomBar = {BottomNavigationBar(navController,true)}
    ) {
        padding -> LazyColumn(modifier = Modifier.padding(padding)) {
            items(notes.size) { index ->
                NoteCard(
                    note = notes[index],
                    onDelete = {viewModel.permanentlyDeleteNote(notes[index])},
                    onRestore = {viewModel.restoreNote(notes[index])},
                    mode = NoteCardMode.Trash
                )
            }
        }
    }
}
