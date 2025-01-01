package com.junqueira.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.junqueira.calculator.model.Calculator


val calculator = Calculator()

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorApp() {
    Card {
        Column {
            CalculatorExpression( modifier = Modifier.weight(1f))
            KeyboardNumbersButton(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun KeyboardNumbersButton(modifier: Modifier = Modifier) {
    val buttons = listOf(
        listOf("AC", "()", "%", "/"),
        listOf("7", "8", "9", "x"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+"),
        listOf("0", ".", "C", "=")
    )
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(15.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        buttons.forEach { row ->
            ButtonRow(row)
        }
    }
}

@Composable
fun ButtonRow(buttons: List<String>) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
        .fillMaxWidth()
    ) {
        buttons.forEach { button ->
            Button(
                contentPadding = PaddingValues(20.dp),
                onClick = {
                    when (button) {
                        "AC" -> {
                            calculator.expression = ""
                            calculator.result = 0.0
                        }
                        "C" -> calculator.expression = clear(calculator.expression)
                        "=" -> calculator.result = processStringOperation(calculator.expression)
                        else -> calculator.expression = writeOperation(calculator.expression, button)
                    }

                }) {
                Text(text = button,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun CalculatorExpression(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentHeight(align = Alignment.Bottom)
    )   {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxWidth()
        )   {
            Text(text = calculator.expression,
                fontSize = 45.sp,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxWidth()
        )   {
            Text(text = if(calculator.result % 1 != 0.0) calculator.result.toString() else calculator.result.toInt().toString(),
                fontSize = 30.sp,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}