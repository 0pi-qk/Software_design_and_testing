package git.group

import git.group.Builder.Builder
import git.group.List.DoIt
import git.group.List.TList
import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter

object Serialization {
    @Throws(Exception::class)
    fun saveToFile(list: TList, filename: String?, type: String?) {
        PrintWriter(filename).use { writer ->
            writer.println(type)
            list.forEach(object : DoIt {
                override fun doIt(o: Any?) {
                    writer.print(o)
                }
            })
        }
        println("\nСписок не сохранен!")
    }

    @Throws(Exception::class)
    fun loadFile(filename: String?): TList {
        BufferedReader(FileReader(filename)).use { br ->
            val type = br.readLine()
            if (!Factory.typeNameList.contains(type)) {
                throw Exception("Неправильная файловая структура")
            }
            val line = br.readLine()
            val items = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val arr = arrayOfNulls<Builder>(items.size)
            for (i in arr.indices) if (items[i] != "null") arr[i] = Factory.getBuilderByName(type)?.parseObject(
                items[i]
            ) as Builder
            println("\nСписок был загружен!")
            return TList(arr)
        }
    }
}