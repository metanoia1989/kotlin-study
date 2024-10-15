package com.example.jetpackcomposeexample

import com.example.jetpackcomposeexample.screens.calculateTip
import org.junit.Test
import org.junit.Assert.*
import java.text.NumberFormat

class CalculateTipTest {

    @Test
    fun calculateTip_20PercentNoRoundUp() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }
}