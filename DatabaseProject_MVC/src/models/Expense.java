package models;

public class Expense {
    private int buildingNo;
    private String date;
    private double elevatorMaintananceFee;
    private double waterBill;
    private double electricityBill;
    private double gasBill;
    private double gardenMaintananceFee;
    private double totalSalary;

    public Expense() {
    }

    public Expense(int buildingNo, String date, double elevatorMaintananceFee, double waterBill, double electricityBill, double gasBill, double gardenMaintananceBill, double totalSalary) {
        this.buildingNo = buildingNo;
        this.date = date;
        this.elevatorMaintananceFee = elevatorMaintananceFee;
        this.waterBill = waterBill;
        this.electricityBill = electricityBill;
        this.gasBill = gasBill;
        this.gardenMaintananceFee = gardenMaintananceBill;
        this.totalSalary = totalSalary;
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

    public double getElevatorMaintananceFee() {
        return elevatorMaintananceFee;
    }

    public void setElevatorMaintananceFee(double elevatorMaintananceFee) {
        this.elevatorMaintananceFee = elevatorMaintananceFee;
    }

    public double getWaterBill() {
        return waterBill;
    }

    public void setWaterBill(double waterBill) {
        this.waterBill = waterBill;
    }

    public double getElectricityBill() {
        return electricityBill;
    }

    public void setElectricityBill(double electricityBill) {
        this.electricityBill = electricityBill;
    }

    public double getGassBill() {
        return gasBill;
    }

    public void setGassBill(double gassBill) {
        this.gasBill = gassBill;
    }

    public double getGardenMaintananceFee() {
        return gardenMaintananceFee;
    }

    public void setGardenMaintananceFee(double gardenMaintananceFee) {
        this.gardenMaintananceFee = gardenMaintananceFee;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "BuildingNo": return buildingNo;
		case "Date": return date;
		case "ElevatorMaintananceFee": return elevatorMaintananceFee;
                case "WaterBill": return waterBill;
                case "ElectricityBill": return electricityBill;
                case "GasBill": return gasBill;
                case "GardenMaintananceFee": return gardenMaintananceFee;
		case "TotalSalary": return totalSalary;
                
		default: return null;
		}
    }
    
    @Override
    public String toString() {
        return "buildingNo=" + buildingNo + ", date=" + date + ", elevatorMaintananceFee=" + elevatorMaintananceFee + ", waterBill=" + waterBill + ", electricityBill=" + electricityBill + ", gassBill=" + gasBill + ", gardenMaintananceBill=" + gardenMaintananceFee + ", totalSalary=" + totalSalary + '}';
    }
    
    
    
}
