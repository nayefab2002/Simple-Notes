package com.nayef.simplenote.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nayef.simplenote.data.Note
import com.nayef.simplenote.ui.theme.poppins

enum class NoteCardMode {
    Active,
    Trash
}

@Composable
fun NoteCard(
    note: Note,
    mode: NoteCardMode = NoteCardMode.Active,
    onClicked: (() -> Unit)? = null,
    onDelete: () -> Unit,
    onRestore: (() -> Unit)? = null,
    onPinToggle: (() -> Unit)? = null
) {
    val modifier = if (onClicked != null) {
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClicked)
    } else {
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    }

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 6.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    text = note.title,
                    style = TextStyle(fontFamily = poppins, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = note.content,
                    style = TextStyle(fontFamily = poppins),
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (mode == NoteCardMode.Active) {
                    if (onPinToggle != null) {
                        IconButton(
                            onClick = onPinToggle,
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Icon(
                                imageVector = if (note.isPinned) Icons.Default.Star else Icons.Default.StarOutline,
                                contentDescription = if (note.isPinned) "Unpin" else "Pin"
                            )
                        }
                    }
                    IconButton(
                        onClick = onDelete,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                } else if (mode == NoteCardMode.Trash) {
                    if (onRestore != null) {
                        IconButton(
                            onClick = onRestore,
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(Icons.Default.Restore, contentDescription = "Restore")
                        }
                    }
                    IconButton(
                        onClick = onDelete,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Icon(Icons.Default.DeleteForever, contentDescription = "Delete Forever")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NoteCardPreview() {
    NoteCard(
        note = Note(title = "Shopping", content = "Buy chocolate", isPinned = true),
        onClicked = {},
        onDelete = {},
        onPinToggle = {}
    )
}
