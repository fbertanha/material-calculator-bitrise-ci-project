package com.felipebertanha.materialcalculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.felipebertanha.materialcalculator.presentation.components.CalculatorButtonGrid
import com.felipebertanha.materialcalculator.presentation.components.CalculatorDisplay
import com.felipebertanha.materialcalculator.ui.theme.MaterialCalculatorTheme

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = viewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            CalculatorDisplay(
                expression = viewModel.expression,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 25.dp, bottomEnd = 25.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(
                        vertical = 64.dp, horizontal = 16.dp
                    )

            )
            Spacer(modifier = Modifier.height(8.dp))
            CalculatorButtonGrid(
                uiActions = calculatorActions,
                onAction = viewModel::onAction,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}

@PreviewLightDark
@Composable
private fun CalculatorScreenPreview() {
    MaterialCalculatorTheme {
        CalculatorScreen()
    }
}