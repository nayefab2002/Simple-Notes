package com.nayef.simplenote.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.nayef.simplenote.data.Note
import com.nayef.simplenote.data.NotesViewModel
import com.nayef.simplenote.ui.theme.poppins


fun saveNote(note: Note, viewModel: NotesViewModel) {
    viewModel.upsertNote(note)
}

@Composable
fun NoteInputDialog(
    notesViewModel: NotesViewModel,
    existingNote: Note? = null,
    onDismiss: () -> Unit
) {
    var title by remember {
        mutableStateOf(existingNote?.title ?: "")
    }
    var content by remember {
        mutableStateOf(existingNote?.content ?: "")
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    if (existingNote != null) {
                        val updatedNote = existingNote.copy(
                            title = title,
                            content = content
                        )
                        saveNote(updatedNote, viewModel = notesViewModel)

                    } else {
                        val note: Note = Note(title = title, content = content, isPinned = false)
                        saveNote(note, viewModel = notesViewModel)
                    }
                    onDismiss()
                },
            ) {
                Text(
                    if (existingNote == null) "Save" else "Update",
                    style = TextStyle(fontFamily = poppins)
                )

            }
        },
        title = {
            Text(
                text = if (existingNote == null) "Add Note" else "Edit Note",
                style = TextStyle(fontFamily = poppins)
            )
        },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    textStyle = TextStyle(fontFamily = poppins))
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    textStyle = TextStyle(fontFamily = poppins),
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Content") },
                    maxLines = 5,
                    modifier = Modifier.height(150.dp)

                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", style = TextStyle(fontFamily = poppins))
            }
        })
}