package com.zion.elders.backend

import android.R.attr.phoneNumber
import androidx.compose.ui.text.intl.Locale
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale.getDefault
import kotlin.random.Random

object Lib {
    fun generateMemberNumber(memberName: String, memberPhoneNumber: String): String{
        // Supposing I have name Ian Mwendwa, phone number 0791930433
        // Generate a unique member number like: ELDR-IANM-0433

        // Split the name into first name and second name. Now, this will break if no space is provided
        // which is why it HAS to be provided with that space
        if (!memberName.contains(" ")){
            // Safely return a message string in the meantime
            return "{Error!}"
        }
        val (firstName, secondName) = memberName.split(" ")
        val firstNameSubstr = firstName.substring(0, 3).uppercase(getDefault())
        val secondNameSubstr = secondName.substring(0, 1).uppercase(getDefault())

        // Cover the case where someone has no phone.
        if (memberPhoneNumber.isEmpty()){
            // Generate a sequence of 4 random integers
            val trailingNumbers = mutableListOf<Int>()

            while (trailingNumbers.size < 5){
                trailingNumbers.add(Random.nextInt(0, 9))

                if (trailingNumbers.size == 4) break
            }
            return "ELDR-$firstNameSubstr$secondNameSubstr-${trailingNumbers.joinToString("")}"
        }
        // Obtain the trailing last 4 phone number digits.
        val trailingPhoneNumberDigits = memberPhoneNumber.takeLast(4)
        return "ELDR-$firstNameSubstr$secondNameSubstr-$trailingPhoneNumberDigits"
    }
    fun getCurrentDate(): String{
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
    // Returns the provided number with a thousand's comma separator.
    fun formatNumber(number: Double): String {
        return "%,d".format(number)
    }
    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
        return formatter.format(millis)
    }
    fun registeredMembersMock(): List<String> {
        // Hardcoded list in the meantime. In the future, this will be obtained from the database
        val registeredMembers = listOf(
            "Wycliffe", "Victor", "Jeff", "Oscar", "Alex", "Juma", "Amos",
            "Solomon", "Geoffrey Mwangi", "Ian", "Munyoki", "Jackson", "Evans",
            "Paul"
        )
        return registeredMembers
    }
}