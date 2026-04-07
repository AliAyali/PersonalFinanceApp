package com.aliayali.personalfinanceapp.domain.repository

import androidx.room.Delete
import com.aliayali.personalfinanceapp.data.local.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: NoteEntity)
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
    fun getAllNotes(): Flow<List<NoteEntity>>
    suspend fun getNoteById(noteId: Int): NoteEntity?
    fun getNotesByDate(date: String): Flow<List<NoteEntity>>
}