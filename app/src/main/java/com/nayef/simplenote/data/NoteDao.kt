package com.nayef.simplenote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes WHERE isDeleted = 0")
    fun getActiveNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE isDeleted = 1")
    fun getDeletedNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    suspend fun getNoteById(id: Int): Note?

    @Insert
    suspend fun insertNote(note: Note)

    @Upsert
    suspend fun upsertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(vararg notes: Note)

    suspend fun insertSampleNotes() {
        insertNotes(
            Note(title = "Groceries", content = "Buy bread", isDeleted = false),
            Note(title = "Workout", content = "Go for a run", isDeleted = true),
            Note(title = "Study", content = "Review CIS-436 final study guide", isDeleted = false),
            Note(title = "Call Mom", content = "Check in and ask her about our summer trip", isDeleted = false),
            Note(title = "Project Ideas", content = "Note app, soundboard, mood tracker", isDeleted = true)
        )
    }
}
