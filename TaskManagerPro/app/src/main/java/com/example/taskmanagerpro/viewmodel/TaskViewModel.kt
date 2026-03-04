package com.taskmanagerpro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskmanagerpro.data.TaskRepository
import com.taskmanagerpro.models.Task
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _filterCompleted = MutableStateFlow<Boolean?>(null)

    val filteredTasks = combine(tasks, searchQuery, _filterCompleted) { tasks, query, filter ->
        tasks.filter { task ->
            val matchesSearch = task.title.contains(query, ignoreCase = true) ||
                    task.description.contains(query, ignoreCase = true)
            val matchesFilter = filter == null || task.completed == filter
            matchesSearch && matchesFilter
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            repository.getTasks().collect { _tasks.value = it }
        }
    }

    fun createTask(title: String, description: String, priority: String) {
        viewModelScope.launch {
            repository.createTask(Task(
                title = title,
                description = description,
                priority = priority
            ))
        }
    }

    fun toggleComplete(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task.copy(completed = !task.completed))
        }
    }

    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
        }
    }

    fun updateSearch(query: String) {
        _searchQuery.value = query
    }

    fun setFilter(completed: Boolean?) {
        _filterCompleted.value = completed
    }

    fun getStats() = tasks.value.let { tasks ->
        Triple(
            tasks.size,
            tasks.count { it.completed },
            tasks.count { !it.completed }
        )
    }
}