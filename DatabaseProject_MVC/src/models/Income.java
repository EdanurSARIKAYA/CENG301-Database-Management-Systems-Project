package models;

public class Income {
    private int buildingNo;
    private String date;
    private double shopRent;
    private double parkingFee;
    private double advertisement;
    private double totalDue;

    public Income() {
    }

    public Income(int buildingNo, String date, double shopRent, double parkingFee, double advertisement, double totalDue) {
        this.buildingNo = buildingNo;
        this.date = date;
        this.shopRent = shopRent;
        this.parkingFee = parkingFee;
        this.advertisement = advertisement;
        this.totalDue = totalDue;
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

    public double getShopRent() {
        return shopRent;
    }

    public void setShopRent(double shopRent) {
        this.shopRent = shopRent;
    }

    public double getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(double parkingFee) {
        this.parkingFee = parkingFee;
    }

    public double getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(double advertisement) {
        this.advertisement = advertisement;
    }

    public double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(double totalDue) {
        this.totalDue = totalDue;
    }
    
    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "BuildingNo": return buildingNo;
		case "Date": return date;
		case "ShopRent": return shopRent;
                case "ParkingFee": return parkingFee;
                case "Advertisement": return advertisement;
                case "TotalDue": return totalDue;
                
		default: return null;
		}
    }

    @Override
    public String toString() {
        return "buildingNo=" + buildingNo + ", date=" + date + ", shopRent=" + shopRent + ", parkingFee=" + parkingFee + ", advertisement=" + advertisement + ", totalDue=" + totalDue + '}';
    }
  
}
