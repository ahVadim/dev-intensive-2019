package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.models.User
import java.lang.StringBuilder

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val parts: List<String>? = fullName?.split(" ")?.filter { it.isNotBlank() }

        val firstName: String? = parts?.getOrNull(0)
        val lastName: String? = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val rule_pairs = listOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya",
            " " to divider
        )
        val chars = payload.toCharArray()
        val newChars = StringBuilder()
        chars.forEach { char ->
            val isUpperCase = char.isUpperCase()
            val correctPair: Pair<String,String>? = rule_pairs.find { it.first == char.toLowerCase().toString() }
            var newChar = correctPair?.second
            if (isUpperCase) newChar = newChar?.toUpperCase()
            newChars.append(newChar ?: char)
        }
        return newChars.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val first = firstName?.getOrNull(0)?.toUpperCase()
        val second = lastName?.getOrNull(0)?.toUpperCase()
        var initials = ""
        first?.let { initials += first }
        second?.let { initials += second }
        return if (initials.isNotBlank()) initials
        else null
    }

}