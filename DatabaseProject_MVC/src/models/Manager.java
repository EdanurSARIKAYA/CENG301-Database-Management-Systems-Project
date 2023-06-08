package models;

public class Manager {
     private int buildingNo;
    private String name;
    private String surname;
    private int phoneNumber;
    private int controllerID;
    private int managerID;
    private int apartmentNo;

    public Manager() {
    }

    public Manager(int buildingNo, String name, String surname, int phoneNumber, int controllerID, int managerID, int apartmentNo) {
        this.buildingNo = buildingNo;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.controllerID = controllerID;
        this.managerID = managerID;
        this.apartmentNo = apartmentNo;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getControllerID() {
        return controllerID;
    }

    public void setControllerID(int controllerID) {
        this.controllerID = controllerID;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public int getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(int apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

     public Object getByName(String attributeName) {
		switch (attributeName) {
		case "BuildingNo": return buildingNo;
		case "Name": return name;
		case "Surname": return surname;
		case "PhoneNumber": return phoneNumber;
                case "ControllerID": return controllerID;
                case "ManagerID": return managerID;
                case "ApartmentNo": return apartmentNo;
		default: return null;
		}
    }
     
    @Override
    public String toString() {
        return "buildingNo=" + buildingNo + ", name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", controllerID=" + controllerID + ", managerID=" + managerID +", apartmentNo=" + apartmentNo + '}';
    }
    
    
}
