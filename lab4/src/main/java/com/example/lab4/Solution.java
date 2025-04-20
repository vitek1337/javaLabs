package com.example.lab4;

import java.util.*;

public class Solution {
    public static void findNum(List<List<Integer>> inputData, List<List<Integer>> newData){

        for (int i = 0; i < inputData.size(); i++){
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < inputData.get(i).size(); j++){
                row.add(countNums(inputData, i, j, inputData.get(i).get(j)));
            }
            newData.add(row);
        }
    }

    public static int countNums(List<List<Integer>> inputData, int row, int col, int num){
        int counter = 0;

        for (int i = 0; i < inputData.get(row).size(); i++){
            if (inputData.get(row).get(i) == num) counter++;
            System.out.println("row" + inputData.get(row).get(i) + " " + counter);
        }
        System.out.println();
        for (int j = 0; j < inputData.size(); j++) {
            if (j != row) {
                if (inputData.get(j).get(col) == num) counter++;
                System.out.println(inputData.get(j).get(col) + " " + counter);
            }
        }

        return counter;
    }


    public static void removeZeroRowsAndColumns(List<List<Integer>> inputData) {
        if (inputData == null || inputData.isEmpty()) {
            return;
        }

        // Множество для хранения индексов строк, которые нужно удалить
        Set<Integer> zeroRows = new HashSet<>();
        // Множество для хранения индексов столбцов, которые нужно удалить
        Set<Integer> zeroCols = new HashSet<>();

        // Проверяем строки
        for (int i = 0; i < inputData.size(); i++) {
            List<Integer> row = inputData.get(i);
            boolean isZeroRow = true;
            for (Integer num : row) {
                if (num != 0) {
                    isZeroRow = false;
                    break;
                }
            }
            if (isZeroRow) {
                zeroRows.add(i);
            }
        }

        // Проверяем столбцы
        int numCols = inputData.get(0).size();
        for (int j = 0; j < numCols; j++) {
            boolean isZeroCol = true;
            for (int i = 0; i < inputData.size(); i++) {
                if (inputData.get(i).get(j) != 0) {
                    isZeroCol = false;
                    break;
                }
            }
            if (isZeroCol) {
                zeroCols.add(j);
            }
        }

        // Удаляем строки, которые состоят только из нулей
        inputData.removeIf(row -> zeroRows.contains(inputData.indexOf(row)));

        // Удаляем столбцы, которые состоят только из нулей
        for (List<Integer> row : inputData) {
            row.removeIf(num -> zeroCols.contains(row.indexOf(num)));
        }
    }

    
}