package com.felipebertanha.materialcalculator.domain

class ExpressionParser(
    private val calculation: String
) {

    fun parse(): List<ExpressionPart> {
        val result = mutableListOf<ExpressionPart>()


        var i = 0

        while (i < calculation.length) {
            val curChar = calculation[i]

            when {
                curChar in operationSymbols -> {
                    val operation = operationFromSymbol(curChar)
                    result.add(ExpressionPart.Op(operation))
                }

                curChar.isDigit() -> {
                    i = parseNumber(i, result)
                    continue
                }

                curChar in "()" -> {
                    result.add(parseParentheses(curChar))
                }
            }
            i++
        }

        return result
    }

    private fun parseNumber(startIndex: Int, result: MutableList<ExpressionPart>): Int {
        var i = startIndex

        val numberAsString = buildString {
            while (i < calculation.length && calculation[i] in "0123456789.") {
                append(calculation[i])
                i++
            }
        }

        result.add(ExpressionPart.Number(numberAsString.toDouble()))
        return i
    }

    private fun parseParentheses(curChar: Char): ExpressionPart {
        return when (curChar) {
            '(' -> ExpressionPart.Parentheses(ParenthesesType.Opening)

            ')' -> ExpressionPart.Parentheses(ParenthesesType.Closing)

            else -> throw IllegalArgumentException("Invalid parentheses symbol received: $curChar")
        }
    }

}