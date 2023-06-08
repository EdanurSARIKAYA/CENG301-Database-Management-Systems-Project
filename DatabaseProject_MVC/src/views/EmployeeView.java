package views;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.*;

public class EmployeeView implements ViewInterface {

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

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int buildingNo = resultSet.getInt("BuildingNo");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");
                String job = resultSet.getString("Job");
                double salary = resultSet.getDouble("Salary");
                int employeeID = resultSet.getInt("EmployeeID");
                String date = resultSet.getString("Date");
                int phoneNumber = resultSet.getInt("PhoneNumber");

                // Display values
                System.out.println(buildingNo + " " + name + " " + surname + " " + job + " " + salary + " " + employeeID + " " + date + " " + phoneNumber);

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

        Integer employeeID = getInteger("EmployeeID : ", false);
        String date = getString("Date : ", false);

        Map<String, Object> whereParameters = new HashMap<>();

        if (employeeID != null && date != null) {
            whereParameters.put("EmployeeID", employeeID);
            whereParameters.put("Date", date);
        }

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Employee", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "BuildingNo, Name, Surname, Job,Salary,EmployeeID,Date,PhoneNumber");

        List<Object> rows = new ArrayList<>();

        String name, surname, job, date;
        int buildingNo, phoneNumber, employeeID;
        double salary;

        System.out.println("Fields to insert:");
        buildingNo = getInteger("BuildingNo : ", false);
        name = getString("Name : ", false);
        surname = getString("Surname : ", false);
        job = getString("Job : ", false);
        salary = getDouble("Salary : ", false);
        employeeID = getInteger("EmployeeID : ", false);
        date = getString("Date : ", false);
        phoneNumber = getInteger("PhoneNumber : ", false);

        System.out.println();

        if (name != null && surname != null && job != null && date != null) {
            rows.add(new Employee(buildingNo, name, surname, job, salary, employeeID, date, phoneNumber));
        }

        parameters.put("rows", rows);

        return new ViewData("Employee", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String name = getString("Name : ", false);
        String surname = getString("Surname : ", false);
        String job = getString("Job : ", false);
        Double salary = getDouble("Salary : ", false);
        Integer phoneNumber = getInteger("PhoneNumber : ", false);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();

        updateParameters.put("Name", name);

        updateParameters.put("Surname", surname);

        updateParameters.put("Job", job);

        updateParameters.put("Salary", salary);

        updateParameters.put("PhoneNumber", phoneNumber);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Employee", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Employee", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Employee View";
    }
}
