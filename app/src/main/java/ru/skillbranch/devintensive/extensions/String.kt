package ru.skillbranch.devintensive.extensions

import java.util.regex.Pattern

fun String.truncate(count: Int = 16): String{
    var result = this.trim()
    if (result.length<=count) return result
    result = this.take(count)
    result = result.trim()
    return result.plus("...")
}

fun String.stripHtml(): String {
    val TAGS = Pattern.compile("<.+?>")
    val EXTRASPACES = Pattern.compile("\\s+")
    val firstStep = TAGS.matcher(this).replaceAll("")
    return EXTRASPACES.matcher(firstStep).replaceAll(" ")
//    return this.replace("\\<[^>]*>","");
}