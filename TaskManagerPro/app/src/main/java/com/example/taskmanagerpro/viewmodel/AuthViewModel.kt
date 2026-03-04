package com.taskmanagerpro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskmanagerpro.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.login(email, password)
            _authState.value = if (result.isSuccess) {
                AuthState.Success("Login realizado!")
            } else {
                AuthState.Error(result.exceptionOrNull()?.message ?: "Erro ao fazer login")
            }
        }
    }

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.signup(email, password)
            _authState.value = if (result.isSuccess) {
                AuthState.Success("Conta criada!")
            } else {
                AuthState.Error(result.exceptionOrNull()?.message ?: "Erro ao criar conta")
            }
        }
    }

    fun logout() = repository.logout()
    fun isLoggedIn() = repository.currentUser != null
    fun resetState() { _authState.value = AuthState.Idle }
}