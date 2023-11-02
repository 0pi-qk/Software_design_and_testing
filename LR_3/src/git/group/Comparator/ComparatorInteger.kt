package git.group.Comparator

import git.group.Builder.BuilderInteger
import java.io.Serializable

class ComparatorInteger : Comparator, Serializable {
    override fun compare(o1: Any?, o2: Any?): Int {
        if ((o1 as BuilderInteger?)!!.value == (o2 as BuilderInteger?)!!.value) return 0
        return if (o1!!.value > o2!!.value) 1 else -1
    }
}