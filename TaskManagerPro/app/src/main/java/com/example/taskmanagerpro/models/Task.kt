package com.taskmanagerpro.models

import com.google.firebase.database.IgnoreExtraProperties

/**
 * Modelo de dados para uma tarefa
 *
 * IMPORTANTE para Firebase Realtime Database:
 * - Deve ter um construtor vazio (sem parâmetros)
 * - Todas as propriedades devem ter valores padrão
 * - Use @IgnoreExtraProperties para ignorar campos extras no banco
 */
@IgnoreExtraProperties
data class Task(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val priority: String = "medium", // low, medium, high
    val completed: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) {
    // Construtor vazio OBRIGATÓRIO para o Firebase
    constructor() : this(
        id = "",
        title = "",
        description = "",
        priority = "medium",
        completed = false,
        createdAt = System.currentTimeMillis()
    )
}