import java.util.*;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        int[][] studentGrades = new int[numStudents][numSubjects];

        // Input grades for each student
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter grades for Student " + (i + 1) + ": ");
            for (int j = 0; j < numSubjects; j++) {
                System.out.print("Enter marks obtained in subject " + (j + 1) + ": ");
                studentGrades[i][j] = scanner.nextInt();
            }
        }

        // Calculate average, highest, and lowest scores
        int[] totalMarks = new int[numStudents];
        double[] averagePercentage = new double[numStudents];
        int[] highestScore = new int[numStudents];
        int[] lowestScore = new int[numStudents];

        for (int i = 0; i < numStudents; i++) {
            totalMarks[i] = 0;
            highestScore[i] = Integer.MIN_VALUE;
            lowestScore[i] = Integer.MAX_VALUE;

            for (int j = 0; j < numSubjects; j++) {
                totalMarks[i] += studentGrades[i][j];

                if (studentGrades[i][j] > highestScore[i]) {
                    highestScore[i] = studentGrades[i][j];
                }

                if (studentGrades[i][j] < lowestScore[i]) {
                    lowestScore[i] = studentGrades[i][j];
                }
            }

            averagePercentage[i] = ((double) totalMarks[i] / (numSubjects * 100)) * 100;
        }

        // Display results for each student
        for (int i = 0; i < numStudents; i++) {
            char grade;
            if (averagePercentage[i] >= 90) {
                grade = 'A';
            } else if (averagePercentage[i] >= 80) {
                grade = 'B';
            } else if (averagePercentage[i] >= 70) {
                grade = 'C';
            } else if (averagePercentage[i] >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }

            System.out.println("\nStudent " + (i + 1) + ":");
            System.out.println("Total Marks: " + totalMarks[i]);
            System.out.println("Average Percentage: " + averagePercentage[i] + "%");
            System.out.println("Highest Score: " + highestScore[i]);
            System.out.println("Lowest Score: " + lowestScore[i]);
            System.out.println("Grade: " + grade);
        }

        scanner.close();
    }
}