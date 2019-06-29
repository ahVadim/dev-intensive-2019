package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.sign

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND): Date{

    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

enum class TimeUnits{
    SECOND, MINUTE, HOUR, DAY
}

fun String.getProperDateWord(isPast: Boolean): String{
    val result = StringBuilder(this)
    if (isPast) result.append(" назад")
    else result.insert(0, "через ")
    return result.toString()
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val rawDiff = date.time - this.time
    val isPast = rawDiff.sign >= 0
    val diff = abs(date.time - this.time)
    return when {
        diff < 1*SECOND -> "только что"
        diff < 45*SECOND -> "несколько секунд".getProperDateWord(isPast)
        diff < 75*SECOND -> "минуту".getProperDateWord(isPast)
        diff < 45* MINUTE -> {
            val minutes = diff / MINUTE
            val text =  when {
                (minutes / 10) == 1L -> "$minutes минут"
                (minutes % 10) == 1L -> "$minutes минуту"
                (minutes % 10) in (2..4) -> "$minutes минуты"
                else -> "$minutes минут"
            }
            return text.getProperDateWord(isPast)
        }

        diff < 75* MINUTE -> "час".getProperDateWord(isPast)
        diff < 22* HOUR -> {
            val hours = diff/ HOUR
            return when {
                (hours / 10) == 1L -> "$hours часов"
                (hours % 10) == 1L -> "$hours час"
                (hours % 10) in (2L..4L) -> "$hours часа"
                else -> "$hours часов"
            }.getProperDateWord(isPast)
        }
        diff < 26* HOUR -> "день".getProperDateWord(isPast)
        diff < 360* DAY -> {
            val days = diff/DAY
            return when {
                (days / 10) == 1L -> "$days дней"
                (days % 10) == 1L -> "$days день"
                (days % 10) in (2..4) -> "$days дня"
                else -> "$days дней"
            }.getProperDateWord(isPast)
        }
        isPast -> "более года назад"
        else -> "более чем через год"
    }
}