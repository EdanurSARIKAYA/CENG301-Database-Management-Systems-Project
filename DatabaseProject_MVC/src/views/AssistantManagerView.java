package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import main.DatabaseUtilities;
import models.*;

public class AssistantManagerView implements ViewInterface {

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        switch (operationName) {
            case "select":
                return selectOperation(modelData);
            case "update":
                return updateOperation(modelData);
            case "select.gui":
                return selectGUI(modelData);
            case "update.gui":
                return updateGUI(modelData);
        }

        return new ViewData("MainMenuView", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int buildingNo = resultSet.getInt("BuildingNo");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");
                int phoneNumber = resultSet.getInt("PhoneNumber");
                int managerID = resultSet.getInt("ManagerID");
                int apartmentNo = resultSet.getInt("ApartmentNo");
                // Display values
                System.out.print(buildingNo + " ");
                System.out.print(name + " ");
                System.out.print(surname + " ");
                System.out.print(phoneNumber + " ");
                System.out.print(managerID + " ");
                System.out.println(apartmentNo);

            }
            resultSet.close();
        }

        return new ViewData("MainMenuView", "");
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("Number of updated rows is " + modelData.recordCount);

        return new ViewData("MainMenuView", "");
    }
	
    Map<String, Object> getWhereParameters() throws Exception {
        System.out.println("Filter conditions:");

        Integer managerID = getInteger("ManagerID : ", false);

        Map<String, Object> whereParameters = new HashMap<>();

        whereParameters.put("ManagerID", managerID);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("AssistantManager", "select", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        Scanner scan=new Scanner(System.in);
        Map<String, Object> updateParameters = new HashMap<>();
         Map<String, Object> parameters = new HashMap<>();
        Connection connection = DatabaseUtilities.getConnection();
        int ApartmentNo;
        System.out.print("Are you sitting in this apartment?(Y/N):");
        String answer = scan.next();
        if (answer.equalsIgnoreCase("Y")) {
        System.out.print("ApartmentNo:");
            ApartmentNo = scan.nextInt();
            String sqlCheckAptNo = ("SELECT * FROM Resident WHERE ApartmentNo=?");
            PreparedStatement prepStmt2 = connection.prepareStatement(sqlCheckAptNo);
            prepStmt2.setInt(1, ApartmentNo);
            ResultSet rs = prepStmt2.executeQuery();
            rs.next();
           // System.out.println("Fields to update:");
        updateParameters.put("Name", rs.getString(2));
        updateParameters.put("Surname", rs.getString(3));
        updateParameters.put("PhoneNumber", rs.getString(4));
        updateParameters.put("ApartmentNo", ApartmentNo);
            
        }
        else if (answer.equalsIgnoreCase("N")) {
        System.out.println("Fields to update:");
        String name = getString("Name : ", false);
        String surname = getString("Surname : ", false);
        Integer phoneNumber = getInteger("PhoneNumber : ", false);
        Integer apartmentNo = getInteger("ApartmentNo : ", false);
        System.out.println();
        updateParameters.put("Name", name);
        updateParameters.put("Surname", surname);
        updateParameters.put("PhoneNumber", phoneNumber);
        updateParameters.put("ApartmentNo", apartmentNo);
        }
        else{
        System.out.println("PLEASE ENTER 'Y' OR 'N' !!!!!!!");
        }
        
        parameters.put("updateParameters", updateParameters);
        //parameters.put("whereParameters", getWhereParameters());

        return new ViewData("AssistantManager", "update", parameters);
    }
    

    @Override
    public String toString() {
        return "AssistantManager View";
    }
}

        
