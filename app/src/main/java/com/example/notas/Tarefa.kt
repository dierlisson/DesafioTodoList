package com.example.notas

import androidx.compose.runtime.MutableState

data class Tarefa(val nome: String, var concluida: MutableState<Boolean>)