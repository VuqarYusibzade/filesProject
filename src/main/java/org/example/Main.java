package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Choose input file format: json(1) , xml(2) , excel(3)");
        int inputFileFormat = scan.nextInt();
        scan.nextLine();

        System.out.println("Input file directory: ");
        String inputDirectory = scan.nextLine();

        List<Student> students = null;

        switch (inputFileFormat) {
            case 1:
                students = MyFileReader.jsonReader(inputDirectory);
                MyFileReader.saveStudentsToExcel(students, "C:\\Users\\yusib\\Desktop\\students_json_output.xlsx");
                System.out.println("Was successful.");


                Collections.sort(students, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));


                Student foundStudent = MyFileReader.searchStudentByName(students, "Vugar");

                if (foundStudent != null) {
                    System.out.println("Student found: " + foundStudent);
                } else {
                    System.out.println("Student not found!");
                }

                break;
            case 2:
                students = MyFileReader.xmlReader(inputDirectory);
                MyFileReader.saveStudentsToExcel(students, "C:\\Users\\yusib\\Desktop\\students_xml_output.xlsx");
                System.out.println("Was successful.");


                Collections.sort(students, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));


                foundStudent = MyFileReader.searchStudentByName(students, "Vugar");

                if (foundStudent != null) {
                    System.out.println("Student found: " + foundStudent);
                } else {
                    System.out.println("Student not found!");
                }

                break;
            case 3:
                ExcelConvertor excelConvertor = new ExcelConvertor();
                students = excelConvertor.excelReader(inputDirectory);
                excelConvertor.sortStudents(students);

                if (students != null) {
                    Student foundStudentInExcel = MyFileReader.searchStudentByName(students, "Vugar Ysuibzada");
                    if (foundStudentInExcel != null) {
                        System.out.println("Student found in Excel: " + foundStudentInExcel);
                    } else {
                        System.out.println("Student not found in Excel!");
                    }
                } else {
                    System.out.println("No students in the list.");
                }
                break;

            default:
                System.out.println("Wrong input file format.");
        }

        if (students != null) {
            for (Student s : students)
                System.out.println(s);
        }
    }
}
