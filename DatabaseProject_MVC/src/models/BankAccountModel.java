package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import main.*;

public class BankAccountModel implements ModelInterface{
        @Override
	public ResultSet select(Map<String, Object> whereParameters) throws Exception {
		// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("	BuildingNo, Date, TotalIncome, TotalExpense, MonthlyStatus");
		sql.append(" FROM BankAccount ");

		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
		
		sql.append("ORDER BY Date");		
		//System.out.println(sql.toString() + "\n");

		
		// execute constructed SQL statement
		Connection connection = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
		ResultSet result = preparedStatement.executeQuery();
		
		return result;
	}
		
	@Override
	public int insert(String fieldNames, List<Object> rows) throws Exception
	{
		// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO BankAccount (" + fieldNames + ") " );
		sql.append(" VALUES ");

		String[] fieldList = fieldNames.split(",");

		int rowCount = 0;
		for (int i=0; i<rows.size(); i++) {
			if (rows.get(i) instanceof BankAccount) {
				rowCount++;
				
				BankAccount bankAccount = (BankAccount)rows.get(i); 
	
				sql.append("(");
				for (int j=0; j<fieldList.length; j++) {
					String fieldName = fieldList[j].trim();
					sql.append(DatabaseUtilities.formatField(bankAccount.getByName(fieldName)));
					if (j < fieldList.length - 1) {
						sql.append(", ");
					}
				}
				sql.append(")");
				
				if (i < rows.size() - 1) {
					sql.append(", ");
				}				
			}
		}		
		//System.out.println(sql.toString());
		
		
		// execute constructed SQL statement
		if (rowCount > 0) {
			Connection connection = DatabaseUtilities.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			rowCount = preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		
		return rowCount;
	}
	
	@Override
	public int update(Map<String,Object> updateParameters, Map<String,Object> whereParameters) throws Exception
	{
		return 1;
	}

	@Override
	public int delete(Map<String,Object> whereParameters) throws Exception
	{
		return 1;
	}

	@Override
	public String toString() {
		return "Bank Account Model";
	}
}
