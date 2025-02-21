package com.felipebertanha.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExpressionWriterTest {

    private lateinit var writer: ExpressionWriter

    @Before
    fun setup() {
        writer = ExpressionWriter()
    }

    @Test
    fun `initial parentheses parsed`() {
        writer.proceedAction(CalculatorAction.Parentheses)
        writer.proceedAction(CalculatorAction.Number(5))
        writer.proceedAction(CalculatorAction.Op(Operation.ADD))
        writer.proceedAction(CalculatorAction.Number(4))
        writer.proceedAction(CalculatorAction.Parentheses)

        val actual = writer.expression

        val expected = "(5+4)"

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `closing parentheses at start not parsed`() {
        writer.proceedAction(CalculatorAction.Parentheses)
        writer.proceedAction(CalculatorAction.Parentheses)

        val actual = writer.expression

        val expected = "(("

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `parentheses around number are parsed`() {
        writer.proceedAction(CalculatorAction.Parentheses)
        writer.proceedAction(CalculatorAction.Number(6))
        writer.proceedAction(CalculatorAction.Parentheses)

        val actual = writer.expression

        val expected = "(6)"

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `valid expression is properly parsed`() {
        writer.proceedAction(CalculatorAction.Number(6))
        writer.proceedAction(CalculatorAction.Op(Operation.MULTIPLY))
        writer.proceedAction(CalculatorAction.Parentheses)
        writer.proceedAction(CalculatorAction.Number(10))
        writer.proceedAction(CalculatorAction.Op(Operation.DIVIDE))
        writer.proceedAction(CalculatorAction.Number(2))
        writer.proceedAction(CalculatorAction.Parentheses)
        writer.proceedAction(CalculatorAction.Calculate)

        val actual = writer.expression

        val expected = "30.0"

        assertThat(actual).isEqualTo(expected)
    }
}