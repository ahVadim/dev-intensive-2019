package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncomig: Boolean = false,
    date: Date = Date(),
    var image: String
) : BaseMessage(id, from, chat, isIncomig, date) {
    override fun formatMessage(): String{
        return "id:$id ${from?.firstName} " +
                "${if (isIncoming) "получил" else "отправил"} изображение \"$image\" " +
                date.humanizeDiff()
    }
}