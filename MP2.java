import java.util.*;

public class MP2 {
    private HashSet<String> courseCodes;
    private HashSet<String> enrollmentPairs;
    private HashMap<Integer, String> students;
    private Scanner scanner;

    public MP2() {
        courseCodes = new HashSet<>();
        enrollmentPairs = new HashSet<>();
        students = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;
        do {
            System.out.println("\nCourse Registration System\n1. Add Student\n2. Add Course\n3. Enroll\n4. List Student Courses\n5. List Courses\n0. Exit");
            System.out.print("Enter choice: ");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.print("\nInvalid input. Enter choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    enrollStudent();
                    break;
                case 4:
                    listStudentCourses();
                    break;
                case 5:
                    listCourses();
                    break;
                case 0:
                    System.out.println("\nExiting...");
                    break;
                default:
                    System.out.println("\nInvalid choice.");
            }
        } while (choice != 0);
    }

    private void addStudent() {
        System.out.print("\nEnter student ID and Name: ");
        String input = scanner.nextLine().trim();
        String[] parts = input.split("\\s+", 2);
        if (parts.length < 2) {
            System.out.println("Invalid format.");
            return;
        }
        try {
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            students.put(id, name);
            System.out.println("\nStudent added");
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid ID format.");
        }
    }

    private void addCourse() {
        System.out.print("\nEnter course code: ");
        String code = scanner.nextLine().trim().toUpperCase();
        if (courseCodes.contains(code)) {
            System.out.println("\nDuplicate rejected");
        } else {
            courseCodes.add(code);
            System.out.println("\nCourse added");
        }
    }

    private void enrollStudent() {
        System.out.print("\nEnter student ID and course code: ");
        String input = scanner.nextLine().trim();
        String[] parts = input.split("\\s+");
        if (parts.length < 2) {
            System.out.println("\nInvalid format.");
            return;
        }
        try {
            int id = Integer.parseInt(parts[0]);
            String course = parts[1].toUpperCase();

            if (!students.containsKey(id)) {
                System.out.println("\nStudent not found");
                return;
            }
            if (!courseCodes.contains(course)) {
                System.out.println("\nCourse not found");
                return;
            }

            String pair = id + "-" + course;
            if (enrollmentPairs.contains(pair)) {
                System.out.println("\nDuplicate enrollment rejected");
            } else {
                enrollmentPairs.add(pair);
                System.out.println("\nEnrollment added");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid ID format.");
        }
    }

    private void listStudentCourses() {
        System.out.print("\nEnter student ID: ");
        String input = scanner.nextLine().trim();
        try {
            int id = Integer.parseInt(input);
            if (!students.containsKey(id)) {
                System.out.println("\nStudent not found");
                return;
            }

            List<String> enrolled = new ArrayList<>();
            for (String pair : enrollmentPairs) { 
                String[] parts = pair.split("-");
                if (Integer.parseInt(parts[0]) == id) {
                    enrolled.add(parts[1]);
                }
            }

            if (enrolled.isEmpty()) {
                System.out.println("\nNo courses enrolled.");
            } else {
                System.out.println(String.join(" and ", enrolled));
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid ID format.");
        }
    }

    private void listCourses() {
        if (courseCodes.isEmpty()) {
            System.out.println("\nNo courses available.");
            return;
        }
        List<String> list = new ArrayList<>();
        for (String code : courseCodes) { 
            list.add(code);
        }
        System.out.println(String.join(", ", list));
    }

    public static void main(String[] args) {
        new MP2().run();
    }
}