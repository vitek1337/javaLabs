package com.example.lab4;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileWorks {
    public static void ReadFile(String filePath, List<List<Integer>> data){

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                List<Integer> row = new ArrayList<>();
                String[] lines = line.split(" "); // Печатает каждую строку из файла

                for (int i = 0; i < lines.length; i++){
                    row.add(Integer.parseInt(lines[i]));
                }
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Если возникает ошибка ввода-вывода, она будет обработана здесь
        }
    }

    public static void WriteFile(String filePath, List<List<Integer>> data){
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).size(); j++){
                    writter.write(data.get(i).get(j) + "\t");
                }
                writter.write("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
