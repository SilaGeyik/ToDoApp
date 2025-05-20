package com.example.todoapp.repository

import com.example.todoapp.data.ToDo
import com.example.todoapp.data.ToDoDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoRepository @Inject constructor(
    private val toDoDao: ToDoDao
) {
    fun getAll() = toDoDao.getAll()
    fun search(query: String) = toDoDao.search(query)

    suspend fun insert(toDo: ToDo) = toDoDao.insert(toDo)
    suspend fun update(toDo: ToDo) = toDoDao.update(toDo)
    suspend fun delete(toDo: ToDo) = toDoDao.delete(toDo)
}
