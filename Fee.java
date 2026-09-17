import java.util.Scanner;

class FeeCalculator {

    int calculateFee(int tuitionFee) {
        return tuitionFee;
    }

    int calculateFee(int tuitionFee, int hostelFee) {
        return tuitionFee + hostelFee;
    }

    int calculateFee(int tuitionFee, int hostelFee, int transportFee) {
        return tuitionFee + hostelFee + transportFee;
    }

    double calculateFee(double tuitionFee, double scholarship) {
        return tuitionFee - scholarship;
    }
}

public class Fee {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FeeCalculator f = new FeeCalculator();

        String input = sc.nextLine().trim();
        String[] a = input.split("\\s+");

        if (a.length == 1) {
            int tuition = Integer.parseInt(a[0]);
            System.out.println(f.calculateFee(tuition));

        } 
        else if (a.length == 2) {

            int tuition = Integer.parseInt(a[0]);
            int hostel = Integer.parseInt(a[1]);

            System.out.println(f.calculateFee(tuition, hostel));

        } 
        else if (a.length == 3) {

            if (a[2].equalsIgnoreCase("Scholarship")) {
                double tuition = Double.parseDouble(a[0]);
                double scholarship = Double.parseDouble(a[1]);

                System.out.println(f.calculateFee(tuition, scholarship));
            } 
            else {
                int tuition = Integer.parseInt(a[0]);
                int hostel = Integer.parseInt(a[1]);
                int transport = Integer.parseInt(a[2]);

                System.out.println(f.calculateFee(tuition, hostel, transport));
            }
        }
    }
}