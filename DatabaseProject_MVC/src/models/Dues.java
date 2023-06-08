package models;

public class Dues {
    private int dueCount;
    private int buildingNo;
    private String date;
    private double duesAmount;
    private int apartmentNo;

    public Dues() {
    }

    public Dues(int dueCount, int buildingNo, String date, double duesAmount, int apartmentNo) {
        this.dueCount = dueCount;
        this.buildingNo = buildingNo;
        this.date = date;
        this.duesAmount = duesAmount;
        this.apartmentNo = apartmentNo;
    }

    public int getDueCount() {
        return dueCount;
    }

    public void setDueCount(int dueCount) {
        this.dueCount = dueCount;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDuesAmount() {
        return duesAmount;
    }

    public void setDuesAmount(double duesAmount) {
        this.duesAmount = duesAmount;
    }

    public int getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(int apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "DueCount": return dueCount;
		case "BuildingNo": return buildingNo;
		case "Date": return date;
                case "DuesAmount": return duesAmount;
                case "ApartmentNo": return apartmentNo;                
		default: return null;
		}
    }

    @Override
    public String toString() {
        return "dueCount=" + dueCount + ", buildingNo=" + buildingNo + ", date=" + date + ", duesAmount=" + duesAmount + ", apartmentNo=" + apartmentNo + '}';
    }
    
   
    
}
