package git.group.Builder

import git.group.Comparator.Comparator
import git.group.Comparator.ComparatorGPS

class BuilderGPS : Builder {
    var latitude = 0.0
    var longitude = 0.0
    var hour = 0
    var minute = 0
    var second = 0

    constructor(latitude: Double, longitude: Double, hour: Int, minute: Int, second: Int) {
        this.latitude = latitude
        this.longitude = longitude
        this.hour = hour
        this.minute = minute
        this.second = second
    }

    constructor() {}

    override fun createObject(): Any? {
        return null
    }

    override fun readObject(): Any? {
        return latitude + longitude + hour + minute + second
    }

    override fun clone(): Any {
        return ""
    }

    override fun parseObject(ss: String?): Any? {
        val numStr = ss!!.split(";|:".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        latitude = numStr[0].toDouble()
        longitude = numStr[1].toDouble()
        hour = numStr[2].toInt()
        minute = numStr[3].toInt()
        second = numStr[4].toInt()
        return this
    }

    override val comparator: Comparator
        get() = ComparatorGPS()
    override val name: String?
        get() = TODO("")

    override fun toString(): String {
        return "$latitude;$longitude:$hour:$minute:$second"
    }

    companion object {

        val name: String = "GPS"

    }
}