package com.example.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "toDos")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
) : Serializable
