package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.todoapp.data.ToDo
import com.example.todoapp.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    // Yapılacakları anlık olarak izler
    val toDoList: LiveData<List<ToDo>> = repository.getAll()

    // Arama sonuçları
    private val _searchResults = MutableLiveData<List<ToDo>>()
    val searchResults: LiveData<List<ToDo>> = _searchResults

    fun insert(toDo: ToDo) = viewModelScope.launch {
        repository.insert(toDo)
    }

    fun update(toDo: ToDo) = viewModelScope.launch {
        repository.update(toDo)
    }

    fun delete(toDo: ToDo) = viewModelScope.launch {
        repository.delete(toDo)
    }

    fun search(query: String) {
        repository.search(query).observeForever {
            _searchResults.value = it
        }
    }
}
