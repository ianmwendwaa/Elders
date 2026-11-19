package com.zion.elders.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

class UtilityFunctionTests {
    @Test
    fun generate_member_number(){
        val mockName = "User One"
        val mockNumber = "0712345678"

        // Expect ELDR-USEO-5678
        val (firstName, lastName) = mockName.split(" ")
        val firstNameSubstr = firstName.substring(0, 3).uppercase()
        val lastNameSubstr = lastName.substring(0, 1).uppercase()

        val trailingPhoneNumberDigits = mockNumber.takeLast(4)

        val memberNumber = "ELDR-$firstNameSubstr$lastNameSubstr-$trailingPhoneNumberDigits"

        assertEquals("ELDR-USEO-5678", memberNumber)
    }
    @Test
    fun format_number(){
        val target = 1000.2
        val formattedTarget = "%,d".format(target.toInt())
        println(formattedTarget)
        assertEquals("1,000", formattedTarget)
    }
}