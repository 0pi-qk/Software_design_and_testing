package com.example.rgr;

import com.example.rgr.Builder.Builder;
import com.example.rgr.List.TList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    private Button save, load, insertBtn, insertBtnBack, insertBtnbyIndex,
            deleteBtn, showBtn, searchBtn, clrBtn, sortBtn;
    private Spinner spinner;
    private EditText insertValueField, insertIndexField;
    private TextView out;
    private Builder builder;
    private TList list;
    private final static String FILE_NAME = "file.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.save);
        load = findViewById(R.id.load);
        insertBtn = findViewById(R.id.insertBtn);
        insertBtnBack = findViewById(R.id.insertBtnBack);
        insertBtnbyIndex = findViewById(R.id.insertBtnbyIndex);
        deleteBtn = findViewById(R.id.deleteBtn);
        showBtn = findViewById(R.id.showBtn);
        searchBtn = findViewById(R.id.searchBtn);
        clrBtn = findViewById(R.id.clrBtn);
        sortBtn = findViewById(R.id.sortBtn);
        spinner = findViewById(R.id.spinner);
        insertValueField = findViewById(R.id.insertValueField);
        insertIndexField = findViewById(R.id.insertIndexField);
        out = findViewById(R.id.out);

        ArrayAdapter<?> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Factory.getTypeNameList());
        spinner.setAdapter(adp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                try
                {
                    builder = Factory.getBuilderByName(spinner.getSelectedItem().toString());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                list = new TList(builder);

                Test test = new Test();

                try {
                    test.run();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
                    objectOutputStream.writeObject(spinner.getSelectedItem());

                    list.forEach((C)-> {
                        try {
                            objectOutputStream.writeObject(C);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                catch(IOException e) {
                    throw new RuntimeException(e);
                }

                out.setText("Список сохранен\n");
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fin = null;
                try {
                    list.clear();
                    fin = openFileInput(FILE_NAME);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fin);
                    String inType = objectInputStream.readObject().toString();
                    String nowType = spinner.getSelectedItem().toString();
                    if (inType.equals(nowType)){
                        Builder obj = null;
                            boolean eof = false;
                            while (!eof) {
                                try {
                                    obj = (Builder) Factory
                                            .getBuilderByName(spinner.getSelectedItem().toString())
                                            .parseObject(objectInputStream.readObject().toString());
                                    list.pushEnd(obj);
                                } catch (EOFException e) {
                                    eof = true;
                                }
                            }
                        out.setText("Список загружен\n");
                    }else{
                        out.setText("Неправильная файловая структура\n");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Builder obj = null;
                try {
                    obj = (Builder) Factory
                            .getBuilderByName(spinner.getSelectedItem().toString())
                            .parseObject(insertValueField.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                insertValueField.setText("");
                list.pushFront(obj);

                out.setText("Элемент: " + obj.toString() + " вставлен в начало списка\n");
            }
        });

        insertBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Builder obj = null;
                try {
                    obj = (Builder) Factory
                            .getBuilderByName(spinner.getSelectedItem().toString())
                            .parseObject(insertValueField.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                insertValueField.setText("");
                list.pushEnd(obj);

                out.setText("Элемент: " + obj.toString() + " вставлен в конец списка\n");
            }
        });

        insertBtnbyIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Builder obj = null;
                try {
                    obj = (Builder) Factory
                            .getBuilderByName(spinner.getSelectedItem().toString())
                            .parseObject(insertValueField.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int index = Integer.parseInt(insertIndexField.getText().toString());
                insertIndexField.setText("");
                insertValueField.setText("");
                list.add(obj, index);

                out.setText("Элемент: " + obj.toString() + " вставлен в позицию: " + index + "\n");
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = Integer.parseInt(insertIndexField.getText().toString());
                insertIndexField.setText("");
                insertValueField.setText("");
                list.delete(index);

                out.setText("Элемент из позиции: " + index + " удален\n");
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = Integer.parseInt(insertIndexField.getText().toString());
                insertIndexField.setText("");

                out.setText("По индексу: " + index + " находится элемент : " + list.find(index) + "\n");

            }
        });

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.sort(builder.getComparator());

                out.setText("Список отсортирован\n");
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out.setText("Полученный список:\n" + list.toString());
            }
        });

        clrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                out.setText("Список очищен\n");
            }
        });


    }
}