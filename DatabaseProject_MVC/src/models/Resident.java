
package models;

public class Resident {
    private int buildingNo;
    private String name;
    private String surname;
    private int phoneNumber;
    private int apartmentNo;
    private String residentType;

    public Resident() {
    }

    public Resident(int buildingNo, String name, String surname, int phoneNumber, int apartmentNo, String residentType) {
        this.buildingNo = buildingNo;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.apartmentNo = apartmentNo;
        this.residentType = residentType;
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

    public int getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(int apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public String getResidentType() {
        return residentType;
    }

    public void setResidentType(String residentType) {
        this.residentType = residentType;
    }

    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "BuildingNo": return buildingNo;
		case "Name": return name;
		case "Surname": return surname;
		case "PhoneNumber": return phoneNumber;
                case "ApartmentNo": return apartmentNo;
                case "ResidentType": return residentType;
		default: return null;
		}
    }
    
    @Override
    public String toString() {
        return "buildingNo=" + buildingNo + ", name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", apartmentNo=" + apartmentNo + ", residentType=" + residentType + '}';
    }
    
    
}

