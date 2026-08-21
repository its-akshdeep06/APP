import java.util.*;

public class CandidateSelection {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] id = new int[n];
        String[] name = new String[n];
        int[] total = new int[n];

        // Input
        for (int i = 0; i < n; i++) {
            id[i] = sc.nextInt();
            name[i] = sc.next();

            total[i] = sc.nextInt()
                    + sc.nextInt()
                    + sc.nextInt();
        }

        // Sorting
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {

                if (total[i] < total[j] ||
                   (total[i] == total[j] && id[i] > id[j])) {

                    int temp = total[i];
                    total[i] = total[j];
                    total[j] = temp;

                    temp = id[i];
                    id[i] = id[j];
                    id[j] = temp;

                    String tempName = name[i];
                    name[i] = name[j];
                    name[j] = tempName;
                }
            }
        }

        // Output
        for (int i = 0; i < k; i++) {
            System.out.println(id[i] + " " + name[i] + " " + total[i]);
        }

        sc.close();
    }
}
