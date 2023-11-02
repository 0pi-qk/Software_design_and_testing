package git.group;

import git.group.Builder.Builder;
import git.group.List.TList;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class view extends JFrame{
    private JTextArea out;
    private JButton searchBtn;
    private JButton sortBtn;
    private JButton save;
    private JButton load;
    private JTextField insertValueField;
    private JTextField insertIndexField;
    private JButton insertBtn;
    private JButton insertBtnBack;
    private JButton deleteBtn;
    private JButton insertBtnbyIndex;
    private JButton showBtn;
    private JButton clrBtn;
    private JPanel PanelMain;
    private JComboBox builderType;
    private JScrollPane scroll;

    private Builder builder;

    private TList list;

    public view() {
        out.setFont(new Font("Time New Roman", Font.PLAIN, 16));

        scroll.getViewport().add(out);
        out.setBounds(0,0,600,600);


        Factory.getTypeNameList().forEach((C)-> builderType.addItem(C));

        builderType.addActionListener(view -> {
            System.out.println(builderType.getSelectedItem());
            Object type = builderType.getSelectedItem();
            String type1 = type.toString();
            try
            {
                builder = Factory.getBuilderByName(type1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            list = new TList(builder);
        });

        load.addActionListener(v -> {
            try {
                list = Serialization.loadFile("file.txt");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String str = out.getText() + "Список был загружен\n";
            out.setText(str);
        });

        save.addActionListener(v -> {
            try {
                Serialization.saveToFile(list, "file.txt", builderType.getSelectedItem().toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String str = out.getText() + "Список сохранен\n";
            out.setText(str);
        });

        insertValueField.setToolTipText("integer: int \nGPS: double;double:int:int:int");

        insertBtn.addActionListener(view -> {
            Builder obj = null;
            try {
                obj = (Builder) Factory
                        .getBuilderByName(builderType.getSelectedItem().toString())
                        .parseObject(insertValueField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            insertValueField.setText("");
            list.pushFront(obj);
            String str = out.getText() + "Элемент: " + obj.toString() + " вставлен в начало списка\n" ;
            out.setText(str);
        });

        insertBtnBack.addActionListener(view -> {
            Builder obj = null;
            try {
                obj = (Builder) Factory
                        .getBuilderByName(builderType.getSelectedItem().toString())
                        .parseObject(insertValueField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            insertValueField.setText("");
            list.pushEnd(obj);
            String str = out.getText() + "Элемент: " + obj.toString() + " вставлен в конец списка\n" ;
            out.setText(str);
        });

        insertBtnbyIndex.addActionListener(view -> {
            Builder obj = null;
            try {
                obj = (Builder) Factory
                        .getBuilderByName(builderType.getSelectedItem().toString())
                        .parseObject(insertValueField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            int index = Integer.parseInt(insertIndexField.getText());
            insertIndexField.setText("");
            insertValueField.setText("");
            list.add(obj, index);
            String str = out.getText() + "Элемент: " + obj.toString() + " вставлен в позицию: " + index + "\n";
            out.setText(str);
        });

        searchBtn.addActionListener(view -> {
            int index = Integer.parseInt(insertIndexField.getText());
            insertIndexField.setText("");
            String str = out.getText() + "По индексу: " + index + " находится элемент : " + list.find(index) + "\n";
            out.setText(str);
        });

        deleteBtn.addActionListener(view -> {
            int index = Integer.parseInt(insertIndexField.getText());
            insertIndexField.setText("");
            insertValueField.setText("");
            list.delete(index);
            String str = out.getText() + "Элемент из позиции: " + index + " удален\n";
            out.setText(str);
        });

        sortBtn.addActionListener(view -> {
            list.sort(builder.getComparator());
            String str = out.getText() + "Список отсортирован\n";
            out.setText(str);

        });

        clrBtn.addActionListener(view -> {
            list.clear();
            String str = out.getText() + "Список очищен\n";
            out.setText(str);

        });

        showBtn.addActionListener(view -> {
            String str = out.getText() + "Полученный список:\n" + list.toString();
            out.setText(str+"\n");
        });

    }

    public static void main(String[] args) throws Exception {
        Builder builder = Factory.getBuilderByName("Integer,GPS");

        JFrame frame = new JFrame("View");
        frame.setContentPane(new view().PanelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Test test = new Test();
        test.run();
    }

}
