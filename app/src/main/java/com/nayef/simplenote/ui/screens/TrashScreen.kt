package com.nayef.simplenote.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.nayef.simplenote.ui.components.TopAppBar


@Composable
fun TrashScreen(viewModel: NotesViewModel, navController: NavController) {
    val notes by viewModel.deletedNotes.collectAsState(initial = emptyList())

    var noteToDelete by remember { mutableStateOf<Note?>(null) }

    Scaffold(
        topBar = { TopAppBar("Trash") },
        bottomBar = { BottomNavigationBar(navController,true) }
    ) {
        padding -> LazyColumn(modifier = Modifier.padding(padding)) {
            items(notes.size) { index ->
                NoteCard(
                    note = notes[index],
                    onDelete = { noteToDelete = notes[index] },
                    onRestore = { viewModel.restoreNote(notes[index]) },
                    mode = NoteCardMode.Trash
                )
            }
        }

        if (noteToDelete != null) {
            AlertDialog(
                onDismissRequest = { noteToDelete = null },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.permanentlyDeleteNote(noteToDelete!!)
                        noteToDelete = null
                    }) {
                        Text("Delete", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { noteToDelete = null }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Delete Forever?") },
                text = { Text("This note will be permanently deleted and cannot be recovered.") }
            )
        }

    }
}
