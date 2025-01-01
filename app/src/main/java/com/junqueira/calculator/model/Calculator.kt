package com.junqueira.calculator.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Calculator {
    var expression by mutableStateOf("")
    var result  by mutableIntStateOf(0)
}