import java.util.Calendar;

public class Friend {

    private String firstName = "";
    private String lastName = "";
    private String birthday = "";
    // Height is stored as centimeters
    private double height = 0.0;
    private String height_unit = "";
    private String gender = "";
    private String otherInfo = "";

    Friend() {}
    
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
     * @return
     * The name of the friend or an empty String if the name hasn't been set
     */
    @Override
    public String toString() {
        return this.getName();
    }

    /**
     * @return
     * The first and last name separated by a space or an empty String if the
     * name hasn't been set
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * @return
     * The first name of the friend or an empty String if the first name hasn't
     * been set
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return
     * The last name of the friend or an empty String if the last name hasn't
     * been set
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @param firstName The first name of the friend
     * @param lastName  The last name of the friend
     */
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Check if the name is set
     * 
     * @return
     * {@code true} if the name has been set, otherwise returns {@code false}
     */
    public boolean isNameSet() {
        return !this.firstName.equals("") && !this.lastName.equals("");
    }

    /**
     * @return
     * The date of birth as a String in format year/month/day or an empty
     * String if the birth date hasn't been set
     */
    public String getBirthDate() {
        return this.birthday;
    }

    /**
     * @return
     * The birth year as an int. Will return 0 if no birth year was set
     */
    public int getBirthYear() {
        return !(this.birthday).equals("")
                ? Integer.parseInt((this.birthday).split("/")[0])
                : 0;
    }

    /**
     * @return
     * The birth month as an int. Will return 0 if no birth year was set
     */
    public int getBirthMonth() {
        return !(this.birthday).equals("")
                ? Integer.parseInt((this.birthday).split("/")[1])
                : 0;
    }

    /**
     * @return
     * The birth day as an int. Will return 0 if no birth day was set
     */
    public int getBirthDay() {
        return !(this.birthday).equals("")
                ? Integer.parseInt((this.birthday).split("/")[2])
                : 0;
    }

    /**
     * @param year  The year born
     * @param month The month born
     * @param day   The day born
     */
    public void setBirthDate(int year, int month, int day) {
        this.birthday = year + "/" + month + "/" + day;
    }

    /**
     * Check if the birth date is set
     * 
     * @return
     * {@code true} if the birth date has been set, otherwise returns
     * {@code false}
     */
    public boolean isBirthDateSet() {
        return !this.birthday.equals("");
    }

    /**
     * @return
     * The height in centimeters. If no height is specified, this returns 0.0
     */
    public double getHeight() {
        return this.height_unit.equals("in") ? this.height * 2.54 : this.height;
    }

    /**
     * @param unit The unit to return the height in (cm/in). cm is the default
     * @return
     * The height as a double. If no height is specified, this returns 0.0
     */
    public double getHeight(String unit) {
        if (this.height == 0.0) return 0.0;
        if (unit.equals("in")) {
            if (this.height_unit.equals("cm")) {
                return this.height / 2.54;
            }
            return this.height;
        }
        return this.getHeight();
    }

    /**
     * @param height    The height
     * @param unit      The unit the height was given (cm/in)
     */
    public void setHeight(double height, String unit) {
        this.height = height;
        this.setHeightUnit(unit);
    }

    /**
     * Check if the height was set
     * 
     * @return
     * {@code true} if the height was set, otherwise returns {@code false}
     */
    public boolean isHeightSet() {
        return this.height != 0.0;
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
        this.height_unit = unit.equals("in") ? "in" : "cm";
    }

    /**
     * @return
     * A String representing the gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Check if the gender was set
     * 
     * @return
     * {@code true} if the gender was set, otherwise returns {@code false}
     */
    public boolean isGenderSet() {
        return !this.gender.equals("");
    }

    /**
     * @return
     * A String of the other features
     */
    public String getOtherInfo() {
        return this.otherInfo;
    }

    /**
     * @param otherInfo A String of other features
     */
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    /**
     * Check if other info was set
     * 
     * @return
     * {@code true} if other info were set, otherwise returns {@code false}
     */
    public boolean isOtherInfoSet() {
        return !this.otherInfo.equals("");
    }

    /**
     * @return
     * The calculated age from the birthday as an int. If no birthday set,
     * returns 0
     */
    public int getAge() {
        return !this.birthday.equals("") ? calculateAge() : 0;
    }

    /**
     * Check if the age was set
     * @return
     * {@code true} if the age was set, otherwise returns {@code false}
     */
    public boolean isAgeSet() {
        return isBirthDateSet();
    }
}
