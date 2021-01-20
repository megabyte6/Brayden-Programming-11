public class Main {
    public static void main(String args[]) {
        School school1 = new School("John Oliver Secondary School",
            "530 E 41st Ave, Vancouver, BC V5W 1P3",
            1114);
        
        // Add 10 students
        school1.addStudent(new Student("Jhon", "Oliver", 7, 377776));
        school1.addStudent(new Student("Harvie", "McDougall", 5, 904123));
        school1.addStudent(new Student("Jeremiah", "Daly", 8, 776340));
        school1.addStudent(new Student("Cali", "Rowe", 12, 841582));
        school1.addStudent(new Student("Sky", "Blair", 8, 906814));
        school1.addStudent(new Student("Brianna", "Roberts", 12, 595742));
        school1.addStudent(new Student("Malcolm", "Caldwell", 6, 849036));
        school1.addStudent(new Student("Shannen", "Whittaker", 5, 106968));
        school1.addStudent(new Student("Jayson", "Christie", 7, 169254));
        school1.addStudent(new Student("Brenna", "Brec", 5, 904123));

        // Add 3 teachers
        school1.addTeacher(new Teacher("Jade", "Pace", "Foreign Government Studies"));
        school1.addTeacher(new Teacher("Natalie", "Randolph", "AP Neuroscience"));
        school1.addTeacher(new Teacher("Neha", "Fleming", "Self Defense, Monsters"));

        // Display both lists
        System.out.println(school1.allStudents());
        System.out.println(school1.allTeachers());

        // Remove 2 students
        school1.removeStudent(new Student("Sky", "Blair", 8, 906814));
        school1.removeStudent(new Student("Brenna", "Brec", 5, 904123));

        // Remove 1 teacher
        school1.removeTeacher(new Teacher("Neha", "Fleming", "Self Defense, Monsters"));

        // Display both lists
        System.out.println(school1.allStudents());
        System.out.println(school1.allTeachers());
    }
}