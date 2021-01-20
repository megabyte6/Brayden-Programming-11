// This class creates a student object that 
// contains information about the student
public class Student {
    private String firstName, lastName;
    private int grade, studentNumber;

    // Constructor
	public Student(String firstName, String lastName, int grade, int studentNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.studentNumber = studentNumber;
    }

    // Used to convert the student information into a string
	@Override
	public String toString() {
		return "Name: " + this.firstName + " " + this.lastName + " Grade: " + this.grade;
	}

	// Used to check if two student objects are equal
	public boolean equals(Student student) {
		if (this.firstName.equals(student.firstName) &&
			this.lastName.equals(student.lastName) &&
			this.grade == student.grade &&
			this.studentNumber == student.studentNumber) {
			return true;
		} else {
			return false;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
}