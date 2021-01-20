import java.util.ArrayList;

// This class creates a school object that 
// contains information about the school
public class School {
    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<String> courses = new ArrayList<>();
    private String name, location;
    private int population;

    // Constructor
    public School(String name, String location, int population) {
		this.name = name;
		this.location = location;
		this.population = population;
	}

    // Adds a new teacher object to the list of teachers
    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    // Adds a new student object to the list of students
    public void addStudent(Student student) {
        this.students.add(student);
    }

    // Removes a teacher object from the list of teachers
    public void removeTeacher(Teacher teacher) {
        for (int i = 0; i < this.teachers.size(); i++) {
            if (teacher.equals(this.teachers.get(i))) {
                this.teachers.remove(i);
                break;
            }
        }
    }

    // Removes a student object from the list of students
    public void removeStudent(Student student) {
        for (int i = 0; i < this.students.size(); i++) {
            if (student.equals(this.students.get(i))) {
                this.students.remove(i);
                break;
            }
        }
    }

    // Returns all teacher objects
    public String allTeachers() {
        String output = "";
        for (Teacher teacher : teachers) {
            output = output + "\n" + teacher;
        }
        return output;
    }

    // Returns all student objects
    public String allStudents() {
        String output = "";
        for (Student student : students) {
            output = output + "\n" + student;
        }
        return output;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
}