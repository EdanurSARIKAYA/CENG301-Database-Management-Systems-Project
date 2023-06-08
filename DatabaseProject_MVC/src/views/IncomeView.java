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

public class IncomeView implements ViewInterface {

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
                Double shopRent = resultSet.getDouble("ShopRent");
                Double parkingFee = resultSet.getDouble("ParkingFee");
                Double advertisement = resultSet.getDouble("Advertisement");
                Double totalDue = resultSet.getDouble("TotalDue");

                // Display values
                System.out.println(buildingNo + " " + date + " " + shopRent + " " + parkingFee + " " + advertisement + " " + totalDue);

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

        return new ViewData("Income", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "BuildingNo, Date,ShopRent, ParkingFee, Advertisement, TotalDue");
        Connection con = DatabaseUtilities.getConnection();
        String date = getString("Date : ", false);
        List<Object> rows = new ArrayList<>();

        int buildingNo;
        double parkingFee, advertisement, totalDue, shopRent;
        String sqlQuery4 = "SELECT DISTINCT Date FROM Dues";
        PreparedStatement prepStmt4 = con.prepareStatement(sqlQuery4);
        ResultSet rs3 = prepStmt4.executeQuery();
        while (rs3.next()) {
            if (date.equals(rs3.getString(1))) {
                String sqlQuery3 = "SELECT DISTINCT Date FROM Income";
                PreparedStatement prepStmt3 = con.prepareStatement(sqlQuery3);
                ResultSet rs = prepStmt3.executeQuery();

                System.out.println("Fields to insert:");
                buildingNo = getInteger("BuildingNo : ", false);
                shopRent = getDouble("ShopRent : ", true);
                parkingFee = getDouble("ParkingFee : ", true);
                advertisement = getDouble("Advertisement : ", true);

                String sqlQuery2 = "SELECT Max(DueCount),Min(DueCount),DuesAmount,BuildingNo FROM Dues WHERE Date = ? GROUP BY DuesAmount,BuildingNo";
                PreparedStatement prepStmt2 = con.prepareStatement(sqlQuery2);
                prepStmt2.setString(1, date);
                ResultSet rs2 = prepStmt2.executeQuery();
                rs2.next();
                totalDue = (rs2.getInt(1) - rs2.getInt(2) + 1) * rs2.getDouble(3);
                System.out.println();

                rows.add(new Income(buildingNo, date, shopRent, parkingFee, advertisement, totalDue));

                parameters.put("rows", rows);
            }

        }
        return new ViewData("Income", "insert", parameters);
    }

    @Override
    public String toString() {
        return "Resident View";
    }
}
