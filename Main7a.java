import coursemanagement.Course;
import doctor.Doctor;
import patient.Patient;
import studentmanagement.Student;

public class Main7a {
    public static void main(String[] args) {
        displayCollegeManagement();
        displayHospitalManagement();
    }

    private static void displayCollegeManagement() {
        Student student = new Student(101, "Aksh", "Computer Science");
        Course course = new Course("CS101", "Object-Oriented Programming", "Dr. Sharma", 4);

        System.out.println("===== COLLEGE MANAGEMENT SYSTEM =====");
        student.displayInformation();
        System.out.println();
        course.displayInformation();
    }

    private static void displayHospitalManagement() {
        Doctor cardiologist = new Doctor(1, "Dr. Mehta", "Cardiology", 1200.00);
        Doctor dermatologist = new Doctor(2, "Dr. Rao", "Dermatology", 900.00);

        Patient[] patients = {
            new Patient(101, "Riya", "Heart disease", 45),
            new Patient(102, "Vikram", "Skin allergy", 30),
            new Patient(103, "Neha", "Heart disease", 52)
        };
        Doctor[] assignedDoctors = {cardiologist, dermatologist, cardiologist};
        int[] patientCounts = new int[2];

        System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
        for (int index = 0; index < patients.length; index++) {
            Patient patient = patients[index];
            Doctor doctor = assignedDoctors[index];
            patient.displayDetails();
            System.out.println("Treating Doctor:");
            doctor.displayDetails();
            System.out.println("-----------------------------------");

            if (doctor == cardiologist) {
                patientCounts[0]++;
            } else {
                patientCounts[1]++;
            }
        }

        System.out.printf("Total fee collected by %s: %.2f%n",
                cardiologist.getName(), patientCounts[0] * cardiologist.getConsultationFee());
        System.out.printf("Total fee collected by %s: %.2f%n",
                dermatologist.getName(), patientCounts[1] * dermatologist.getConsultationFee());
    }
}
