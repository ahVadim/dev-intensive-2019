package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView(): UserView {

    val nickname = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val mLastVisit = lastVisit
    val status = when {
        mLastVisit == null -> "Ещё не разу не был"
        isOnline -> "online"
        else -> "Последний раз был ${mLastVisit.humanizeDiff()}"
    }

    return UserView(
        id = id,
        fullName = "$firstName $lastName",
        nickName = nickname,
        avatar = avatar,
        status = status,
        initials = initials
    )
}
