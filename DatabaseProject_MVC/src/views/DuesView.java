package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import main.DatabaseUtilities;
import models.*;

public class DuesView implements ViewInterface {

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
                int dueCount = resultSet.getInt("DueCount");
                int buildingNo = resultSet.getInt("BuildingNo");
                String date = resultSet.getString("Date");
                Double duesAmount = resultSet.getDouble("DuesAmount");
                int apartmentNo = resultSet.getInt("ApartmentNo");

                // Display values
                System.out.println(dueCount + " " + buildingNo + " " + date + " " + duesAmount + " " + apartmentNo);

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

        if (date != null) {
            whereParameters.put("Date", date);
        }

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Dues", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "DueCount,BuildingNo, Date,DuesAmount,ApartmentNo");
        List<Object> rows = new ArrayList<>();
        Connection con = DatabaseUtilities.getConnection();
        String dueDate;
        int buildingNo, dueCount, apartmentNo;
        double dueAmount;
        
        String sqlQuery = "SELECT DISTINCT Date FROM Dues";
        PreparedStatement prepStmt = con.prepareStatement(sqlQuery);
        ResultSet rs = prepStmt.executeQuery();
        
        String sqlQuery2 = "SELECT * FROM Resident";
        PreparedStatement prepStmt2 = con.prepareStatement(sqlQuery2);
        ResultSet rs2 = prepStmt2.executeQuery();
        rs2.next();
        
        String sqlQuery4 = "SELECT COUNT(ApartmentNo) FROM Resident";
        PreparedStatement prepStmt4 = con.prepareStatement(sqlQuery4);
        ResultSet rs4 = prepStmt4.executeQuery();
        rs4.next();
        
        dueDate = getString("New due date : ", false);
        
        String sqlQuery5 = "SELECT COUNT(1) FROM Dues WHERE Date=? GROUP BY Date ";
        PreparedStatement prepStmt5 = con.prepareStatement(sqlQuery5);
        prepStmt5.setString(1, dueDate);
        ResultSet rs5 = prepStmt5.executeQuery();
        rs5.next();
        
        
        boolean flag = false;
        
        
        while (rs.next()) {
            if (rs.getString(1).equals(dueDate)&&rs4.getInt(1)-1 < rs5.getInt(1)) {
                System.out.print("This month's dues have already been taken.");
                flag = true;
                return new ViewData(null,null,null);
            }
        }
        
         if( flag == false) {
                dueAmount = getDouble("Due amount : ", false);
                apartmentNo=getInteger("Apartment no : ", false);
                    String sqlQuery3 = "SELECT Max(DueCount) FROM Dues";
                    PreparedStatement prepStmt3 = con.prepareStatement(sqlQuery3);
                    ResultSet rs3 = prepStmt3.executeQuery();
                    rs3.next();
                    
                    dueCount=rs3.getInt(1) + 1;
                    buildingNo=rs2.getInt(1);
                    
                    rows.add(new Dues(dueCount, buildingNo, dueDate, dueAmount, apartmentNo));
                    parameters.put("rows", rows);
                
         }
         return new ViewData("Dues", "insert", parameters);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("fieldNames", "DueCount,BuildingNo, Date,DuesAmount,ApartmentNo");
//        Connection connection = DatabaseUtilities.getConnection();
//        List<Object> rows = new ArrayList<>();
//        Scanner scan = new Scanner(System.in, "iso-8859-9");
//        String dueDate;
//        int buildingNo, dueCount, apartmentNo;
//        double dueAmount;
//
//        String sqlQuery4 = "SELECT DISTINCT Date FROM Dues GROUP BY Date";
//        PreparedStatement prepStmt4 = connection.prepareStatement(sqlQuery4);
//        ResultSet rs3 = prepStmt4.executeQuery();
//        System.out.println("Fields to insert:");
//        String sqlQuery = "SELECT ApartmentNo FROM Manager";
//        PreparedStatement prepStmt = connection.prepareStatement(sqlQuery);
//        ResultSet rs = prepStmt.executeQuery();
//        String sqlQuery2 = "SELECT COUNT(ApartmentNo) FROM Resident";
//        PreparedStatement prepStmt2 = connection.prepareStatement(sqlQuery);
//        ResultSet rs4 = prepStmt2.executeQuery();
//        rs4.next();
//        dueDate = getString("New due date : ", false);
//        String sqlQuery5 = "SELECT COUNT(1) FROM Dues WHERE Date=? GROUP BY Date ";
//        PreparedStatement prepStmt5 = connection.prepareStatement(sqlQuery5);
//        prepStmt5.setString(1, dueDate);
//        ResultSet rs5 = prepStmt5.executeQuery();
//        rs5.next();
//        boolean flag = false;
//        String sqlQuery3 = "SELECT Max(DueCount) FROM Dues";
//        PreparedStatement prepStmt3 = connection.prepareStatement(sqlQuery3);
//        ResultSet rs2 = prepStmt3.executeQuery();
//        rs2.next();
//        if (rs4.getInt(1) <= rs5.getInt(1)) {
//            if (flag == false) {
//                rs.next();
//                apartmentNo = getInteger("Apartment no : ", false);
//                if (apartmentNo != rs.getInt(1)) {
//                    dueAmount = getDouble("Due amount : ", false);
//                    dueCount = (rs2.getInt(1) + 1);
//                    buildingNo = 1;
//                    System.out.println();
//                    rows.add(new Dues(dueCount, buildingNo, dueDate, dueAmount, apartmentNo));
//                    parameters.put("rows", rows);
//                } else {
//                    dueCount = (rs2.getInt(1) + 1);
//                    buildingNo = 1;
//                    dueAmount = 0;
//                    System.out.println();
//                    rows.add(new Dues(dueCount, buildingNo, dueDate, dueAmount, apartmentNo));
//                    parameters.put("rows", rows);
//                    System.out.println("Manager doesn't pay due.!");
//                }
//            }
//        }
//
//        return new ViewData("Dues", "insert", parameters);
    }

    @Override
    public String toString() {
        return "Dues View";
    }
}
