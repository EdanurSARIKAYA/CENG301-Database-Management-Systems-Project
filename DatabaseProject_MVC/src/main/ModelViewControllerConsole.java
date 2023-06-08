
package main;

import java.util.HashMap;
import java.util.Map;
import models.*;
import views.*;

public class ModelViewControllerConsole {
    public static void main(String[] args) throws Exception {
		// Connect to database
		connectToDatabase();
		
		// Model View Controller (MVC)
		// Router knows all the controllers
		Map<String, Controller> router = new HashMap<>();
                
		router.put("MainMenuView", new Controller(new MainMenuView(), new NopModel()));
		router.put("Income", new Controller(new IncomeView(), new IncomeModel()));
                router.put("Expense", new Controller(new ExpenseView(),new ExpenseModel()));
                router.put("Dues",new Controller(new DuesView(),new DuesModel()));
                router.put("AssistantManager",new Controller(new AssistantManagerView(),new AssistantManagerModel()));
                router.put("Manager", new Controller(new ManagerView(),new ManagerModel()));
                router.put("Controller", new Controller(new ControllerView(),new ControllerModel()));
                router.put("BankAccount",new Controller(new BankAccountView(), new BankAccountModel()));
                router.put("Resident",new Controller(new ResidentView(),new ResidentModel()));
                router.put("Employee",new Controller(new EmployeeView(),new EmployeeModel()));
                

		ViewData viewData = new ViewData("MainMenuView", "",null);		
		do {
			// Model, View, and Controller
			Controller controller = router.get(viewData.functionName);
			ModelData modelData = controller.executeModel(viewData);
			viewData = controller.getView(modelData, viewData.functionName, viewData.operationName);
			
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println();
		}
		while (viewData.functionName != null);
		
		System.out.println();
		System.out.println();
		System.out.println("Program is ended...");


		// Disconnect from database
		disconnectFromDatabase();
	}

	
	public static void connectToDatabase() {
		DatabaseUtilities.host = "localhost:1433";
		DatabaseUtilities.databaseName = "BuildingManagement";
		
		try {
			DatabaseUtilities.getConnection();
		}
		catch(Exception e) {
			System.out.println("Exception occured : " + e);
			return;
		}		
	}
	
	public static void disconnectFromDatabase() {
		try {
			DatabaseUtilities.disconnect();
		}
		catch(Exception e) {
			System.out.println("Error disconnecting from database : " + e);
			return;
		}		
	}
}
