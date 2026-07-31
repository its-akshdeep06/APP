import java.util.Scanner;

public class LargestThree {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a, b, c, largest;

        System.out.print("Enter first number: ");
        a = sc.nextInt();

        System.out.print("Enter second number: ");
        b = sc.nextInt();

        System.out.print("Enter third number: ");
        c = sc.nextInt();

        if (a > b) 
            largest = a;
        else if (b > c) 
            largest = b;
        else 
            largest = c;

        System.out.println("Largest number = " + largest);

        sc.close();
    }
}