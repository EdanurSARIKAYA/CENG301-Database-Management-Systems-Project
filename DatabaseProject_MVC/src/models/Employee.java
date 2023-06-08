package models;

public class Employee {
    private int buildingNo;
    private String name;
    private String surname;
    private String job;
    private double salary;
    private int employeeID;
    private String date;
    private int phoneNumber;

    public Employee() {
    }

    public Employee(int buildingNo, String name, String surname, String job, double salary, int employeeID, String date, int phoneNumber) {
        this.buildingNo = buildingNo;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.salary = salary;
        this.employeeID = employeeID;
        this.date = date;
        this.phoneNumber = phoneNumber;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "BuildingNo": return buildingNo;
		case "Name": return name;
		case "Surname": return surname;
                case "Job": return job;
                case "Salary": return salary;
                case "EmployeeID": return employeeID;
                case "Date": return date;
		case "PhoneNumber": return phoneNumber;
                
		default: return null;
		}
    }
    
    @Override
    public String toString() {
        return "buildingNo=" + buildingNo + ", name=" + name + ", surname=" + surname + ", job=" + job + ", salary=" + salary + ", employeeID=" + employeeID + ", date=" + date + ", phoneNumber=" + phoneNumber + '}';
    }
    
    
}
