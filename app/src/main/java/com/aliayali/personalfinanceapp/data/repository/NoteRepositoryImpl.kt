package com.aliayali.personalfinanceapp.data.repository

import com.aliayali.personalfinanceapp.data.local.database.dao.NoteDao
import com.aliayali.personalfinanceapp.data.local.database.entity.NoteEntity
import com.aliayali.personalfinanceapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
) : NoteRepository {
    override suspend fun insertNote(note: NoteEntity) {
        dao.insertNote(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        dao.updateNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        dao.deleteNote(note)
    }

    override fun getAllNotes(): Flow<List<NoteEntity>> =
        dao.getAllNotes()

    override suspend fun getNoteById(noteId: Long): NoteEntity? =
        dao.getNoteById(noteId)

    override fun getNotesByDate(date: String): Flow<List<NoteEntity>> =
        dao.getNotesByDate(date)
}