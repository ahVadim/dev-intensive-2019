package ru.skillbranch.devintensive.models

import java.util.*

class UserBuilder {
    var user =  User("0", null, null, null)
    fun id(p: String): UserBuilder{
        user = user.copy(id = p)
        return this
    }

    fun firstName(p: String?): UserBuilder{
        user = user.copy(firstName = p)
        return this
    }

    fun lastName(p: String?): UserBuilder{
        user = user.copy(lastName = p)
        return this
    }

    fun avatar(p: String?): UserBuilder{
        user = user.copy(avatar = p)
        return this
    }

    fun rating(p: Int): UserBuilder{
        user = user.copy(rating = p)
        return this
    }

    fun respect(p: Int): UserBuilder{
        user = user.copy(respect = p)
        return this
    }

    fun lastVisit(p: Date?): UserBuilder{
        user = user.copy(lastVisit = p)
        return this
    }

    fun isOnline(p: Boolean): UserBuilder{
        user = user.copy(isOnline = p)
        return this
    }

    fun build(): User = user
}