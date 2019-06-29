package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncomig: Boolean = false,
    date: Date = Date(),
    var text: String
) : BaseMessage(id, from, chat, isIncomig, date) {
    override fun formatMessage(): String{
        return "id:$id ${from?.firstName} " +
                "${if (isIncoming) "получил" else "отправил"} сообщение \"$text\" " +
                date.humanizeDiff()
    }
}