package git.group.Comparator

import git.group.Builder.BuilderGPS
import java.io.Serializable

class ComparatorGPS : Comparator, Serializable {
    override fun compare(o1: Any?, o2: Any?): Int {
        return ((o1 as BuilderGPS?)!!.latitude - (o2 as BuilderGPS?)!!.latitude).toInt()
    }
}