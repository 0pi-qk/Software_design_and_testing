package git.group.Builder

import git.group.Comparator.Comparator

interface Builder : Cloneable {
    fun createObject(): Any?
    fun readObject(): Any?
    override fun clone(): Any
    fun parseObject(ss: String?): Any?
    val comparator: Comparator?
    val name: String?
}