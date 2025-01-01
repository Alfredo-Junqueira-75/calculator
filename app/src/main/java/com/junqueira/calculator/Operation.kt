package com.junqueira.calculator

import net.objecthunter.exp4j.ExpressionBuilder

var openArms = false
fun clear(operation: String) = operation.dropLast(1)
fun writeOperation(expression: String, button: String): String {

    var aux = expression

    if (button != "()" ){
        aux += button
    }
    else {
        if (openArms) {
            aux = aux.plus(")")
            openArms = false
        } else {
            aux = aux.plus("(")
            openArms = true
        }
    }
    return aux
}

fun processStringOperation(string: String): Int {

    return (try {
        val formattedExpression = string.replace("x", "*")
        val expr = ExpressionBuilder(formattedExpression).build()
        expr.evaluate()
    } catch (e: Exception) {
        println("Erro ao avaliar a expressão: ${e.message}")
        0.0
    }).toInt()
}