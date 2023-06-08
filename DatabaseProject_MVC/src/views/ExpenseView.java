package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.DatabaseUtilities;
import models.*;

public class ExpenseView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        switch (operationName) {
            case "select":
                return selectOperation(modelData);
            case "insert":
                return insertOperation(modelData);
            case "select.gui":
                return selectGUI(modelData);
            case "insert.gui":
                return insertGUI(modelData);
        }

        return new ViewData("MainMenuView", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int buildingNo = resultSet.getInt("BuildingNo");
                String date = resultSet.getString("Date");
                Double elevator = resultSet.getDouble("ElevatorMaintananceFee");
                Double water = resultSet.getDouble("WaterBill");
                Double electricity = resultSet.getDouble("ElectricityBill");
                Double gas = resultSet.getDouble("GasBill");
                Double garden = resultSet.getDouble("GardenMaintananceFee");
                Double totalSalary = resultSet.getDouble("TotalSalary");

                // Display values
                System.out.println(buildingNo + " " + date + " " + elevator + " " + water + " " + electricity + " " + gas + " " + garden + " " + totalSalary);

            }
            resultSet.close();
        }

        return new ViewData("MainMenuView", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.println("Number of inserted rows is " + modelData.recordCount);

        return new ViewData("MainMenuView", "");
    }

    Map<String, Object> getWhereParameters() throws Exception {
        System.out.println("Filter conditions:");

        String date = getString("Date : ", false);

        Map<String, Object> whereParameters = new HashMap<>();

        whereParameters.put("Date", date);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Expense", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "BuildingNo, Date, ElevatorMaintananceFee, WaterBill, ElectricityBill, GasBill,GardenMaintananceFee,TotalSalary");
        Connection con = DatabaseUtilities.getConnection();
        List<Object> rows = new ArrayList<>();
        String date = getString("Date : ", false);

        int buildingNo;
        double water, electric, gas, garden, totalSalary = 0, elevator;
        String sqlQuery4 = "SELECT DISTINCT Date FROM Employee";
        PreparedStatement prepStmt4 = con.prepareStatement(sqlQuery4);
        ResultSet rs3 = prepStmt4.executeQuery();
        boolean flag2 = false;
        while (rs3.next()) {
            if (date.equals(rs3.getString(1))) {

                String sqlQuery3 = "SELECT DISTINCT Date FROM Expense";
                PreparedStatement prepStmt3 = con.prepareStatement(sqlQuery3);
                ResultSet rs = prepStmt3.executeQuery();
                System.out.println("Fields to insert:");
                buildingNo = getInteger("BuildingNo : ", false);
                elevator = getDouble("ElevatorMaintananceFee : ", false);
                water = getDouble("WaterBill : ", false);
                electric = getDouble("ElectricityBill : ", false);
                gas = getDouble("GasBill : ", false);
                garden = getDouble("GardenMaintananceFee : ", false);

                System.out.println();
                String sqlQuery2 = "SELECT Salary,BuildingNo FROM Employee WHERE Date = ? ";
                PreparedStatement prepStmt2 = con.prepareStatement(sqlQuery2);
                prepStmt2.setString(1, date);
                ResultSet rs2 = prepStmt2.executeQuery();

                while (rs2.next()) {
                    buildingNo = rs2.getInt(2);
                    totalSalary += rs2.getDouble(1);
                }
                rows.add(new Expense(buildingNo, date, elevator, water, electric, gas, garden, totalSalary));

                parameters.put("rows", rows);

            }

        }
        return new ViewData("Expense", "insert", parameters);
    }

    @Override
    public String toString() {
        return "Expense View";
    }
}
