package com.example.a226complete

import com.example.a226complete.unit6.Calculator
import org.junit.Test
import org.junit.Assert.assertEquals

class CalculatorTest {

    @Test
    fun testAdd() {
        val calc = Calculator()
        assertEquals(8, calc.add(5, 3))
    }

    @Test
    fun testDivideByZero() {
        val calc = Calculator()
        val result = calc.divide(10, 0)
        assertEquals(0, result) // matches our debug-safe divide
    }
}