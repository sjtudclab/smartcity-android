package sjtu.dclab.smartcity.model;

/**
 * CitizenResident
 *
 * @author Jian Yang
 * @date 2015/7/30
 */
public class CitizenResident {
    private int id, age, status, user, apartment;
    private String name, identification_type, identification_value, marriage_status, employment_status,
            residence_category, resident_status, education_status, politics_status, migration_status,
            income_status, nation, gender, phone, relationship;

    @Override
    public String toString() {
        return "CitizenResident{}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification_type() {
        return identification_type;
    }

    public void setIdentification_type(String identification_type) {
        this.identification_type = identification_type;
    }

    public String getIdentification_value() {
        return identification_value;
    }

    public void setIdentification_value(String identification_value) {
        this.identification_value = identification_value;
    }

    public String getMarriage_status() {
        return marriage_status;
    }

    public void setMarriage_status(String marriage_status) {
        this.marriage_status = marriage_status;
    }

    public String getEmployment_status() {
        return employment_status;
    }

    public void setEmployment_status(String employment_status) {
        this.employment_status = employment_status;
    }

    public String getResidence_category() {
        return residence_category;
    }

    public void setResidence_category(String residence_category) {
        this.residence_category = residence_category;
    }

    public String getResident_status() {
        return resident_status;
    }

    public void setResident_status(String resident_status) {
        this.resident_status = resident_status;
    }

    public String getEducation_status() {
        return education_status;
    }

    public void setEducation_status(String education_status) {
        this.education_status = education_status;
    }

    public String getPolitics_status() {
        return politics_status;
    }

    public void setPolitics_status(String politics_status) {
        this.politics_status = politics_status;
    }

    public String getMigration_status() {
        return migration_status;
    }

    public void setMigration_status(String migration_status) {
        this.migration_status = migration_status;
    }

    public String getIncome_status() {
        return income_status;
    }

    public void setIncome_status(String income_status) {
        this.income_status = income_status;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
