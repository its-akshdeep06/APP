import java.util.Scanner;

public class AttendanceTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] attendance = new int[7];
        int presentDays = 0;

        System.out.println("Enter attendance for 7 days (1 = Present, 0 = Absent):");
        for (int i = 0; i < 7; i++) {
            System.out.print("Day " + (i + 1) + ": ");
            attendance[i] = sc.nextInt();
        }

        for (int i = 0; i < 7; i++) {
            if (attendance[i] == 1) {
                presentDays++;
            }
        }

        double percentage = ((double) presentDays / 7) * 100;
        System.out.println("\nPresent Days : " + presentDays + " / 7");
        System.out.printf("Attendance   : %.2f%%\n", percentage);

        if (percentage >= 75) {
            System.out.println("Eligible for Exam");
        } else {
            System.out.println("Not Eligible");
        }
    }
}