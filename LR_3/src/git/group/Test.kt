package git.group

import git.group.Builder.Builder
import git.group.Builder.BuilderGPS
import git.group.Builder.BuilderInteger
import git.group.List.DoIt
import git.group.List.TList
import java.util.*

class Test {
    val MAX = 100.0
    val MIN = -100.0
    val minHour = 0
    val maxHour = 23
    val minTime = 0
    val maxTime = 59
    @Throws(Exception::class)
    fun settingBuilder(name: String): Builder {
        if (name == BuilderGPS.name) {
            return BuilderGPS()
        }
        return if (name == BuilderInteger.typename) {
            BuilderInteger()
        } else {
            val e = Exception("Oшибка: нет такого типа")
            throw e
        }
    }

    @Throws(Exception::class)
    fun run() {
        testInt()
        testGPS()
    }

    private fun drawList(otherlist: TList) {
        otherlist.forEach(object : DoIt {
            override fun doIt(o: Any?) {
                println(o)
            }
        })
    }

    @Throws(Exception::class)
    fun testInt() {
        var builder: Builder? = null
        val list: TList
        print("\tТест Integer")
        try {
            builder = settingBuilder("Integer")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        list = TList(builder)
        print("\nСписок пуст\n")
        drawList(list)
        val max = (Math.random() * 5).toInt() + 4
        for (i in 0 until max) {
            val value = (Math.random() * 100).toInt() - 50
            val obj: Builder = BuilderInteger(value)
            obj.toString()
            list.add(obj, i)
        }
        print("\nСгенерированный список\n")
        drawList(list)
        print("\nПоиск каждого второго элемента\n")
        var i = 0
        while (i < max) {
            println(list.find(i))
            i = i + 2
        }
        println("\nПроизошла сортировка\n")
        list.sort(builder!!.comparator)
        drawList(list)
        list.clear()
        println("Список удален")
    }

    @Throws(Exception::class)
    fun testGPS() {
        var builder: Builder? = null
        val list: TList
        print("\tTest GPS")
        try {
            builder = settingBuilder("GPS")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        list = TList(builder)
        print("\nСписок пуст\n")
        drawList(list)
        val max = (Math.random() * 5).toInt() + 4
        for (i in 0 until max) {
            val rand = Random()
            val latitude = MIN + (MAX - MIN) * rand.nextDouble()
            val longitude = MIN + (MAX - MIN) * rand.nextDouble()
            val hour = rand.nextInt(maxHour - minHour)
            val minute = rand.nextInt(maxTime - minTime)
            val second = rand.nextInt(maxTime - minTime)
            val obj: Builder = BuilderGPS(latitude, longitude, hour, minute, second)
            obj.toString()
            list.add(obj, i)
        }
        print("\nСгенерированный список\n")
        drawList(list)
        print("\nПоиск каждого второго элемента\n")
        var i = 1
        while (i < max) {
            println(list.find(i))
            i = i + 2
        }
        println("\nПроизошла сортировка\n")
        list.sort(builder!!.comparator)
        drawList(list)
        list.clear()
        println("Список удален")
    }
}