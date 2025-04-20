package com.example.lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class MainWindow extends JFrame {

    public MainWindow(){
        setTitle("ЛАБА НОМЕР 4");
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        CustomTable matrix = new CustomTable();
        add(new JScrollPane(matrix), BorderLayout.WEST);

        CustomTable matrixSolution = new CustomTable();
        add(new JScrollPane(matrixSolution), BorderLayout.EAST);


        JPanel taskChoosingFrame = new JPanel(new GridLayout(2, 1, 1, 1));
        JButton solveBtn = new JButton("Удалить нулевые строки и столбцы");

        String[] taskNum = {"Задание 1", "Задание 2", "Задание 3"};
        JComboBox taskChooser = new JComboBox(taskNum);

        taskChoosingFrame.add(solveBtn);
        taskChoosingFrame.add(taskChooser);
        add(taskChoosingFrame);

        JButton addColBtn = new JButton("Добавить строку");
        JButton addRowBtn = new JButton("Добавить столбец");
        JButton deleteColBtn = new JButton("Удалить строку");
        JButton deleteRowBtn = new JButton("Удалить столбец");
        JButton openFileBtn = new JButton("Прочитать матрицу из файла");
        JButton writeFileBtn = new JButton("Записать ответ в файл");


        addColBtn.addActionListener(e -> {
            ((Matrix) matrix.getModel()).addRow();
            }
        );

        writeFileBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == fileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                ((Matrix) matrixSolution.getModel()).setDataToFile(selectedFile.getPath());
            }
        });

        addRowBtn.addActionListener(e -> {
                    ((Matrix) matrix.getModel()).addColumn();
                }
        );

        deleteColBtn.addActionListener(e -> {
                    ((Matrix) matrix.getModel()).removeColumn();
                }
        );

        deleteRowBtn.addActionListener(e -> {
                    ((Matrix) matrix.getModel()).removeRow();
                }
        );

        openFileBtn.addActionListener(e ->{
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == fileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "выбран файл:" + selectedFile.getAbsolutePath());
                ((Matrix) matrix.getModel()).setDataFromFile(selectedFile.getAbsolutePath());
            }
        });

        solveBtn.addActionListener(e -> {
            List<List<Integer>> newData;
            newData = new ArrayList<>();

            int task = Integer.parseInt(((String) taskChooser.getSelectedItem()).split(" ")[1]);
            if (task == 1) {
                ((Matrix) matrix.getModel()).readData(newData);
                ((Matrix) matrixSolution.getModel()).setSolveDataTask1(newData);
            }
            else if (task == 2){
                List<List<Integer>> newData2;
                newData2 = new ArrayList<>();
                ((Matrix) matrix.getModel()).readData(newData);
                ((Matrix) matrixSolution.getModel()).setSolveDataTask2(newData, newData2);
            }
        });

        taskChooser.addActionListener(e -> {
            int task = Integer.parseInt(((String) taskChooser.getSelectedItem()).split(" ")[1]);
            if (task == 1) solveBtn.setText("Удалить нулевые строки и столбцы");

            else if (task == 2) solveBtn.setText("Посчитать количество вхождений числа в строку и столбец");

        });


        JPanel buttonPanel = new JPanel();

        buttonPanel.add(addColBtn);
        buttonPanel.add(addRowBtn);
        buttonPanel.add(deleteColBtn);
        buttonPanel.add(deleteRowBtn);
        buttonPanel.add(openFileBtn);
        buttonPanel.add(writeFileBtn);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);

    }

    public static void main(String[] args) {
        new MainWindow();
    }
}