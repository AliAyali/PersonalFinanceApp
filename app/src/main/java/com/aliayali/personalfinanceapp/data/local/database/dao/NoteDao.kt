package com.aliayali.personalfinanceapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aliayali.personalfinanceapp.data.local.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE id = :noteId LIMIT 1")
    suspend fun getNoteById(noteId: Long): NoteEntity?

    @Query("SELECT * FROM note_table WHERE date = :date")
    fun getNotesByDate(date: String): Flow<List<NoteEntity>>
}