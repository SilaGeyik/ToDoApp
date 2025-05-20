package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {
    @Query("SELECT * FROM toDos")
    fun getAll(): LiveData<List<ToDo>>

    @Query("SELECT * FROM toDos WHERE name LIKE '%' || :search || '%'")
    fun search(search: String): LiveData<List<ToDo>>

    @Insert
    suspend fun insert(todo: ToDo)

    @Update
    suspend fun update(todo: ToDo)

    @Delete
    suspend fun delete(todo: ToDo)
}
