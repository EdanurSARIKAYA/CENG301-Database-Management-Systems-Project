package views;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import main.DatabaseUtilities;
import models.*;


public class BankAccountView implements ViewInterface {

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
                Double totalIncome = resultSet.getDouble("TotalIncome");
                Double totalExpense = resultSet.getDouble("TotalExpense");
                Double monthlyStatus = resultSet.getDouble("MonthlyStatus");

                // Display values
                System.out.println(buildingNo + " " + date + " " + totalIncome + " " + totalExpense + " " + monthlyStatus);

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

        return new ViewData("BankAccount", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "BuildingNo, Date,TotalIncome,TotalExpense,MonthlyStatus");
        Connection connection = DatabaseUtilities.getConnection();
        List<Object> rows = new ArrayList<>();

        String date;
        int buildingNo;       
        Scanner scan=new Scanner(System.in);
        System.out.print("New Date:");
        String Date = scan.next();
        String sqlQuery = "SELECT * FROM Income WHERE Date = ? ";
        PreparedStatement prepStmt = connection.prepareStatement(sqlQuery);
        prepStmt.setString(1, Date);
        ResultSet rs = prepStmt.executeQuery();
        rs.next();
        double totalIncome = rs.getDouble(3) + rs.getDouble(4) + rs.getDouble(5) + rs.getDouble(6);
        
         String sqlQuery2 = "SELECT * FROM Expense WHERE Date = ? ";
        PreparedStatement prepStmt2 = connection.prepareStatement(sqlQuery2);
        prepStmt2.setString(1, Date);
        ResultSet rs2 = prepStmt2.executeQuery();
        rs2.next();
        double totalExpense = rs2.getDouble(3) + rs2.getDouble(4) + rs2.getDouble(5) + rs2.getDouble(6) + rs2.getDouble(7) + rs2.getDouble(8);

        double monthlyStatus = totalIncome - totalExpense;
        System.out.println("Fields to insert:");
        
        buildingNo = getInteger("BuildingNo : ", false);
        date = getString("Date : ", false);
//        totalIncome = getDouble("TotalIncome : ", false);
//        totalExpense = getDouble("TotalExpense : ", false);
//        monthlyStatus = getDouble("MonthlyStatus : ", false);

        System.out.println();

        rows.add(new BankAccount(buildingNo, date, totalIncome, totalExpense, monthlyStatus));

        parameters.put("rows", rows);

        return new ViewData("BankAccount", "insert", parameters);
    }

    @Override
    public String toString() {
        return "BankAccount View";
    }
}
