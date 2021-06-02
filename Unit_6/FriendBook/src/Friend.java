import java.util.Calendar;

public class Friend {

    private String firstName;
    private String lastName;
    private String birthday = "";
    // Height is stored as centimeters
    private double height = 0.0;
    private String height_unit = "";
    private String gender = "";
    private String otherFeatures = "";

    Friend(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private int calculateAge() {
        int yearBorn = Integer.parseInt(this.birthday.split("/")[0]);
        int monthBorn = Integer.parseInt(this.birthday.split("/")[1]);
        int dayBorn = Integer.parseInt(this.birthday.split("/")[2]);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        // If the current month equals the month born, check the day born
        // Otherwise if the current day of the month is less than the day of
        // the month born, the Friend's birthday hasn't passed yet
        if (currentMonth == monthBorn && currentDay < dayBorn) {
            return (currentYear - yearBorn) - 1;

        // If the current month is less than the month born, the Friend's
        // birthday hasn't passed yet
        } else if (currentMonth < monthBorn) {
            return (currentYear - yearBorn) - 1;
        }

        // Otherwise, get the age based on the current year
        return currentYear - yearBorn;
    }

    /**
     * @return The name of the friend
     */
    @Override
    public String toString() {
        return this.getName();
    }

    /**
     * @return The first and last name separated by a space
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * @return The first name of the friend
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return The last name of the friend
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Set the name for the friend
     * @param firstName The first name of the friend
     * @param lastName  The last name of the friend
     */
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return The date of birth as a String in format year/month/day
     */
    public String getBirthDate() {
        return this.birthday;
    }

    /**
     * @return The birth year as an int. Will return 0 if no birth year was set
     */
    public int getBirthYear() {
        if (!(this.birthday).equals("")) {
            return Integer.parseInt((this.birthday).split("/")[0]);
        }
        return 0;
    }

    /**
     * @return The birth month as an int. Will return 0 if no birth year was set
     */
    public int getBirthMonth() {
        if (!(this.birthday).equals("")) {
            return Integer.parseInt((this.birthday).split("/")[1]);
        }
        return 0;
    }

    /**
     * @return The birth day as an int. Will return 0 if no birth day was set
     */
    public int getBirthDay() {
        if (!(this.birthday).equals("")) {
            return Integer.parseInt((this.birthday).split("/")[2]);
        }
        return 0;
    }

    /**
     * Set the date of birth
     * @param year  The year born
     * @param month The month born
     * @param day   The day born
     */
    public void setBirthDate(int year, int month, int day) {
        this.birthday = year + "/" + month + "/" + day;
    }

    /**
     * @return The height in centimeters. If no height is specified, this returns 0.0
     */
    public double getHeight() {
        if ((this.height_unit).equals("in")) {
            return this.height * 2.54;
        }
        return this.height;
    }

    /**
     * @param unit The unit to return the height in (cm/in). cm is the default
     * @return The height as a double
     */
    public double getHeight(String unit) {
        if (unit.equals("in")) {
            if ((this.height_unit).equals("cm")) {
                return this.height / 2.54;
            }
            return this.height;
        }
        return this.getHeight();
    }

    /**
     * Set the height of this friend object
     * @param height    The height
     * @param unit      The unit the height was given (cm/in)
     */
    public void setHeight(double height, String unit) {
        this.height = height;
        this.setHeightUnit(unit);
    }

    /**
     * @return The unit the height is in (cm/in)
     */
    public String getHeightUnit() {
        return this.height_unit;
    }

    /**
     * Set the unit the height is in
     * @param unit The unit the height is given in (cm/in). Default is cm
     */
    public void setHeightUnit(String unit) {
        if (unit.equals("in")) {
            this.height_unit = "in";
            return;
        }
        this.height_unit = "cm";
    }

    /**
     * @return A String representing the gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * @param gender    Set gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return A String of the other features
     */
    public String getOtherFeatures() {
        return this.otherFeatures;
    }

    /**
     * @param otherFeatures A String of other features
     */
    public void setOtherFeatures(String otherFeatures) {
        this.otherFeatures = otherFeatures;
    }

    /**
     * @return The calculated age from the birthday as an int
     */
    public int getAge() {
        if (!this.birthday.equals("")) {
            return calculateAge();
        } else {
            return 0;
        }
    }
}
