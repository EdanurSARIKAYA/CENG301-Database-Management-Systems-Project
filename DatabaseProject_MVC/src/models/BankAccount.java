package models;

public class BankAccount {
    private int buildingNo;
    private String date;
    private double totalIncome;
    private double totalExpense;
    private double monthlyStatus;

    public BankAccount() {
    }

    public BankAccount(int buildingNo, String date, double totalIncome, double totalExpense, double monthlyStatus) {
        this.buildingNo = buildingNo;
        this.date = date;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.monthlyStatus = monthlyStatus;
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

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getMonthlyStatus() {
        return monthlyStatus;
    }

    public void setMonthlyStatus(double monthlyStatus) {
        this.monthlyStatus = monthlyStatus;
    }

    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "BuildingNo": return buildingNo;
		case "Date": return date;
		case "TotalIncome": return totalIncome;
                case "TotalExpense": return totalExpense;
                case "MonthlyStatus": return monthlyStatus;        
		default: return null;
		}
    }
    
    @Override
    public String toString() {
        return "buildingNo=" + buildingNo + ", date=" + date + ", totalIncome=" + totalIncome + ", totalExpense=" + totalExpense + ", monthlyStatus=" + monthlyStatus + '}';
    }
 
}
