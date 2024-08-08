package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExcelConvertor {



    public List<Student> excelReader(String inputDirectory) {
        List<Student> students = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(inputDirectory);
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                int age = (int) row.getCell(2).getNumericCellValue();
                String speciality = row.getCell(3).getStringCellValue();

                Student student = new Student(id, name, age, speciality);
                students.add(student);
            }

            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void sortStudents(List<Student> students) {
        Collections.sort(students, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
    }

    public Student searchStudentByName(List<Student> students, String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }


}
