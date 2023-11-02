package git.group.Builder

import git.group.Comparator.Comparator
import git.group.Comparator.ComparatorInteger

class BuilderInteger : Builder {
    var value = 0

    constructor(`val`: Int) {
        value = `val`
    }

    constructor() {}

    override fun createObject(): Any? {
        return null
    }

    override fun readObject(): Any? {
        return value
    }

    override fun parseObject(ss: String?): Any? {
        value = ss!!.toInt()
        return this
    }

    override val comparator: Comparator
        get() = ComparatorInteger()

    override fun clone(): Any {
        return ""
    }

    override val name: String
        get() = "Int"

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        val typename: String = "Integer"
    }
}