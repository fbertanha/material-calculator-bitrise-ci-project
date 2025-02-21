package com.felipebertanha.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionEvaluatorTest {
    private lateinit var evaluator: ExpressionEvaluator

    @Test
    fun `simple expression properly evaluated`() {
        // 4+5-3x5/3
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(-4.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.0),
            )
        )

        val actual = evaluator.evaluate()

        val expected = -4

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `expression with decimals properly evaluated`() {
        //
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.5),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.5),
            )
        )

        val actual = evaluator.evaluate()

        val expected = 4.5

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `expression with parentheses properly evaluated`() {
        //
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Parentheses(ParenthesesType.Opening),
                ExpressionPart.Number(2.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(5.0),
                ExpressionPart.Parentheses(ParenthesesType.Closing),
            )
        )

        val actual = evaluator.evaluate()

        val expected = -9

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `expression with percentage properly evaluated`() {
        //
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(120.0),
                ExpressionPart.Op(Operation.PERCENT),
                ExpressionPart.Number(10.0),
            )
        )

        val actual = evaluator.evaluate()

        val expected = 12

        assertThat(actual).isEqualTo(expected)
    }
}