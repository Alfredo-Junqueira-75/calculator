package com.junqueira.calculator.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Calculator {
    var expression by mutableStateOf("")
    var result  by mutableDoubleStateOf(0.0)
}