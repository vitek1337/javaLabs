package com.example.lab4;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.*;

import static com.example.lab4.FileWorks.ReadFile;
import static com.example.lab4.FileWorks.WriteFile;
import static com.example.lab4.Solution.findNum;
import static com.example.lab4.Solution.removeZeroRowsAndColumns;


class CustomTable extends JTable {
    public CustomTable() {
        super(new Matrix());
    }
}

public class Matrix extends AbstractTableModel {
    private List<String> columns;
    private List<List<Integer>> data;


    public Matrix(){
        this.columns = new ArrayList<>();
        this.data = new ArrayList<>();
        baseGeneration();
    }

    public void updateMatrix(List<List<Integer>> data){
        this.columns = new ArrayList<>();

        for (int i = 0; i < data.get(0).size(); i++){
            columns.add("#" + i);
        }

        fireTableRowsInserted(data.size() - 1, data.size() - 1);
        fireTableStructureChanged();
    }

    public void baseGeneration(){
        columns.add("#0");
        columns.add("#1");

        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            row.add(i);
        }
        data.add(row);
    }

    public int getRowCount(){
        return this.data.size();
    }

    @Override
    public int getColumnCount(){
        return this.columns.size();
    }

    @Override
    public Object getValueAt(int row, int column){
        return data.get(row).get(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int ColumnIndex){
        int totalValue = Integer.parseInt(aValue.toString());
        data.get(rowIndex).set(ColumnIndex, totalValue);
        fireTableCellUpdated(rowIndex, ColumnIndex);
    }

    public String getColumnName(int column) {
        return columns.get(column); // Возвращаем заголовок столбца
    }

    public void addRow(){
        List<Integer> row = new ArrayList<>();
        for(int i = 0; i < columns.size(); i++){
            row.add(i);
        }
        data.add(row);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void addColumn() {
        // Создаем новый столбец
        String newColumnName = "#" + columns.size();
        columns.add(newColumnName);

        // Добавляем новый столбец в каждую строку данных (заполняем нулями)
        for (List<Integer> row : data) {
            row.add(0); // Добавляем 0 в новый столбец
        }

        fireTableStructureChanged(); // Обновляем таблицу, добавив новый столбец
    }

    public void removeRow() {
        if (data.size() > 1) {
            data.remove(data.size() - 1); // Удаляем строку по индексу
            fireTableRowsDeleted(data.size(), data.size()); // Обновляем таблицу
        }
    }

    // Метод для удаления столбца
    public void removeColumn() {
        if (columns.size() > 1) {
            columns.remove(columns.size() - 1); // Удаляем заголовок столбца
            // Удаляем столбец из всех строк
            for (List<Integer> row : data) {
                row.remove(data.size() - 1);
            }
            fireTableStructureChanged(); // Обновляем структуру таблицы
        }
    }

    public void setDataFromFile(String filePath){
        this.columns = new ArrayList<>();
        this.data = new ArrayList<>();

        ReadFile(filePath, data);

        for (int i = 0; i < data.get(0).size(); i++){
            columns.add("#" + i);
        }

        fireTableRowsInserted(data.size() - 1, data.size() - 1);
        fireTableStructureChanged();
    }

    public void setSolveDataTask1(List<List<Integer>> data){
        this.columns = new ArrayList<>();

        removeZeroRowsAndColumns(data);
        for (int i = 0; i < data.get(0).size(); i++){
            columns.add("#" + i);
        }

        this.data = data;

        fireTableRowsInserted(data.size() - 1, data.size() - 1);
        fireTableStructureChanged();
    }

    public void setSolveDataTask2(List<List<Integer>> data, List<List<Integer>> data2){
        this.columns = new ArrayList<>();
        findNum(data, data2);

        for (int i = 0; i < data.get(0).size(); i++){
            columns.add("#" + i);
        }

        this.data = data2;

        fireTableRowsInserted(data.size() - 1, data.size() - 1);
        fireTableStructureChanged();
    }

    public void sendToFunc(){
        removeZeroRowsAndColumns(data);
    }

    public void readData(List<List<Integer>> newData){

        for (int i = 0; i < data.size(); i++){
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < data.get(i).size(); j++){
                row.add(data.get(i).get(j));
            }

            newData.add(row);
        }
    }

    public void setDataToFile(String file){
        WriteFile(file, this.data);
    }
}
