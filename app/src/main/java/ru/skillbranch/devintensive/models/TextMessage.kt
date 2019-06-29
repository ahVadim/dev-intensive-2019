package ru.skillbranch.devintensive.models

import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncomig: Boolean = false,
    date: Date = Date(),
    var text: String
) : BaseMessage(id, from, chat, isIncomig, date) {
    override fun formatMessage(): String  = "id:$id ${from?.firstName} " +
            "${if (isIncomig) "получил" else "отправил"} сообщение \"$text\""
}