package git.group

import git.group.Builder.Builder
import git.group.List.TList
import java.awt.Font
import java.awt.event.ActionEvent
import javax.swing.*
import java.awt.BorderLayout
import java.awt.Dimension

class Gui internal constructor() : JFrame("Lab3") {
    private var builder: Builder? = null
    private var list: TList? = null

    init {
        val PanelMain= JPanel()
        val searchBtn= JButton("Поиск по списку")
        val sortBtn= JButton("Отсортировать список")
        val save= JButton("Сохранить в файл")
        val load= JButton("Загрузить из файла")
        val insertValueField= JTextField()
        val insertIndexField= JTextField()
        val insertBtn= JButton("Вставить в начало")
        val insertBtnBack= JButton("Вставить в конец")
        val deleteBtn= JButton("Удалить по индексу")
        val insertBtnbyIndex= JButton("Вставить по индексу")
        val showBtn= JButton("Вывести список")
        val clrBtn= JButton("Очистить список")
        val out = JTextArea()

        out!!.font = Font("Time New Roman", Font.PLAIN, 16)
        val scroll = JScrollPane()
        scroll!!.viewport.add(out)
        out.setBounds(0, 0, 600, 600)
        val builderType: JComboBox<*> = JComboBox<Any?>(Factory.typeNameList.toTypedArray())
        builderType!!.addActionListener { view: ActionEvent? ->
            println(builderType.selectedItem)
            val type = builderType.selectedItem
            val type1 = type.toString()
            try {
                builder = Factory.getBuilderByName(type1)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            list = TList(builder)
        }
        load!!.addActionListener { v: ActionEvent? ->
            list = try {
                Serialization.loadFile("file.txt")
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
            val str = """${out.text}
Список был загружен
""".trimIndent()
            out.text = str
        }
        save!!.addActionListener { v: ActionEvent? ->
            try {
                list?.let { Serialization.saveToFile(it, "file.txt", builderType.selectedItem.toString()) }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
            val str = """${out.text}
Список сохранен
""".trimIndent()
            out.text = str
        }
        insertValueField!!.toolTipText = "integer: int \nGPS: double;double:int:int:int"
        insertBtn!!.addActionListener { view: ActionEvent? ->
            var obj: Builder? = null
            try {
                obj = Factory
                    .getBuilderByName(builderType.selectedItem.toString())
                    ?.parseObject(insertValueField.text) as Builder
            } catch (e: Exception) {
                e.printStackTrace()
            }
            insertValueField.text = ""
            list!!.pushFront(obj)
            val str = """${out.text}Элемент: ${obj.toString()} вставлен в начало списка
"""
            out.text = str
        }
        insertBtnBack!!.addActionListener { view: ActionEvent? ->
            var obj: Builder? = null
            try {
                obj = Factory
                    .getBuilderByName(builderType.selectedItem.toString())
                    ?.parseObject(insertValueField.text) as Builder
            } catch (e: Exception) {
                e.printStackTrace()
            }
            insertValueField.text = ""
            list!!.pushEnd(obj)
            val str = """${out.text}Элемент: ${obj.toString()} вставлен в конец списка
"""
            out.text = str
        }
        insertBtnbyIndex!!.addActionListener { view: ActionEvent? ->
            var obj: Builder? = null
            try {
                obj = Factory
                    .getBuilderByName(builderType.selectedItem.toString())
                    ?.parseObject(insertValueField.text) as Builder
            } catch (e: Exception) {
                e.printStackTrace()
            }
            val index = insertIndexField!!.text.toInt()
            insertIndexField.text = ""
            insertValueField.text = ""
            list!!.add(obj, index)
            val str = """${out.text}Элемент: ${obj.toString()} вставлен в позицию: $index
"""
            out.text = str
        }
        searchBtn!!.addActionListener { view: ActionEvent? ->
            val index = insertIndexField!!.text.toInt()
            insertIndexField.text = ""
            val str = """${out.text}
По индексу: $index находится элемент : ${list!!.find(index)}
"""
            out.text = str
        }
        deleteBtn!!.addActionListener { view: ActionEvent? ->
            val index = insertIndexField!!.text.toInt()
            insertIndexField.text = ""
            insertValueField.text = ""
            list!!.delete(index)
            val str = """${out.text}
Элемент из позиции: $index удален
"""
            out.text = str
        }
        sortBtn!!.addActionListener { view: ActionEvent? ->
            list!!.sort(builder!!.comparator)
            val str = """${out.text}
Список отсортирован
""".trimIndent()
            out.text = str
        }
        clrBtn!!.addActionListener { view: ActionEvent? ->
            list!!.clear()
            val str = """${out.text}
Список очищен
""".trimIndent()
            out.text = str
        }
        showBtn!!.addActionListener { view: ActionEvent? ->
            val str = """${out.text}
Полученный список:
${list.toString()}""".trimIndent()
            out.text = """$str

""".trimIndent()
        }
        PanelMain.layout = BorderLayout()

        val menu = JPanel()
        menu.setPreferredSize(Dimension(325, 600))

        builderType.setPreferredSize(Dimension(305, 25))
        menu.add(builderType, BorderLayout.NORTH)

        val line1 = JPanel()
        save.setPreferredSize(Dimension(150, 25))
        line1.add(save, BorderLayout.EAST)
        load.setPreferredSize(Dimension(150, 25))
        line1.add(load, BorderLayout.WEST)
        menu.add(line1, BorderLayout.NORTH)

        val line2 = JPanel()
        val text1 =JLabel("Значение")
        text1.setPreferredSize(Dimension(80, 25))
        line2.add(text1, BorderLayout.EAST)
        insertValueField.setPreferredSize(Dimension(220, 25))
        line2.add(insertValueField, BorderLayout.WEST)
        menu.add(line2, BorderLayout.NORTH)

        val line3 = JPanel()
        insertBtn.setPreferredSize(Dimension(150, 25))
        line3.add(insertBtn, BorderLayout.EAST)
        insertBtnBack.setPreferredSize(Dimension(150, 25))
        line3.add(insertBtnBack, BorderLayout.WEST)
        menu.add(line3, BorderLayout.NORTH)

        val line4 = JPanel()
        val text2 =JLabel("Индекс")
        text2.setPreferredSize(Dimension(80, 25))
        line4.add(text2, BorderLayout.EAST)
        insertIndexField.setPreferredSize(Dimension(220, 25))
        line4.add(insertIndexField, BorderLayout.WEST)
        menu.add(line4, BorderLayout.NORTH)

        val line5 = JPanel()
        insertBtnbyIndex.setPreferredSize(Dimension(150, 25))
        line5.add(insertBtnbyIndex, BorderLayout.EAST)
        deleteBtn.setPreferredSize(Dimension(150, 25))
        line5.add(deleteBtn, BorderLayout.WEST)
        menu.add(line5, BorderLayout.NORTH)

        searchBtn.setPreferredSize(Dimension(305, 25))
        menu.add(searchBtn, BorderLayout.NORTH)

        sortBtn.setPreferredSize(Dimension(305, 25))
        menu.add(sortBtn, BorderLayout.NORTH)

        val line6 = JPanel()
        showBtn.setPreferredSize(Dimension(150, 25))
        line6.add(showBtn, BorderLayout.EAST)
        clrBtn.setPreferredSize(Dimension(150, 25))
        line6.add(clrBtn, BorderLayout.WEST)
        menu.add(line6, BorderLayout.NORTH)

        PanelMain.add(menu, BorderLayout.EAST)
        PanelMain.add(JScrollPane(out), BorderLayout.CENTER)

        contentPane = PanelMain
        setSize(925, 600)
        isResizable = false
        isVisible = true
        defaultCloseOperation = EXIT_ON_CLOSE
    }
}