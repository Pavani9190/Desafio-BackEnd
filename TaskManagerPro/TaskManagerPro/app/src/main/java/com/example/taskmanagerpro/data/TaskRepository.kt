package com.taskmanagerpro.data

import com.google.firebase.database.*
import com.taskmanagerpro.models.Task
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class TaskRepository {
    private val database = FirebaseDatabase.getInstance()
    private val tasksRef = database.getReference("tasks")

    /**
     * Observa as tarefas em tempo real do Firebase Realtime Database
     * IMPORTANTE: awaitClose DEVE ser a última declaração no callbackFlow
     */
    fun getTasks(): Flow<List<Task>> = callbackFlow {
        // Criar o ValueEventListener
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tasks = mutableListOf<Task>()

                // Iterar sobre os filhos do snapshot
                snapshot.children.forEach { childSnapshot ->
                    val task = childSnapshot.getValue(Task::class.java)
                    task?.let {
                        // Adicionar o ID do Firebase à tarefa
                        tasks.add(it.copy(id = childSnapshot.key ?: ""))
                    }
                }

                // Enviar as tarefas para o Flow
                trySend(tasks)
            }

            override fun onCancelled(error: DatabaseError) {
                // Fechar o Flow em caso de erro
                close(error.toException())
            }
        }

        // Adicionar o listener
        tasksRef.addValueEventListener(listener)

        // CRÍTICO: awaitClose DEVE ser a última declaração
        // Isso previne vazamento de memória quando o Flow é cancelado
        awaitClose {
            tasksRef.removeEventListener(listener)
        }
        // NÃO COLOQUE NADA DEPOIS DO awaitClose!
    }

    /**
     * Cria uma nova tarefa no Realtime Database
     */
    suspend fun createTask(task: Task) {
        val newTaskRef = tasksRef.push()
        val taskWithId = task.copy(id = newTaskRef.key ?: "")
        newTaskRef.setValue(taskWithId).await()
    }

    /**
     * Atualiza uma tarefa existente
     */
    suspend fun updateTask(task: Task) {
        tasksRef.child(task.id).setValue(task).await()
    }

    /**
     * Deleta uma tarefa pelo ID
     */
    suspend fun deleteTask(taskId: String) {
        tasksRef.child(taskId).removeValue().await()
    }
}