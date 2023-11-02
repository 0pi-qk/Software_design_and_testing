package git.group

import git.group.Builder.Builder
import git.group.Builder.BuilderGPS
import git.group.Builder.BuilderInteger
import java.util.*

object Factory {
    val typeNameList: ArrayList<String>
        get() = ArrayList(Arrays.asList("  ", "Integer", "GPS"))

    @Throws(Exception::class)
    fun getBuilderByName(name: String): Builder? {
        return if (name == BuilderGPS.name) {
            BuilderGPS()
        } else if (name == BuilderInteger.typename) {
            BuilderInteger()
        } else {
            null
        }
    }
}