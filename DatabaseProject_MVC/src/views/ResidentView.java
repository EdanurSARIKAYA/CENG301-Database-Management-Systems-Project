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

public class ResidentView implements ViewInterface {

    public ArrayList<Resident> residentArrayList = new ArrayList<Resident>();

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

        switch (operationName) {
            case "select":
                return selectOperation(modelData);
            case "insert":
                return insertOperation(modelData);
            case "update":
                return updateOperation(modelData);
            case "delete":
                return deleteOperation(modelData);
            case "select.gui":
                return selectGUI(modelData);
            case "insert.gui":
                return insertGUI(modelData);
            case "update.gui":
                return updateGUI(modelData);
            case "delete.gui":
                return deleteGUI(modelData);
        }

        return new ViewData("MainMenuView", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;
        int i = 0;
        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int buildingNo = resultSet.getInt("BuildingNo");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");
                int phoneNumber = resultSet.getInt("PhoneNumber");
                int apartmentNo = resultSet.getInt("ApartmentNo");
                String residentType = resultSet.getString("ResidentType");
                // Display values
                System.out.println(buildingNo + " " + name + " " + surname + " " + phoneNumber + " " + apartmentNo + " " + residentType);

            }
            resultSet.close();
        }

        return new ViewData("MainMenuView", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.println("Number of inserted rows is " + modelData.recordCount);

        return new ViewData("MainMenuView", "");
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("Number of updated rows is " + modelData.recordCount);

        return new ViewData("MainMenuView", "");
    }

    ViewData deleteOperation(ModelData modelData) throws Exception {
        System.out.println("Number of deleted rows is " + modelData.recordCount);

        return new ViewData("MainMenuView", "");
    }

    Map<String, Object> getWhereParameters() throws Exception {
        System.out.println("Filter conditions:");

        Integer apartmentNo = getInteger("ApartmentNo : ", false);

        Map<String, Object> whereParameters = new HashMap<>();

        whereParameters.put("ApartmentNo", apartmentNo);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Resident", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "BuildingNo, Name, Surname, PhoneNumber, ApartmentNo, ResidentType");

        List<Object> rows = new ArrayList<>();

        String name, surname, residentType;
        int buildingNo, phoneNumber, apartmentNo;

        int[] arr = new int[32];
        boolean flag = false;
        String sqlQuery = "SELECT * FROM Resident";
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement prepStmt2 = connection.prepareStatement(sqlQuery);
        ResultSet rs = prepStmt2.executeQuery();
        int counter = 0;
        while (rs.next()) {

            for (int i = 0; i < 33; i++) {
                if (i == rs.getInt(5)) {
                    arr[counter++] = i;
                }
            }
        }
        apartmentNo = getInteger("ApartmentNo : ", false);

        for (int j = 0; j < arr.length; j++) {
            if (apartmentNo == arr[j]) {
                flag = true;
                break;
            }
        }
        if (flag != true) {
            System.out.println("Fields to insert:");
            buildingNo = getInteger("BuildingNo : ", false);
            name = getString("Name : ", false);
            surname = getString("Surname : ", false);
            phoneNumber = getInteger("PhoneNumber : ", false);
            residentType = getString("ResidentType : ", false);
            System.out.println();
            rows.add(new Resident(buildingNo, name, surname, phoneNumber, apartmentNo, residentType));
            parameters.put("rows", rows);
        } else {
            System.out.println("Someone already lives in this apartment!!!");
            return new ViewData(null, null, null);
        }

        return new ViewData("Resident", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String name = getString("Name : ", false);
        String surname = getString("Surname : ", false);
        Integer phoneNumber = getInteger("PhoneNumber : ", false);
        String residentType = getString("ResidentType : ", false);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();

        updateParameters.put("Name", name);

        updateParameters.put("Surname", surname);

        updateParameters.put("PhoneNumber", phoneNumber);

        updateParameters.put("ResidentType", residentType);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Resident", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Resident", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Resident View";
    }
}
